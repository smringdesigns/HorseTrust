import Image from "next/image"
import { getListing } from "@/lib/api"
import { mapBackendListingToFrontend } from "@/lib/mappers"

export default async function HorseDetailPage({ params }: { params: { id: string } }) {
  const listingData = await getListing(params.id)

  // Usamos el mapper
  const listing = mapBackendListingToFrontend(listingData)

  const formattedPrice = new Intl.NumberFormat("en-US", {
    style: "currency",
    currency: "USD",
    maximumFractionDigits: 0,
  }).format(listing.price)

  return (
    <main className="max-w-7xl mx-auto px-6 py-10">
      <div className="grid lg:grid-cols-2 gap-10">
        <div className="relative aspect-video rounded-2xl overflow-hidden">
          <Image
            src={listing.imageUrl || "/placeholder.jpg"}
            alt={listing.name}
            fill
            className="object-cover"
          />
        </div>

        <div>
          <h1 className="text-4xl font-black mb-2">{listing.name}</h1>

          <p className="text-slate-400 mb-6">
            {listing.breed} • {listing.age} años • {listing.location}
          </p>

          <p className="text-3xl font-bold text-[#13ec37] mb-6">{formattedPrice}</p>

          {listing.verified && (
            <span className="inline-block bg-[#13ec37]/10 text-[#13ec37] px-3 py-1 rounded-full text-xs font-bold mb-6">
              VERIFIED LISTING
            </span>
          )}

          <p className="text-slate-300 leading-relaxed">{listing.description}</p>
        </div>
      </div>
    </main>
  )
}