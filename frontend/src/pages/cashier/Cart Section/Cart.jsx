import { Button } from '@/components/ui/button'
import { Pause, ShoppingCart, Trash } from 'lucide-react'
import React from 'react'
import CartCard from './CartCard'

const orderedProducts = [

    {
        id: 1,
        product_name: "Men Slin Fit Checkered Spread Coller Cashual Shirt ",
        sku: "SHRT-COTTON-BLACK-2025",
        quantity: 2,
        price: 799,

    },
    {
        id: 1,
        product_name: "Men Slin Fit Checkered Spread Coller Cashual Shirt ",
        sku: "SHRT-COTTON-BLACK-2025",
        quantity: 2,
        price: 799,

    },

]

const Cart = () => {
    return (

        <div className='border-r-2 h-[90vh] flex flex-col justify-between'>

            <div className='p-3 bg-gray-100 border-b-2 flex justify-between items-center'>
                <div className='flex font-semibold'> <ShoppingCart />  Cart ({3}) item</div>
                <div className='flex items-center gap-2'>
                    <Button variant='secondary'> <Pause /> HOLD</Button>
                    <Button variant='secondary'> <Trash /> Clear</Button>
                </div>
            </div>
            <div className='p-4 flex flex-col gap-3 grow overflow-scroll'>

                {
                    orderedProducts.map((item) => {
                        return <CartCard product={item} key={item.id} />
                    })
                }
            </div>
            <div className='p-3 bg-gray-100 mb-8'>
                <div className='w-full flex justify-between'>
                    <p className='text-sm text-gray-700'>Sub Total </p>
                    <p className='text-sm text-gray-700'>{orderedProducts.reduce((acc, val) => { return acc + (val.price * val.quantity) }, 0)}</p>
                </div>
                <div className='w-full flex justify-between'>
                    <p className='text-sm text-gray-700'>Tax </p>
                    <p className='text-sm text-gray-700'>{orderedProducts.reduce((acc, val) => { return acc + (val.price * val.quantity) }, 0) * 0.05}</p>
                </div>
                <div className='w-full border-t-2 flex justify-between'>
                    <p className='font-semibold text-sm text-gray-700'>Total </p>
                    <p className='font-semibold text-sm text-gray-700'>{orderedProducts.reduce((acc, val) => { return acc + (val.price * val.quantity) }, 0)}</p>
                </div>
            </div>

        </div>
    )
}

export default Cart