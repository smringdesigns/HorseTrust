import HorseCard from '@/components/horse/HorseCard';
import { getListings } from '@/lib/api';

export default async function SearchPage() {
  const horses = await getListings();

  return (
    <div className="p-8">
      <h1 className="text-2xl font-bold mb-6">Marketplace</h1>

      <div className="grid grid-cols-1 md:grid-cols-2 xl:grid-cols-3 gap-6">
        {horses.map((horse) => (
          <HorseCard key={horse.id} horse={horse} />
        ))}
      </div>
    </div>
  );
}