const BASE_URL = "http://localhost:8080/api/v1";

/**
 * Función base para peticiones estándar (JSON)
 */
export async function fetchAPI(endpoint: string, options: RequestInit = {}) {
  const res = await fetch(`${BASE_URL}${endpoint}`, {
    ...options,
    headers: {
      "Content-Type": "application/json",
      ...(options.headers || {}),
    },
  });
  
  if (!res.ok) throw new Error(`API error: ${res.status}`);
  return res.json();
}

// --- LISTINGS (Anuncios) ---

export async function getListings() {
  return fetchAPI("/listings");
}

export async function getListing(id: string) {
  return fetchAPI(`/listings/${id}`);
}

/**
 * Registro de caballo con soporte para IMAGEN
 * Aquí no usamos fetchAPI porque no queremos el header Content-Type JSON
 */
export async function createListing(formData: FormData, token: string) {
  const res = await fetch(`${BASE_URL}/listings`, {
    method: "POST",
    headers: {
      // IMPORTANTE: Al enviar FormData, NO se define el Content-Type.
      // El navegador lo hace solo para incluir el "boundary" del archivo.
      "Authorization": `Bearer ${token}`,
    },
    body: formData, // Enviamos el objeto FormData directamente
  });

  if (!res.ok) {
    const errorBody = await res.json().catch(() => ({}));
    throw new Error(errorBody.message || "Error creando anuncio");
  }
  
  return res.json();
}

// --- USERS ---

export async function getUserProfile(id: string) {
  return fetchAPI(`/users/${id}/profile`);
}

// --- AUTHENTICATION ---

export async function login(credentials: { email: string, password: string }) {
  return fetchAPI("/auth/login", { 
    method: "POST", 
    body: JSON.stringify(credentials) 
  });
}

export async function register(credentials: { name: string, email: string, password: string }) {
  return fetchAPI("/auth/register", { 
    method: "POST", 
    body: JSON.stringify(credentials) 
  });
}