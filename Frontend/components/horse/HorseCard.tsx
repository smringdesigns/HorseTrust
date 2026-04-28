import Link from "next/link";
import { Listing } from "@/types/listing";

type Props = {
  horse: Listing;
};

export default function HorseCard({ horse }: Props) {
  return (
    <Link href={`/horse/${horse.id}`}>
      <div className="bg-white rounded-xl shadow p-4 hover:shadow-lg transition cursor-pointer">
        <img
          src={horse.imageUrl || "/placeholder.jpg"}
          alt={horse.name}
          className="w-full h-48 object-cover rounded-lg mb-3"
        />

        <h3 className="text-lg font-bold">{horse.name}</h3>
        <p className="text-sm text-gray-500">
          {horse.breed} • {horse.age} años
        </p>

        <div className="flex justify-between mt-2">
          <span className="font-semibold">
            ${horse.price.toLocaleString()}
          </span>

          {horse.verified && (
            <span className="text-green-600 text-sm">✔ Verified</span>
          )}
        </div>
      </div>
    </Link>
  );
}