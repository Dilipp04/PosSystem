import { Card, CardContent } from '@/components/ui/card'
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from '@/components/ui/table'
import React from 'react'

const OrderItemTable = ({ selectedOrder }) => {
    return (
        <Card className="col-span-2">
            <CardContent className={"p-4"}>
                <h2 className='font-semibold mb-2'>Recent Orders</h2>
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

            </CardContent>

        </Card>)
}

export default OrderItemTable