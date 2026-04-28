"use client";

import AuthGuard from "@/components/auth/AuthGuard";
import { useState, useEffect } from "react";

export default function SellerDashboard() {
  const [userName, setUserName] = useState("Vendedor");

  useEffect(() => {
    const name = localStorage.getItem("user_name");
    if (name) setUserName(name);
  }, []);

  return (
    <AuthGuard>
      <div className="min-h-screen bg-[#0a0f0b] text-white flex">
        
        {/* BARRA LATERAL (Sidebar Mini) */}
        <aside className="w-20 border-r border-white/5 flex flex-col items-center py-8 gap-10">
          <div className="text-[#13ec37] font-black italic text-xl">HT</div>
          <nav className="flex flex-col gap-8">
            <span className="material-symbols-outlined text-[#13ec37] cursor-pointer">dashboard</span>
            <span className="material-symbols-outlined text-slate-600 hover:text-white cursor-pointer transition-colors">database</span>
            <span className="material-symbols-outlined text-slate-600 hover:text-white cursor-pointer transition-colors">mail</span>
            <span className="material-symbols-outlined text-slate-600 hover:text-white cursor-pointer transition-colors">settings</span>
          </nav>
        </aside>

        {/* CONTENIDO PRINCIPAL */}
        <main className="flex-1 p-12 overflow-y-auto">
          
          {/* HEADER DEL DASHBOARD */}
          <header className="flex justify-between items-end mb-12">
            <div>
              <p className="text-[#13ec37] text-[10px] font-bold uppercase tracking-[0.3em] mb-2">Panel Administrativo</p>
              <h1 className="text-4xl font-black tracking-tight">Bienvenido, {userName}</h1>
            </div>
            
            {/* BOTÓN PRINCIPAL: AGREGAR CABALLO */}
            <button className="bg-[#13ec37] text-black px-6 py-3 rounded-xl font-black uppercase text-xs tracking-widest flex items-center gap-2 hover:scale-105 transition-all shadow-lg shadow-[#13ec37]/20">
              <span className="material-symbols-outlined text-sm">add_circle</span>
              Registrar Nuevo Ejemplar
            </button>
          </header>

          {/* GRID DE ESTADÍSTICAS */}
          <div className="grid grid-cols-1 md:grid-cols-3 gap-6 mb-12">
            <StatCard label="Mis Publicaciones" value="12" sub="8 Verificadas" icon="visibility" />
            <StatCard label="Interesados" value="48" sub="+12 esta semana" icon="groups" />
            <StatCard label="Trust Score" value="98%" sub="Rendimiento Elite" icon="verified_user" />
          </div>

          {/* SECCIÓN DE GESTIÓN */}
          <section className="bg-white/5 border border-white/10 rounded-3xl p-8">
            <div className="flex justify-between items-center mb-8">
              <h3 className="text-xl font-bold italic">Inventario de Caballos</h3>
              <div className="flex gap-4">
                <div className="bg-black/40 border border-white/10 px-4 py-2 rounded-lg text-xs text-slate-400 flex items-center gap-2">
                   <span className="material-symbols-outlined text-sm">search</span>
                   Buscar en mi lista...
                </div>
              </div>
            </div>

            {/* TABLA O LISTA DE CABALLOS (Placeholder) */}
            <div className="space-y-4">
               {[1, 2, 3].map((i) => (
                 <div key={i} className="flex items-center justify-between p-4 bg-white/[0.02] border border-white/5 rounded-2xl hover:bg-white/[0.05] transition-all group">
                   <div className="flex items-center gap-4">
                     <div className="w-12 h-12 bg-slate-800 rounded-xl overflow-hidden">
                       <div className="w-full h-full bg-[#13ec37]/10 flex items-center justify-center">
                         <span className="material-symbols-outlined text-[#13ec37]">image</span>
                       </div>
                     </div>
                     <div>
                       <p className="font-bold">Ejemplar de Prueba #{i}</p>
                       <p className="text-[10px] text-slate-500 uppercase font-bold tracking-widest">KWPN Warmblood</p>
                     </div>
                   </div>
                   <div className="flex gap-3 opacity-0 group-hover:opacity-100 transition-opacity">
                     <button className="p-2 hover:text-[#13ec37] transition-colors"><span className="material-symbols-outlined text-sm">edit</span></button>
                     <button className="p-2 hover:text-red-500 transition-colors"><span className="material-symbols-outlined text-sm">delete</span></button>
                   </div>
                 </div>
               ))}
            </div>
          </section>

        </main>
      </div>
    </AuthGuard>
  );
}

// Componente pequeño para las tarjetas de stats
function StatCard({ label, value, sub, icon }: { label: string, value: string, sub: string, icon: string }) {
  return (
    <div className="bg-white/5 border border-white/10 p-6 rounded-3xl relative overflow-hidden group hover:border-[#13ec37]/50 transition-all">
      <span className="material-symbols-outlined absolute right-[-10px] bottom-[-10px] text-white/5 text-8xl group-hover:text-[#13ec37]/10 transition-colors">
        {icon}
      </span>
      <p className="text-slate-500 text-[10px] font-bold uppercase tracking-widest mb-1">{label}</p>
      <p className="text-4xl font-black mb-1">{value}</p>
      <p className="text-[#13ec37] text-[10px] font-bold uppercase">{sub}</p>
    </div>
  );
}