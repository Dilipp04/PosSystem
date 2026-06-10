import React, { useState } from 'react'
import OrderDetailsSection from './OrderDetailsSection'
import ReturnItemSection from './ReturnItemSection'
import OrderTable from './OrderTable'
import ReturnReceiptDialog from './ReturnReceiptDialog'

const RefundPage = () => {
    const [selectedOrder, setSelectedOrder] = useState(null);
    const [showReturnReceiptDialog, setShowReturnReceiptDialog] = useState(false)
    const handleSelectOrder = (order) => setSelectedOrder(order)

    return (
        <div className='h-full flex flex-col'>

            <div className=' overflow-hidden'>
                {!selectedOrder ?
                    (<OrderTable handleSelectOrder={handleSelectOrder} />) :

                    (<div className='flex'>
                        <OrderDetailsSection selectedOrder={selectedOrder} handleSelectOrder={handleSelectOrder} />
                        <ReturnItemSection selectedOrder={selectedOrder} setShowReturnReceiptDialog={setShowReturnReceiptDialog} />
                    </div>
                    )
                }
            </div>
            {showReturnReceiptDialog && <ReturnReceiptDialog selectedOrder={selectedOrder} setShowReturnReceiptDialog={setShowReturnReceiptDialog} showReturnReceiptDialog={showReturnReceiptDialog} />}
        </div>
    )
}

export default RefundPage