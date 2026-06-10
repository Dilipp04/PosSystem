import { Button } from '@/components/ui/button'
import { Notebook } from 'lucide-react'

import React from 'react'
import Products from './Cart Section/Products'
import Cart from './Cart Section/Cart'

const CreateOrder = () => {
    return (
        <div className='h-full bg-slate-50'>
            <main className='grid h-full w-full grid-cols-12 gap-5 px-5'>
                <section className='col-span-12 xl:col-span-4 rounded-3xl bg-white border border-slate-200 shadow-sm overflow-hidden'>
                    <Products />
                </section>

                <section className='col-span-12 xl:col-span-5 rounded-3xl bg-white border border-slate-200 shadow-sm overflow-hidden'>
                    <Cart />
                </section>

                <aside className='col-span-12 xl:col-span-3 rounded-3xl bg-white border border-slate-200 shadow-sm p-5 flex flex-col gap-5'>
                    <div className='flex items-center justify-between'>
                        <div>
                            <p className='text-sm text-muted-foreground'>Customer</p>
                            <h2 className='font-semibold'>Select Customer</h2>
                        </div>
                        <Button variant='secondary' className='rounded-full px-4 py-2'>Select</Button>
                    </div>

                    <div className='space-y-2'>
                        <p className='text-sm text-muted-foreground'>Discount</p>
                        <div className='flex gap-2'>
                            <input type='number' min='0' defaultValue='0' className='w-full rounded-xl border border-slate-200 bg-slate-50 px-3 py-3 text-sm outline-none' />
                            <Button variant='outline' className='w-24 rounded-xl'>%</Button>
                            <Button variant='outline' className='w-24 rounded-xl'>$</Button>
                        </div>
                    </div>

                    <div className='space-y-2'>
                        <div className='flex items-center gap-2'>
                            <Notebook size={18} />
                            <p className='text-sm font-semibold'>Note</p>
                        </div>
                        <textarea placeholder='Enter note...' className='w-full min-h-30 rounded-2xl border border-slate-200 bg-slate-50 px-3 py-3 text-sm outline-none resize-none' />
                    </div>

                    <div className='mt-auto rounded-3xl border border-slate-200 bg-slate-100 p-4 text-center'>
                        <p className='text-sm text-muted-foreground'>Total Amount</p>
                        <p className='mt-3 text-4xl font-bold text-emerald-700'>899$</p>
                    </div>

                    <div className='space-y-3'>
                        <Button className='w-full rounded-3xl bg-emerald-700 text-white hover:bg-emerald-600'>Process Payment</Button>
                        <Button variant='secondary' className='w-full rounded-3xl'>Hold Order</Button>
                    </div>
                </aside>
            </main>
        </div>
    )
}

export default CreateOrder
