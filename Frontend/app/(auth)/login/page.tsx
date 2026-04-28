"use client";

import { useState } from "react";
import { login } from "@/lib/api";

export default function LoginPage() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [isLoading, setIsLoading] = useState(false);

  const handleLogin = async (e: React.FormEvent) => {
    e.preventDefault();
    setIsLoading(true);

    try {
      const data = await login({ email, password });

      // Guardar token para las peticiones futuras a Java
      localStorage.setItem("token", data.token);

      // Pequeño retardo para que la animación de carga se vea pro
      setTimeout(() => {
        window.location.href = "/";
      }, 500);
      
    } catch (err) {
      alert("Error al iniciar sesión ❌. Verifica tus credenciales.");
      console.error(err);
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="flex items-center justify-center min-h-screen bg-[#0a0f0b] relative overflow-hidden">
      
      {/* Decoración de fondo (Efecto de luz verde tecnológica) */}
      <div className="absolute top-[-10%] right-[-10%] w-[500px] h-[500px] bg-[#13ec37]/5 rounded-full blur-[120px]" />
      <div className="absolute bottom-[-10%] left-[-10%] w-[400px] h-[400px] bg-[#13ec37]/5 rounded-full blur-[100px]" />

      <form
        onSubmit={handleLogin}
        className="relative z-10 bg-white/5 border border-white/10 p-10 rounded-3xl flex flex-col gap-6 w-[400px] backdrop-blur-xl shadow-2xl"
      >
        <div className="text-center mb-4">
          <h1 className="text-white text-3xl font-black italic tracking-tighter">
            HORSE<span className="text-[#13ec37]">TRUST</span>
          </h1>
          <p className="text-slate-500 text-[10px] uppercase tracking-[0.3em] font-bold mt-2">
            Marketplace de Caballos
          </p>
        </div>

        <div className="space-y-4">
          {/* Campo Email */}
          <div className="flex flex-col gap-2">
            <label className="text-[10px] font-bold text-[#13ec37] uppercase ml-1">Email</label>
            <input
              type="email"
              placeholder="admin@horsetrust.com"
              value={email}
              onChange={e => setEmail(e.target.value)}
              className="p-4 rounded-xl bg-black/40 border border-white/10 text-white outline-none focus:border-[#13ec37] transition-all placeholder:text-slate-700"
              required
            />
          </div>

          {/* Campo Password */}
          <div className="flex flex-col gap-2">
            <label className="text-[10px] font-bold text-[#13ec37] uppercase ml-1">Contraseña</label>
            <input
              type="password"
              placeholder="••••••••"
              value={password}
              onChange={e => setPassword(e.target.value)}
              className="p-4 rounded-xl bg-black/40 border border-white/10 text-white outline-none focus:border-[#13ec37] transition-all placeholder:text-slate-700"
              required
            />
          </div>
        </div>

        <button 
          disabled={isLoading}
          className="bg-[#13ec37] text-black py-4 rounded-xl font-black uppercase tracking-widest hover:scale-[1.02] active:scale-[0.98] transition-all shadow-lg shadow-[#13ec37]/20 mt-4 flex justify-center items-center"
        >
          {isLoading ? (
            <div className="w-6 h-6 border-2 border-black/20 border-t-black rounded-full animate-spin" />
          ) : (
            "Ingresar al Sistema"
          )}
        </button>

        <div className="text-center mt-2">
          <a href="#" className="text-[10px] text-slate-500 hover:text-white transition-colors uppercase font-bold tracking-widest">
            ¿Problemas para acceder? Contacte a Soporte
          </a>
        </div>
      </form>
    </div>
  );
}