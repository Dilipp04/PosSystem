import { Button } from '@/components/ui/button'
import { MenuIcon, X } from 'lucide-react'
import React, { useMemo, useState } from 'react'
import { useLocation } from 'react-router'
import Sidebar from './sidebar/Sidebar'

const pageTitles = {
    '/': {
        title: 'POS Terminal',
        subtitle: 'Create new Order',
    },
    '/orders': {
        title: 'Order History',
        subtitle: 'View and manage orders',
    },
    '/customers': {
        title: 'Customer Management',
        subtitle: 'Lookup customers and loyalty points',
    },
    '/returns': {
        title: 'Return/Refund',
        subtitle: 'Process order returns',
    },
    '/shift-summary': {
        title: 'Shift Summary',
        subtitle: 'Review cashier shift activity',
    },
}

const CashierDashboardLayout = ({ children }) => {
    const [isSidebarOpen, setIsSidebarOpen] = useState(false)
    const location = useLocation()

    const pageTitle = useMemo(() => {
        return pageTitles[location.pathname] || pageTitles['/']
    }, [location.pathname])

    const handleSidebarClick = (event) => {
        if (event.target.closest('a')) {
            setIsSidebarOpen(false)
        }
    }

    return (
        <div className='min-h-screen bg-slate-50'>
            <div
                className={`fixed inset-0 z-40 bg-slate-950/40 transition-opacity ${
                    isSidebarOpen ? 'opacity-100' : 'pointer-events-none opacity-0'
                }`}
                onClick={() => setIsSidebarOpen(false)}
            />

            <div
                className={`fixed left-0 top-0 z-50 h-screen transition-transform duration-300 ease-out ${
                    isSidebarOpen ? 'translate-x-0' : '-translate-x-full'
                }`}
                onClick={handleSidebarClick}
            >
                <Button
                    type='button'
                    variant='ghost'
                    size='icon'
                    className='absolute right-3 top-5 z-10 h-9 w-9 rounded-xl bg-white'
                    onClick={() => setIsSidebarOpen(false)}
                    aria-label='Close sidebar'
                >
                    <X className='size-4' />
                </Button>
                <Sidebar />
            </div>

            <header className='flex h-20 w-full items-center justify-between px-5'>
                <Button
                    variant='ghost'
                    className='h-11 w-11 rounded-xl'
                    onClick={() => setIsSidebarOpen(true)}
                    aria-label='Open sidebar'
                >
                    <MenuIcon />
                </Button>

                <div className='text-center'>
                    <h1 className='text-2xl font-extrabold'>{pageTitle.title}</h1>
                    <p className='text-xs text-muted-foreground'>{pageTitle.subtitle}</p>
                </div>

                <img
                    className='h-11 w-11 rounded-full border border-slate-200 object-cover'
                    src='https://static.vecteezy.com/system/resources/previews/002/318/271/original/user-profile-icon-free-vector.jpg'
                    alt='profile'
                />
            </header>

            <div className='h-[calc(100vh-5rem)] overflow-auto'>{children}</div>
        </div>
    )
}

export default CashierDashboardLayout
