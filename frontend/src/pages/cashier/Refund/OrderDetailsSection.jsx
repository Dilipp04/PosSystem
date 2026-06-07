import { Badge } from '@/components/ui/badge'
import { Button } from '@/components/ui/button'
import { Card, CardContent } from '@/components/ui/card'
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from '@/components/ui/table'

import { ChevronLeftIcon } from 'lucide-react'
import React from 'react'

const OrderDetailsSection = ({ handleSelectOrder, selectedOrder }) => {
    return (
        <div className='w-1/2 border-r p-4' >
            <div className='mb-4'>
                <Button onClick={() => handleSelectOrder(null)}>
                    <ChevronLeftIcon />  Back to order table
                </Button>
            </div>

            <Card>
                <CardContent>
                    <div className='flex justify-between mb-4'>
                        <div >
                            <h2>Order {selectedOrder.id}</h2>
                            <p className='text-xs'>{selectedOrder.createdAt}</p>
                        </div>
                        <Badge variant='outline'>
                            {selectedOrder.paymentType}
                        </Badge>
                    </div>

                    <div className='mb-4'>
                        <h3 className='font-medium text-sm text-muted-foreground mb-2'>
                            {selectedOrder?.customer?.phone}
                        </h3>
                        <h2 className='text-sm'>
                            {selectedOrder?.customer?.fullName}
                        </h2>
                    </div>
                    <div className='text-sm'>
                        <h2 className='flex justify-between'>Order Summary</h2>
                        <div>
                            <span>Total Items : {selectedOrder.items.length}</span>
                        </div>
                        <div>
                            <span>Order Total : {selectedOrder.totalAmount}</span>
                        </div>
                    </div>
                </CardContent>
            </Card>

            <div className='flex-1 overflow-auto mt-5'>
                <h2>Order Items</h2>
                <Table className="text-xs">
                    <TableHeader>
                        <TableRow>
                            <TableHead className="w-[150x]">Image</TableHead>
                            <TableHead className="w-[150x]">Item</TableHead>
                            <TableHead className="w-[150x]">Quantity</TableHead>
                            <TableHead className="text-right">Price</TableHead>
                            <TableHead className="text-right">Total</TableHead>
                        </TableRow>
                    </TableHeader>
                    <TableBody>
                        {
                            selectedOrder.items.map((item) => (
                                <TableRow key={item.id}>
                                    <TableCell>
                                        <div className='w-10 h-10'>
                                            {
                                                item.product?.image &&
                                                <img src={item.product?.image}
                                                    className='w-10 h-10 object-cover rounded-md' />
                                            }
                                        </div>
                                    </TableCell>
                                    <TableCell>
                                        <div className='flex flex-col'>
                                            <span>{item.product.name.slice(0, 20)}...</span>
                                            <span className='text-xs text-gray-500'>SKU : {item.product?.sku}</span>
                                        </div>
                                    </TableCell>
                                    <TableCell>{item.quantity}</TableCell>
                                    <TableCell>{item.product?.sellingPrice}</TableCell>
                                    <TableCell className="text-right">
                                        {(item.product?.sellingPrice * item.quantity)?.toFixed(2)}
                                    </TableCell>
                                </TableRow>
                            ))
                        }

                    </TableBody>
                </Table>

            </div>


        </div>
    )
}

export default OrderDetailsSection