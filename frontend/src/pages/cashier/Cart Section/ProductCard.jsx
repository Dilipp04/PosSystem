import { Badge } from '@/components/ui/badge'
import { Card, CardContent } from '@/components/ui/card'
import React from 'react'



const ProductCard = ({ product }) => {
    return (
        <Card className="h-56 ">
            <CardContent>
                <img src={product?.image} alt="" />
                <div>
                    <h3 className='font-bold'>{product.name}</h3>
                    <p className='text-muted-foreground text-xs'>{product.sku}</p>
                    <div className='flex justify-between'>
                        <span className='text-green-700 font-semibold'> {product.price} $</span>
                        <Badge variant="secondary">{product.category}</Badge>
                    </div>
                </div>
            </CardContent>

        </Card>
    )
}

export default ProductCard