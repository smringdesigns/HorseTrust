import HorseCard from '@/components/horse/HorseCard';
import { getListings } from '@/lib/api';
import { mapBackendListingToFrontend } from '@/lib/mappers';
import { Listing } from '@/types/listing';

export default async function MarketplaceHome() {
  let response;
  try {
    response = await getListings();
  } catch {
    response = null;
  }

  const horsesRaw = Array.isArray(response?.listings) ? response.listings : [];
  const horses: Listing[] = horsesRaw.map(mapBackendListingToFrontend);

  return (
    <main className="min-h-screen bg-[#f6f8f6] dark:bg-[#102215] pb-24">
      <div className="max-w-7xl mx-auto px-6">
        <section className="mb-16 pt-12">
          <h3 className="text-2xl font-bold mb-6 text-white italic tracking-tight">
            Nuevos Ingresos
          </h3>
          {horses.length === 0 ? (
            <p>No hay caballos disponibles por el momento.</p>
          ) : (
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
              {horses.map(horse => <HorseCard key={horse.id} horse={horse} />)}
            </div>
          )}
        </section>
      </div>
    </main>
  );
}