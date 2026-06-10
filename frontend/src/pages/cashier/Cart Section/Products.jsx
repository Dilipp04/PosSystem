import { Button } from '@/components/ui/button'
import { Input } from '@/components/ui/input'
import { BarcodeIcon } from 'lucide-react'
import React from 'react'
import ProductCard from './ProductCard'

const products = [
    {
        id: 1,
        name: "Product Name",
        image: "https://tse2.mm.bing.net/th/id/OIP.Mriaa4Q97uJt6YnaHdKhGgHaHa?pid=Api&h=220&P=0",
        sku: "SKU12345",
        price: 899,
        category: "men_shirt",
    },
    {
        id: 2,
        name: "Product Name",
        image: "https://tse2.mm.bing.net/th/id/OIP.Mriaa4Q97uJt6YnaHdKhGgHaHa?pid=Api&h=220&P=0",
        sku: "SKU12345",
        price: 899,
        category: "men_shirt",
    },
    {
        id: 3,
        name: "Product Name",
        image: "https://tse2.mm.bing.net/th/id/OIP.Mriaa4Q97uJt6YnaHdKhGgHaHa?pid=Api&h=220&P=0",
        sku: "SKU12345",
        price: 899,
        category: "men_shirt",
    },
]

const Products = () => {
    return (
        <div className='h-full flex flex-col'>
            <div className='p-4 bg-slate-100 border-b border-slate-200'>
                <Input className='text-sm rounded-2xl border-slate-200 bg-white' type='text' placeholder='Search products...' />
                <div className='mt-4 flex items-center justify-between gap-4'>
                    <span className='font-semibold'>2 Products found</span>
                    <Button variant='secondary' className='rounded-full px-4 py-2 gap-2'>
                        <BarcodeIcon /> Scan
                    </Button>
                </div>
            </div>
            <div className='p-4 grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4 overflow-y-auto grow'>
                {products.map((product) => (
                    <ProductCard product={product} key={product.id} />
                ))}
            </div>
        </div>
    )
}

export default Products