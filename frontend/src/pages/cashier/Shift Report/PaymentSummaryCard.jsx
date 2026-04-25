import { Card, CardContent } from '@/components/ui/card'
import { CreditCard } from 'lucide-react'
import React from 'react'

const PaymentSummaryCard = () => {
  const shiftData = {
    totalSales: 69999,
    paymentSummaries: [
      {
        type: "CASH",
        totalAmount: 2999,
        transactionCount: 6
      },
      {
        type: "UPI",
        totalAmount: 4999,
        transactionCount: 3
      },
      {
        type: "CARD",
        totalAmount: 3499,
        transactionCount: 6
      }

    ]
  }
  return (
    <Card>
      <CardContent className={"p-5"}>
        <h2 className='text-xl font-semibold mb-4'>
          Payment Summary
        </h2>
        <div className='space-y-3'>
          {
            shiftData.paymentSummaries.map((payment) =>
              <div className='flex items-center' key={payment.type}>

                <div className='w-10 h-10 rounded-full bg-primary/10 flex items-center justify-center mr-4'>
                  <CreditCard />
                </div>
                <div className='flex-1'>
                  <div className='flex justify-between'>
                    <span className='font-medium'>{payment.type}</span>
                    <span className=''>{payment.totalAmount}</span>
                  </div>
                  <div className='flex justify-between text-sm text-muted-foreground'>
                    <span>{payment.transactionCount} transactions</span>
                    <span>{((payment.totalAmount / shiftData.totalSales) * 100).toFixed(1)} %</span>
                  </div>
                </div>
              </div>
            )
          }
        </div>
      </CardContent>
    </Card>
  )
}

export default PaymentSummaryCard