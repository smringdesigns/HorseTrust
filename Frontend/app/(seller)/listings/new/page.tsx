"use client";

import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";
import NewListingForm from "@/components/forms/NewListingForm";

export default function NewListingPage() {
  const router = useRouter();
  const [token, setToken] = useState<string | null>(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const storedToken = localStorage.getItem("token");

    if (!storedToken) {
      router.push("/login");
    } else {
      setToken(storedToken);
    }

    setLoading(false);
  }, [router]);

  if (loading) return null;
  if (!token) return null;

  return (
    <div className="p-10">
      <NewListingForm token={token} />
    </div>
  );
}