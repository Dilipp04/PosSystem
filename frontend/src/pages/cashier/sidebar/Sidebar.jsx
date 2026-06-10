import { Button } from '@/components/ui/button'
import {
    BarChart3,
    History,
    LogOut,
    ReceiptText,
    RotateCcw,
    ShoppingCart,
    Users,
} from 'lucide-react'
import React from 'react'
import { NavLink } from 'react-router'

const cashierNavItems = [
    {
        label: 'Create Order',
        path: '/cashier/create',
        icon: ShoppingCart,
    },
    {
        label: 'Order History',
        path: '/cashier/orders',
        icon: History,
    },
    {
        label: 'Customers',
        path: '/cashier/customers',
        icon: Users,
    },
    {
        label: 'Returns',
        path: '/cashier/returns',
        icon: RotateCcw,
    },
    {
        label: 'Shift Summary',
        path: '/cashier/shift-summary',
        icon: BarChart3,
    },
]

const Sidebar = ({ navItems = cashierNavItems }) => {
    return (
        <aside className='flex h-screen w-72 flex-col border-r border-slate-200 bg-white'>
            <div className='flex h-20 items-center border-b border-slate-200 px-5'>
                <div className='flex items-center gap-3'>
                    <div className='flex size-11 items-center justify-center rounded-2xl bg-emerald-950 text-white'>
                        <ReceiptText className='size-5' />
                    </div>
                    <div>
                        <h1 className='text-lg font-extrabold leading-tight text-slate-950'>POS</h1>
                        <p className='text-xs text-muted-foreground'>Cashier Panel</p>
                    </div>
                </div>
            </div>

            <nav className='flex-1 space-y-1 overflow-y-auto p-4'>
                {navItems.map((item) => {
                    const Icon = item.icon

                    return (
                        <NavLink
                            key={item.path}
                            to={item.path}
                            end={item.path === '/'}
                            className={({ isActive }) =>
                                `flex h-11 items-center gap-3 rounded-2xl px-4 text-sm font-semibold transition-colors ${isActive
                                    ? 'bg-emerald-950 text-white shadow-sm'
                                    : 'text-slate-600 hover:bg-slate-100 hover:text-slate-950'
                                }`
                            }
                        >
                            {Icon && <Icon className='size-4' />}
                            <span>{item.label}</span>
                        </NavLink>
                    )
                })}
            </nav>

            <div className='border-t border-slate-200 p-4'>
                <Button
                    variant='secondary'
                    className='h-11 w-full justify-start gap-3 rounded-2xl px-4 text-sm font-semibold'
                >
                    <LogOut className='size-4' />
                    Logout
                </Button>
            </div>
        </aside>
    )
}

export { cashierNavItems }
export default Sidebar
