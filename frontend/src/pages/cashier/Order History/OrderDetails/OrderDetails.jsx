import React from 'react'
import OrderHistory from '../OrderHistory'
import OrderInformation from './OrderInformation'
import OrderItemTable from './OrderItemTable'
import CustomerInformation from './CustomerInformation'

const OrderDetails = ({ selectedOrder }) => {
    return (
        <div>
            <div className='grid grid-cols-2 gap-3 mb-4'>
                <OrderInformation selectedOrder={selectedOrder} />
                <CustomerInformation selectedOrder={selectedOrder} />
                <OrderItemTable selectedOrder={selectedOrder} />
            </div>
        </div>
    )
}

export default OrderDetails