export type Listing = {
  id: string
  name: string
  price: number
  breed: string
  age: number
  location: string
  imageUrl?: string
  verified?: boolean
  description?: string
}