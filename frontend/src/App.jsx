import { useState } from 'react'

import { Button } from './components/ui/button'
import CreateOrder from './pages/cashier/CreateOrder'
import ShiftSummaryPage from './pages/cashier/Shift Report/ShiftSummaryPage'
import OrderHistory from './pages/cashier/Order History/OrderHistory'
import RefundPage from './pages/cashier/Refund/RefundPage'
import CashierRouters from './routes/CashierRouters'
import { Route, Routes } from 'react-router'

function App() {

  return (
    <div>
      <Routes>
        <Route path={"/cashier/*"} element={<CashierRouters />} />
        {/* <Route path={"/store/*"} element={<CashierRouters />} /> */}
        {/* <Route path={"/branch/*"} element={<CashierRouters />} /> */}
        {/* <Route path={"/super-admin/*"} element={<CashierRouters />} /> */}
        <Route path='/login' element={<Login />} />
      </Routes>


    </div>
  )
}

export default App
