package com.vargas.horsemarket.infrastructure.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de Swagger / OpenAPI 3 con soporte de JWT.
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        final String securitySchemeName = "bearerAuth";

        return new OpenAPI()
                .info(new Info()
                        .title("HorseMarket API")
                        .description("Marketplace de compra-venta de caballos con anuncios verificados. " +
                                "Incluye verificación veterinaria, videos de rendimiento y mensajería segura.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Vargas HorseMarket Team")
                                .email("dev@vargas.com"))
                        .license(new License()
                                .name("Privado")
                                .url("https://vargas.com")))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .description("Ingresa tu JWT token. Ejemplo: Bearer eyJhbGci...")));
    }
}
