// lib/mappers.ts
import { Listing } from '@/types/listing';

export function mapBackendListingToFrontend(listingData: any): Listing {
  return {
    id: listingData.id?.toString() || listingData.horseName || "unknown-id",
    name: listingData.title || listingData.horseName || "Unknown",
    price: listingData.price || 0,
    breed: listingData.breed || "Desconocida",
    age: listingData.ageYears || 0,
    location: listingData.location || "Desconocida",
    imageUrl: (listingData.imageUrls && listingData.imageUrls[0]) || "/placeholder.jpg",
    verified: listingData.premium || false,
    description: listingData.description || "",
  };
}