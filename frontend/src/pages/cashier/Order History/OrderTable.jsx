import { Button } from '@/components/ui/button'
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from '@/components/ui/table'
import { EyeIcon, Printer, Repeat } from 'lucide-react'
import React from 'react'

const OrderTable = ({ handleViewOrderDetails }) => {
    const orders = [
        {
            id: 1,
            createdAt: "12:59 PM",
            customer: {
                fullName: "Dilip prajapati",
                phone: 9152537335,

            },
            totalAmount: 24999,
            status: "COMPLETED",
            paymentType: "CASH",
            items: [
                {
                    id: 1,
                    product: {
                        name: "White T-Shirt",
                        image: "https://cdn.pixabay.com/photo/2024/02/06/18/10/ai-generated-8557635_1280.jpg",
                        sellingPrice: 499,
                        sku: "SHRT-S-COTTON-BLU-2025"
                    },
                    quantity: 2,
                },

            ]
        }
    ]
    return (
        <div className='w-full p-4'>
            <Table>
                <TableHeader>
                    <TableRow>
                        <TableHead className="w-[150x]">Order ID</TableHead>
                        <TableHead className="w-[150x]">Date/Time</TableHead>
                        <TableHead className="w-[150x]">Customer</TableHead>
                        <TableHead className="w-[150x]">Amount</TableHead>
                        <TableHead className="w-[150x]">Payment Mode</TableHead>
                        <TableHead className="w-[150x]">Status</TableHead>
                        <TableHead className="w-[150x]">Actions</TableHead>
                    </TableRow>
                </TableHeader>
                <TableBody>
                    {
                        orders.map((order) => (
                            <TableRow key={order.id}>
                                <TableCell>{order.id}</TableCell>
                                <TableCell>{order.createdAt}</TableCell>
                                <TableCell>{order.customer.fullName}</TableCell>
                                <TableCell>{order.totalAmount}</TableCell>
                                <TableCell>{order.paymentType}</TableCell>
                                <TableCell>{order.status}</TableCell>
                                <TableCell className="text-right">
                                    <div className='flex justiy-end gap-2'>
                                        <Button onClick={
                                            () => handleViewOrderDetails(order)
                                        } variant={"ghost"} size='icon'>
                                            <EyeIcon className="w-4 h-4" />
                                        </Button>
                                        <Button variant={"ghost"} size='icon'>
                                            <Printer className="w-4 h-4" />
                                        </Button>
                                        <Button variant={"ghost"} size='icon'>
                                            <Repeat className="w-4 h-4" />
                                        </Button>
                                    </div>
                                </TableCell>
                            </TableRow>
                        ))
                    }

                </TableBody>
            </Table>



        </div>
    )
}

export default OrderTable