import CreateOrder from '@/pages/cashier/CreateOrder'
import CashierDashboardLayout from '@/pages/cashier/CashierDashboardLayout'
import CustomerLookup from '@/pages/cashier/Customer Management/CustomerLookup'
import OrderHistory from '@/pages/cashier/Order History/OrderHistory'
import RefundPage from '@/pages/cashier/Refund/RefundPage'
import ShiftSummaryPage from '@/pages/cashier/Shift Report/ShiftSummaryPage'
import React from 'react'
import { Route, Routes } from 'react-router'

const CashierRouters = () => {
    return (
        <CashierDashboardLayout>
            <Routes>
                <Route path='/create' element={<CreateOrder />} />
                <Route path='/orders' element={<OrderHistory />} />
                <Route path='/customers' element={<CustomerLookup />} />
                <Route path='/returns' element={<RefundPage />} />
                <Route path='/shift-summary' element={<ShiftSummaryPage />} />
            </Routes>
        </CashierDashboardLayout>
    )
}

export default CashierRouters
