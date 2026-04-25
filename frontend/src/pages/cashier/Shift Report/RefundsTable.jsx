import { Card, CardContent } from '@/components/ui/card'
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from '@/components/ui/table'
import React from 'react'
const RefundsTable = () => {
  const shiftData = {
    refunds: [
      {
        id: 234,
        orderId: 2,
        reason: "wrrong product received",
        amount: 699
      }
    ]
  }
  return (
    <Card>
      <CardContent>
        <h2 className='text-xl font-semibold mb-4'>Recent Orders</h2>
        <Table>
          <TableHeader>
            <TableRow>
              <TableHead className="w-[150x]">Refund Id</TableHead>
              <TableHead className="w-[150x]">Order ID</TableHead>
              <TableHead className="w-[150x]">Reason</TableHead>
              <TableHead className="text-right">Amount</TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            {
              shiftData.refunds .map((refund) => (
                <TableRow key={refund.id}>
                  <TableCell>RFD - {refund.id}</TableCell>
                  <TableCell>ORD - {refund.orderId}</TableCell>
                  <TableCell>{refund.reason}</TableCell>
                  <TableCell className="text-right">{refund.amount}</TableCell>
                </TableRow>
              ))
            }

          </TableBody>
        </Table>

      </CardContent>

    </Card>
  )
}

export default RefundsTable