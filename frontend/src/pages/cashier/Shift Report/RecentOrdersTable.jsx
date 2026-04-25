import { Card, CardContent } from '@/components/ui/card'
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from '@/components/ui/table'
import React from 'react'

const RecentOrdersTable = () => {

  const shiftData = {
    recentOrders: [
      {
        id: 1,
        createdAt: "01:25 PM",
        paymentType: "CASH",
        totalAmount: 7899
      },
      {
        id: 2,
        createdAt: "01:25 PM",
        paymentType: "CASH",
        totalAmount: 7899
      },
    ]
  }
  return (
    <Card>
      <CardContent>
        <h2 className='text-xl font-semibold mb-4'>Recent Orders</h2>
        <Table>
          <TableHeader>
            <TableRow>
              <TableHead className="w-[150x]">Order Id</TableHead>
              <TableHead className="w-[150x]">Time</TableHead>
              <TableHead className="w-[150x]">Payment</TableHead>
              <TableHead className="text-right">Amount</TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            {
              shiftData.recentOrders.map((order) => (
                <TableRow key={order.id}>
                  <TableCell>{order.id}</TableCell>
                  <TableCell>{order.createdAt}</TableCell>
                  <TableCell>{order.paymentType}</TableCell>
                  <TableCell className="text-right">{order.totalAmount}</TableCell>
                </TableRow>
              ))
            }

          </TableBody>
        </Table>

      </CardContent>

    </Card>
  )
}

export default RecentOrdersTable