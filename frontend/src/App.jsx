import { useState } from 'react'

import './App.css'
import { Button } from './components/ui/button'
import CreateOrder from './pages/cashier/CreateOrder'
import ShiftSummaryPage from './pages/cashier/Shift Report/ShiftSummaryPage'
import OrderHistory from './pages/cashier/Order History/OrderHistory'
import RefundPage from './pages/cashier/Refund/RefundPage'

function App() {

  return (
    <div>

      {/* <CreateOrder/> */}
      {/* <CustomerLoopup/> */}
      {/* <ShiftSummaryPage /> */}
      {/* <OrderHistory /> */}
      <RefundPage />
    </div>
  )
}

export default App
