import { useState } from 'react'

import './App.css'
import { Button } from './components/ui/button'
import CreateOrder from './pages/cashier/CreateOrder'
import ShiftSummaryPage from './pages/cashier/Shift Report/ShiftSummaryPage'

function App() {

  return (
    <div>

      {/* <CreateOrder/> */}
      {/* <CustomerLoopup/> */}
      <ShiftSummaryPage />
    </div>
  )
}

export default App
