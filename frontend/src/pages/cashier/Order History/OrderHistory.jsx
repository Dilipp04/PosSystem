import React, { useState } from 'react'
import OrderTable from './OrderTable'
import { Dialog, DialogContent, DialogFooter, DialogHeader, DialogTitle } from '@/components/ui/dialog'
import OrderDetails from './OrderDetails/OrderDetails'
import { Button } from '@/components/ui/button'
import { Download } from 'lucide-react'

const OrderHistory = () => {
    const [showOrderInvoiceDialog, setShowOrderInvoiceDialog] = useState(false)
    const [selectedOrder, setSelectedOrder] = useState(null)

    const handleViewOrderDetails = (order) => {
        setSelectedOrder(order)
        setShowOrderInvoiceDialog(true)
    }

    return (
        <div className='h-full flex  flex-col'>
            <div className='flex p-4 overflow-auto'>
                <OrderTable handleViewOrderDetails={handleViewOrderDetails} />
            </div>
            <Dialog open={showOrderInvoiceDialog} onOpenChange={setShowOrderInvoiceDialog}>
                <DialogContent className="max-w-4xl">
                    <DialogHeader>
                        <DialogTitle>
                            Order - Invoice
                        </DialogTitle>
                    </DialogHeader>
                    <OrderDetails selectedOrder={selectedOrder} />
                    <DialogFooter>
                        <Button>
                            <Download />
                            Download PDF
                        </Button>
                    </DialogFooter>
                </DialogContent>
            </Dialog>
        </div>
    )
}

export default OrderHistory