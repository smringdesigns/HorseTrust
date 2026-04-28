"use client";

import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";

export default function AuthGuard({ children }: { children: React.ReactNode }) {
  const router = useRouter();
  const [authorized, setAuthorized] = useState(false);

  useEffect(() => {
    // 1. Buscamos el token que guardaste en el login
    const token = localStorage.getItem("token");

    if (!token) {
      // 2. Si no hay token, lo mandamos al login de inmediato
      setAuthorized(false);
      router.push("/login");
    } else {
      // 3. Si hay token, permitimos ver el contenido
      setAuthorized(true);
    }
  }, [router]);

  // Mientras verifica, mostramos una pantalla de carga elegante
  if (!authorized) {
    return (
      <div className="min-h-screen bg-[#0a0f0b] flex items-center justify-center">
        <div className="flex flex-col items-center gap-4">
          <div className="w-12 h-12 border-4 border-[#13ec37]/20 border-t-[#13ec37] rounded-full animate-spin" />
          <p className="text-[#13ec37] font-bold text-xs uppercase tracking-widest animate-pulse">
            Verificando Credenciales...
          </p>
        </div>
      </div>
    );
  }

  return <>{children}</>;
}