import { Card, CardContent } from '@/components/ui/card'
import React from 'react'

const TopSellingItems = () => {
  const shiftData = {
    topSellingProducts: [

      {
        id: 1,
        name: "Men Geomatric Print Polo Next Cotton Blact T-shirt",
        sellingPrice: 899,
        quanity: 5
      },
      {
        id: 2,
        name: "Women Geomatric Print Polo Next Cotton Blact T-shirt",
        sellingPrice: 899,
        quanity: 9
      }
    ]
  }
  return (
    <Card>
      <CardContent className={"p-5"}>
        <h2 className='text-xl font-semibold mb-4'>Top Selling Items</h2>
        <div className='sapce-y-3'>
          {
            shiftData.topSellingProducts.map((product, index) =>
              <div key={product.id} className=' flex items-center'>
                <div className='w-6 h-6 rounded-full  bg-primary/10 flex items-center justify-center mr-3 text-sm font-medium'>
                  {index + 1}
                </div>
                <div className='flex-1 '>
                  <div className='flex justify-between'>
                    <span>{product.name}</span>
                    <span>Rs. {product.sellingPrice}</span>

                  </div>
                  <div className='flex justify-between text-sm text-muted-foreground'>
                    <span>{product.quanity} units sold</span>
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

export default TopSellingItems