import { Button } from '@/components/ui/button'
import { Input } from '@/components/ui/input'
import { CalendarDays, Plus, Search, Star } from 'lucide-react'
import React, { useMemo, useState } from 'react'

const customers = [
    {
        id: 1,
        name: 'John Doe',
        email: 'john@example.com',
        phone: '123-456-7890',
        loyaltyPoints: 150,
        totalOrders: 8,
        totalSpent: 420,
        recentSpend: 940,
        orders: [
            {
                id: 1,
                date: '2023-09-12 02:30 PM',
                payment: 'Card',
                total: 75,
                status: 'Completed',
                items: [
                    { name: 'Cotton Classic Polo Shirt', quantity: 1, price: 35 },
                    { name: 'Straight Fit Denim Jeans', quantity: 1, price: 40 },
                ],
            },
        ],
    },
    {
        id: 2,
        name: 'Jane Smith',
        email: 'jane@example.com',
        phone: '987-654-3210',
        loyaltyPoints: 200,
        totalOrders: 10,
        totalSpent: 500,
        recentSpend: 1150,
        orders: [
            {
                id: 1,
                date: '2023-10-01 10:00 AM',
                payment: 'Cash',
                total: 50,
                status: 'Completed',
                items: [
                    {
                        name: 'Men Slim Fit Checkered Spread Collar Casual Shirt (Pack of 2)',
                        quantity: 2,
                        price: 20,
                    },
                    { name: 'Leather Wallet', quantity: 1, price: 10 },
                ],
            },
            {
                id: 2,
                date: '2023-10-08 04:15 PM',
                payment: 'UPI',
                total: 95,
                status: 'Completed',
                items: [
                    { name: 'Printed Kurti', quantity: 1, price: 45 },
                    { name: 'Casual Sneakers', quantity: 1, price: 50 },
                ],
            },
        ],
    },
    {
        id: 3,
        name: 'Alice Johnson',
        email: 'alice@example.com',
        phone: '555-555-5555',
        loyaltyPoints: 250,
        totalOrders: 12,
        totalSpent: 760,
        recentSpend: 1280,
        orders: [
            {
                id: 1,
                date: '2023-11-14 12:20 PM',
                payment: 'Cash',
                total: 120,
                status: 'Completed',
                items: [
                    { name: 'Women Formal Blazer', quantity: 1, price: 85 },
                    { name: 'Silk Scarf', quantity: 1, price: 35 },
                ],
            },
        ],
    },
    {
        id: 4,
        name: 'Bob Brown',
        email: 'bob@example.com',
        phone: '444-444-4444',
        loyaltyPoints: 300,
        totalOrders: 16,
        totalSpent: 900,
        recentSpend: 1490,
        orders: [
            {
                id: 1,
                date: '2023-12-04 09:45 AM',
                payment: 'Card',
                total: 65,
                status: 'Completed',
                items: [
                    { name: 'Running Shoes', quantity: 1, price: 60 },
                    { name: 'Socks', quantity: 1, price: 5 },
                ],
            },
        ],
    },
]

const currencyFormatter = new Intl.NumberFormat('en-US', {
    style: 'currency',
    currency: 'USD',
})

const CustomerLookup = () => {
    const [searchTerm, setSearchTerm] = useState('')
    const [selectedCustomerId, setSelectedCustomerId] = useState(2)

    const filteredCustomers = useMemo(() => {
        const normalizedSearch = searchTerm.trim().toLowerCase()

        if (!normalizedSearch) {
            return customers
        }

        return customers.filter((customer) => {
            return [customer.name, customer.email, customer.phone].some((value) =>
                value.toLowerCase().includes(normalizedSearch)
            )
        })
    }, [searchTerm])

    const selectedCustomer =
        customers.find((customer) => customer.id === selectedCustomerId) || customers[0]

    return (
        <main className='min-h-screen bg-white text-slate-950'>

            <section className='grid min-h-[calc(100vh-4.75rem)] grid-cols-1 lg:grid-cols-[26rem_1fr]'>
                <aside className='border-b border-slate-200 lg:border-b-0 lg:border-r'>
                    <div className='flex gap-3 border-b border-slate-200 p-4'>
                        <div className='relative min-w-0 flex-1'>
                            <Search className='absolute left-3 top-1/2 size-4 -translate-y-1/2 text-slate-400' />
                            <Input
                                value={searchTerm}
                                onChange={(event) => setSearchTerm(event.target.value)}
                                placeholder='Search Customers...'
                                className='h-10 rounded-lg border-slate-200 pl-10 text-sm shadow-none'
                            />
                        </div>
                        <Button className='h-10 gap-2 rounded-lg bg-emerald-950 px-4 text-sm font-semibold text-white hover:bg-emerald-900'>
                            <Plus className='size-4' />
                            Add New
                        </Button>
                    </div>

                    <div className='divide-y divide-slate-100'>
                        {filteredCustomers.map((customer) => {
                            const isSelected = customer.id === selectedCustomerId

                            return (
                                <button
                                    key={customer.id}
                                    type='button'
                                    onClick={() => setSelectedCustomerId(customer.id)}
                                    className={`flex w-full items-start justify-between gap-4 px-4 py-4 text-left transition-colors ${isSelected ? 'bg-slate-100' : 'bg-white hover:bg-slate-50'
                                        }`}
                                >
                                    <span className='min-w-0'>
                                        <span className='block truncate text-base font-bold text-slate-900'>
                                            {customer.name}
                                        </span>
                                        <span className='mt-1 block truncate text-sm text-slate-500'>
                                            {customer.email}
                                        </span>
                                        <span className='mt-1 block text-sm text-slate-500'>
                                            {customer.phone}
                                        </span>
                                    </span>

                                    <span className='mt-1 inline-flex h-6 shrink-0 items-center gap-1.5 rounded-lg bg-emerald-950 px-2.5 text-xs font-bold text-white'>
                                        <Star className='size-3.5 text-white' />
                                        {customer.loyaltyPoints} pts
                                    </span>
                                </button>
                            )
                        })}
                    </div>
                </aside>

                <section className='min-w-0 bg-white p-4'>
                    <div className='flex flex-col gap-4 sm:flex-row sm:items-start sm:justify-between'>
                        <div>
                            <h2 className='text-base font-bold text-slate-900'>{selectedCustomer.name}</h2>
                            <p className='mt-1 text-sm text-slate-500'>{selectedCustomer.email}</p>
                            <p className='text-sm text-slate-500'>{selectedCustomer.phone}</p>
                        </div>
                        <Button className='h-10 w-fit gap-2 rounded-lg bg-emerald-950 px-4 text-sm font-semibold text-white hover:bg-emerald-900'>
                            <Plus className='size-4' />
                            Add Points
                        </Button>
                    </div>

                    <div className='mt-6 grid grid-cols-1 gap-4 md:grid-cols-3'>
                        <SummaryCard label='Loyalty Points' value={selectedCustomer.loyaltyPoints} />
                        <SummaryCard label='Total Orders' value={selectedCustomer.totalOrders} />
                        <SummaryCard label='Total Spent' value={selectedCustomer.totalSpent} />
                    </div>

                    <div className='mt-5 rounded-2xl border border-slate-200 bg-white p-5 shadow-sm'>
                        <h3 className='text-base font-bold text-slate-900'>Purchase History</h3>
                        <p className='mt-7 text-xl font-bold text-slate-500'>
                            {selectedCustomer.recentSpend}$
                        </p>
                    </div>

                    <div className='mt-5 rounded-2xl border border-slate-200 bg-white p-5 shadow-sm'>
                        <h3 className='text-base font-bold text-slate-900'>Purchase History</h3>

                        <div className='mt-6 space-y-4'>
                            {selectedCustomer.orders.map((order) => (
                                <article
                                    key={order.id}
                                    className='rounded-2xl border border-slate-200 bg-white p-4'
                                >
                                    <div className='flex items-start justify-between gap-4'>
                                        <div>
                                            <h4 className='text-sm font-bold text-slate-900'>
                                                Order #{order.id}
                                            </h4>
                                            <p className='mt-2 flex items-center gap-2 text-sm text-slate-500'>
                                                <CalendarDays className='size-4' />
                                                {order.date}
                                            </p>
                                        </div>
                                        <div className='text-right'>
                                            <p className='text-sm font-semibold text-slate-600'>
                                                {currencyFormatter.format(order.total)}
                                            </p>
                                            <span className='mt-3 inline-flex h-6 items-center rounded-lg bg-emerald-950 px-2.5 text-xs font-bold text-white'>
                                                {order.status}
                                            </span>
                                        </div>
                                    </div>

                                    <p className='mt-6 text-sm text-slate-500'>
                                        Payment : {order.payment}
                                    </p>

                                    <div className='mt-4 border-t border-slate-100 pt-4'>
                                        <p className='text-sm font-bold text-slate-900'>Items:</p>
                                        <div className='mt-4 space-y-3'>
                                            {order.items.map((item) => (
                                                <div
                                                    key={item.name}
                                                    className='flex items-start justify-between gap-4 text-sm'
                                                >
                                                    <p className='min-w-0 text-slate-500'>{item.name}</p>
                                                    <p className='shrink-0 font-semibold text-slate-700'>
                                                        {item.quantity} * {item.price.toFixed(2)}
                                                    </p>
                                                </div>
                                            ))}
                                        </div>
                                    </div>
                                </article>
                            ))}
                        </div>
                    </div>
                </section>
            </section>
        </main>
    )
}

const SummaryCard = ({ label, value }) => {
    return (
        <div className='rounded-2xl border border-slate-200 bg-white p-5 shadow-sm'>
            <h3 className='text-base font-bold text-slate-900'>{label}</h3>
            <p className='mt-7 flex items-center gap-4 text-xl font-medium text-slate-600'>
                <Star className='size-5 text-yellow-400' />
                {value}
            </p>
        </div>
    )
}

export default CustomerLookup
