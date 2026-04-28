#  HorseTrust — Marketplace de Caballos Verificados

Backend completo en **Spring Boot 3 + Java 21** con **Arquitectura Hexagonal (Ports & Adapters)**.

---

##  Estructura de Paquetes

```
com.vargas.horsemarket
├── domain/                     ← Núcleo del negocio (sin dependencias de infraestructura)
│   ├── model/                  ← Entidades de dominio puras
│   │   ├── User.java
│   │   ├── HorseListing.java
│   │   ├── VeterinaryRecord.java
│   │   ├── PerformanceVideo.java
│   │   ├── Message.java
│   │   ├── Rating.java
│   │   └── Report.java
│   ├── valueobject/
│   │   └── Money.java          ← Value Object inmutable
│   ├── repository/             ← PUERTOS de salida (interfaces)
│   │   ├── UserRepository.java
│   │   ├── HorseListingRepository.java
│   │   ├── VeterinaryRecordRepository.java
│   │   ├── MessageRepository.java
│   │   ├── RatingRepository.java
│   │   └── ReportRepository.java
│   └── enums/
│       ├── UserRole.java       ← BUYER, SELLER, VET, ADMIN
│       ├── ListingStatus.java  ← DRAFT → PUBLISHED → UNDER_VERIFICATION → VERIFIED/REJECTED → SOLD
│       ├── VerificationStatus.java
│       ├── VideoStatus.java
│       └── ReportStatus.java
│
├── application/                ← Casos de uso (orquestación)
│   ├── usecase/
│   │   ├── AuthUseCase.java              ← Registro y login JWT
│   │   ├── CreateListingUseCase.java     ← Crear anuncio (SELLER)
│   │   ├── PublishListingUseCase.java    ← Publicar anuncio
│   │   ├── ListingQueryUseCase.java      ← Consultas paginadas
│   │   ├── VetVerificationUseCase.java   ← ⭐ CORE: verificación veterinaria
│   │   ├── MessageUseCase.java           ← Chat comprador-vendedor
│   │   └── RatingUseCase.java            ← Calificaciones post-venta
│   ├── service/
│   │   └── ListingResponseMapper.java
│   └── dto/                    ← DTOs de entrada y salida
│
├── infrastructure/             ← ADAPTADORES (implementaciones concretas)
│   ├── config/
│   │   ├── SecurityConfig.java           ← Spring Security 6 + JWT
│   │   └── OpenApiConfig.java            ← Swagger UI
│   ├── security/
│   │   ├── JwtService.java               ← Generación/validación JWT
│   │   ├── JwtAuthFilter.java            ← Filtro por request
│   │   └── UserDetailsServiceImpl.java
│   ├── persistence/
│   │   ├── entity/                       ← Entidades JPA (separadas del dominio)
│   │   ├── repository/                   ← Spring Data JPA repositories
│   │   └── adapter/                      ← Implementan los puertos del dominio
│   ├── controller/             ← REST Controllers (sin lógica de negocio)
│   │   ├── AuthController.java
│   │   ├── HorseListingController.java
│   │   ├── VetController.java
│   │   ├── MessageController.java
│   │   ├── RatingController.java
│   │   └── UserController.java
│   └── mapper/                 ← MapStruct (Entity ↔ Domain)
│
└── shared/
    ├── exception/
    │   └── GlobalExceptionHandler.java   ← @RestControllerAdvice
    └── audit/
        └── AuditorAwareImpl.java         ← Auditoría automática con Spring Data
```

---

##  Cómo ejecutar

### Prerrequisitos
- Java 21
- Maven 3.9+
- MySQL 8.0+

### Configuración de base de datos
```sql
CREATE DATABASE horsemarket_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### Ajustar credenciales en `application.properties`
```properties
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_PASSWORD
```

### Ejecutar
```bash
mvn spring-boot:run
```

Flyway creará automáticamente todas las tablas al iniciar.

---

##  API Endpoints

### Swagger UI
```
http://localhost:8080/api/v1/swagger-ui.html
```

### Autenticación
| Método | Ruta | Descripción |
|--------|------|-------------|
| POST | `/api/v1/auth/register` | Registrar usuario |
| POST | `/api/v1/auth/login` | Login → JWT |

### Anuncios
| Método | Ruta | Rol | Descripción |
|--------|------|-----|-------------|
| GET | `/api/v1/listings` | Público | Listar publicados |
| GET | `/api/v1/listings/verified` | Público | Listar verificados |
| GET | `/api/v1/listings/{id}` | Público | Detalle |
| GET | `/api/v1/listings/search?breed=` | Público | Buscar por raza |
| GET | `/api/v1/listings/my` | SELLER | Mis anuncios |
| POST | `/api/v1/listings` | SELLER | Crear anuncio |
| PATCH | `/api/v1/listings/{id}/publish` | SELLER | Publicar |

### Verificación Veterinaria
| Método | Ruta | Rol | Descripción |
|--------|------|-----|-------------|
| POST | `/api/v1/vet/records` | VET | Iniciar revisión |
| PATCH | `/api/v1/vet/records/{id}/verify` | VET | Aprobar/rechazar |

### Mensajería
| Método | Ruta | Rol | Descripción |
|--------|------|-----|-------------|
| POST | `/api/v1/messages` | Auth | Enviar mensaje |
| GET | `/api/v1/messages/conversation/{listingId}/{otherUserId}` | Auth | Ver conversación |

### Calificaciones
| Método | Ruta | Rol | Descripción |
|--------|------|-----|-------------|
| POST | `/api/v1/ratings` | BUYER | Calificar vendedor |

---

##  Flujo de Verificación (CORE)

```
DRAFT → [SELLER publica] → PUBLISHED
     → [VET crea registro] → UNDER_VERIFICATION
     → [VET verifica] → VERIFIED ✅ o REJECTED ❌
                      → [Solo VERIFIED] → PREMIUM ⭐
                                       → SOLD 🎉
```

---

##  Seguridad

- JWT firmado con HS256 (configurable en `application.properties`)
- BCrypt con factor 12 para contraseñas
- Spring Security 6 con filtros stateless
- `@PreAuthorize` por rol en cada endpoint sensible
- CORS configurado (ajustar orígenes para producción)

### Usuarios de prueba (seed)
| Email | Password | Rol |
|-------|----------|-----|
| `admin@horsemarket.com` | `Admin1234!` | ADMIN |
| `vet@horsemarket.com` | `Admin1234!` | VET |

---

##  Migraciones Flyway

| Script | Contenido |
|--------|-----------|
| V1 | Usuarios, roles, refresh tokens |
| V2 | Anuncios de caballos, imágenes |
| V3 | Registros veterinarios |
| V4 | Videos de rendimiento |
| V5 | Mensajería |
| V6 | Calificaciones |
| V7 | Reportes anti-fraude |
| V8 | Datos semilla |

---

##  Stack Técnico

| Tecnología | Versión | Uso |
|------------|---------|-----|
| Java | 21 | Lenguaje |
| Spring Boot | 3.2.3 | Framework |
| Spring Security | 6 | Autenticación/Autorización |
| Spring Data JPA | 3.2 | Persistencia |
| MySQL | 8.0 | Base de datos |
| Flyway | 10 | Migraciones |
| jjwt | 0.12.3 | JWT |
| MapStruct | 1.5.5 | Mapeo entidades ↔ dominio |
| Lombok | 1.18.30 | Reducción de boilerplate |
| Springdoc OpenAPI | 2.3.0 | Swagger UI |

---

## Principios Aplicados

- **Arquitectura Hexagonal**: Dominio independiente de infraestructura
- **DDD ligero**: Entidades de dominio con reglas de negocio, Value Objects
- **SOLID**: Separación de responsabilidades, interfaces para repositorios
- **Clean Code**: Nombres descriptivos, métodos pequeños, SLF4J para logs
- **Auditoria automática**: `createdAt`, `updatedAt`, `createdBy`, `updatedBy`
