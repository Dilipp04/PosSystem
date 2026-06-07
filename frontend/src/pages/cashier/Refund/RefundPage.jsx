import React, { useState } from 'react'
import OrderDetailsSection from './OrderDetailsSection'
import ReturnItemSection from './ReturnItemSection'
import OrderTable from './OrderTable'
import ReturnReceiptDialog from './ReturnReceiptDialog'

const RefundPage = () => {
    const [selectedOrder, setSelectedOrder] = useState(null);
    const handleSelectOrder = (order) => setSelectedOrder(order)

    return (
        <div className='h-full flex flex-col'>
            <div className='p-4 bg-card border-b'>
                <h1 className='text-2xl font-bold'>
                    Return/Refund
                </h1>

            </div>
            <div className=' overflow-hidden'>
                {!selectedOrder ?
                    (<OrderTable handleSelectOrder={handleSelectOrder} />) :

                    (<div className='flex'>
                        <OrderDetailsSection selectedOrder={selectedOrder} handleSelectOrder={handleSelectOrder} />
                        <ReturnItemSection />
                    </div>
                    )
                }
            </div>
            {/* {selectedOrder && <ReturnReceiptDialog />} */}
        </div>
    )
}

export default RefundPage