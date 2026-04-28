"use client";

import { useState, useEffect, useCallback } from "react";
import { createListing } from "@/lib/api";

type FormDataType = {
  title: string;
  horseName: string;
  price: number;
  breed: string;
  ageYears: number;
  description: string;
};

const INITIAL_FORM: FormDataType = {
  title: "",
  horseName: "",
  price: 0,
  breed: "",
  ageYears: 1,
  description: "",
};

export default function NewListingForm({ token }: { token: string }) {
  const [loading, setLoading] = useState(false);
  const [preview, setPreview] = useState<string | null>(null);
  const [imageFile, setImageFile] = useState<File | null>(null);
  const [formData, setFormData] = useState<FormDataType>(INITIAL_FORM);

  // ✅ Evita memory leaks al cambiar preview
  useEffect(() => {
    return () => {
      if (preview) URL.revokeObjectURL(preview);
    };
  }, [preview]);

  const handleChange = useCallback(
    (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
      const { name, value } = e.target;

      setFormData((prev) => ({
        ...prev,
        [name]:
          name === "price" || name === "ageYears"
            ? Number(value)
            : value,
      }));
    },
    []
  );

  const handleImageChange = useCallback(
    (e: React.ChangeEvent<HTMLInputElement>) => {
      const file = e.target.files?.[0];
      if (!file) return;

      setImageFile(file);
      setPreview(URL.createObjectURL(file));
    },
    []
  );

  const buildFormData = () => {
    const data = new FormData();

    if (imageFile) data.append("file", imageFile);

    Object.entries(formData).forEach(([key, value]) => {
      data.append(key, String(value));
    });

    // valores por defecto
    data.append("sex", "Macho");
    data.append("discipline", "Salto");
    data.append("currencyCode", "USD");

    return data;
  };

  const resetForm = () => {
    setFormData(INITIAL_FORM);
    setImageFile(null);
    setPreview(null);
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!token || loading) return;

    try {
      setLoading(true);
      await createListing(buildFormData(), token);
      resetForm();
      alert("✅ ¡Ejemplar registrado con éxito!");
    } catch (err: any) {
      console.error(err);
      alert(err?.message || "❌ Error inesperado");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen w-full bg-[#0a0f0b] flex items-center justify-center py-6 px-4 relative overflow-hidden">
      {/* Background */}
      <div className="fixed inset-0 z-0 bg-[#0a0f0b]">
        <div className="absolute top-[-10%] left-[-5%] w-[500px] h-[500px] bg-[#13ec37]/10 rounded-full blur-[120px]" />
        <div className="absolute bottom-[-10%] right-[-5%] w-[500px] h-[500px] bg-[#13ec37]/5 rounded-full blur-[100px]" />
      </div>

      <form
        onSubmit={handleSubmit}
        className="relative z-10 w-full max-w-2xl bg-black/60 backdrop-blur-3xl border border-[#13ec37]/30 p-8 rounded-[2.5rem] shadow-[0_0_60px_rgba(0,0,0,0.8)] flex flex-col gap-5"
      >
        <div className="text-center">
          <h2 className="text-2xl font-black text-white italic uppercase tracking-tighter">
            Nuevo <span className="text-[#13ec37]">Ejemplar</span>
          </h2>
          <div className="h-1 w-16 bg-[#13ec37] mx-auto mt-2 rounded-full shadow-[0_0_10px_#13ec37]" />
        </div>

        {/* Upload */}
        <div className="relative w-full h-40 bg-[#13ec37]/5 border-2 border-dashed border-[#13ec37]/20 rounded-3xl flex flex-col items-center justify-center overflow-hidden hover:border-[#13ec37]/50 transition-all cursor-pointer">
          {preview ? (
            <img src={preview} className="w-full h-full object-cover" alt="preview" />
          ) : (
            <div className="flex flex-col items-center">
              <span className="material-symbols-outlined text-[#13ec37] text-3xl animate-pulse">
                add_a_photo
              </span>
              <p className="text-[#13ec37]/60 text-[9px] font-bold uppercase tracking-widest mt-2">
                Imagen Oficial
              </p>
            </div>
          )}
          <input
            type="file"
            accept="image/*"
            className="absolute inset-0 opacity-0 cursor-pointer"
            onChange={handleImageChange}
          />
        </div>

        {/* Inputs */}
        <div className="grid grid-cols-1 md:grid-cols-2 gap-x-6 gap-y-4 text-left">
          <Input label="Título" name="title" onChange={handleChange} />
          <Input label="Nombre" name="horseName" onChange={handleChange} />
          <Input label="Precio (USD)" name="price" type="number" onChange={handleChange} />
          <Input label="Raza" name="breed" onChange={handleChange} />
          <Input label="Edad" name="ageYears" type="number" onChange={handleChange} />

          <div className="md:col-span-2 space-y-1">
            <label className="text-[9px] font-black text-[#13ec37]/70 uppercase tracking-widest ml-3 italic">
              Descripción
            </label>
            <textarea
              name="description"
              required
              rows={2}
              onChange={handleChange}
              className="w-full bg-white/5 border border-white/10 p-3.5 rounded-xl text-white outline-none focus:border-[#13ec37] resize-none text-sm"
            />
          </div>
        </div>

        <button
          type="submit"
          disabled={loading}
          className="w-full bg-[#13ec37] text-black py-4 rounded-2xl font-black uppercase tracking-[0.3em] text-[10px] hover:shadow-[0_0_25px_#13ec37] transition-all shadow-[0_6px_0_#0a6b1a] active:translate-y-[2px] disabled:opacity-50"
        >
          {loading ? "Sincronizando..." : "Confirmar Registro"}
        </button>
      </form>
    </div>
  );
}

/* 🔹 Subcomponente reutilizable */
function Input({
  label,
  name,
  type = "text",
  onChange,
}: {
  label: string;
  name: string;
  type?: string;
  onChange: any;
}) {
  return (
    <div className="space-y-1">
      <label className="text-[9px] font-black text-[#13ec37]/70 uppercase tracking-widest ml-3 italic">
        {label}
      </label>
      <input
        name={name}
        type={type}
        required
        onChange={onChange}
        className="w-full bg-white/5 border border-white/10 p-3.5 rounded-xl text-white outline-none focus:border-[#13ec37] text-sm"
      />
    </div>
  );
}