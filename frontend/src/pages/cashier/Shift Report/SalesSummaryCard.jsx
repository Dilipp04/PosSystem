import { Card, CardContent } from '@/components/ui/card'
import React from 'react'

const SalesSummaryCard = () => {

  const shiftData = {
    totalOrders: 59,
    totalSales: 69999,
    totalRefunds: 21999,
    netSales: 48000
  }

  return (
    <Card>
      <CardContent>
        <h2 className='text-cl font-semibold mb-4'>
          Sales Summary
        </h2>
        <div className='space-y-2'>
          <div className='flex justify-between'>
            <span className='text-muted-foreground'>Total Orders : </span>
            <span className='font-medium'>{shiftData.totalOrders}</span>
          </div>
          <div className='flex justify-between'>
            <span className='text-muted-foreground'>Total Sales : </span>
            <span className='font-medium'>{shiftData.totalSales}</span>
          </div>
          <div className='flex justify-between'>
            <span className='text-muted-foreground'>Total Refunds : </span>
            <span className='font-medium text-red-500'>{shiftData.totalRefunds}</span>
          </div>
          <div className='flex justify-between border-t'>
            <span className='text-muted-foreground'>Net Sales : </span>
            <span className='font-medium'>{shiftData.netSales}</span>
          </div>
        </div>
      </CardContent>
    </Card>
  )
}

export default SalesSummaryCard