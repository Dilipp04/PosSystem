import { Badge } from '@/components/ui/badge'
import { Button } from '@/components/ui/button'
import { ButtonGroup } from '@/components/ui/button-group'
import { Card, CardContent } from '@/components/ui/card'
import { MinusIcon, PlusIcon, Trash2Icon } from 'lucide-react'
import React from 'react'



const CartCard = ({ product }) => {
    return (
        <Card className="h-30 min-h-30 border-l-4 border-black">
            <CardContent className="flex items-center max-h-30 grow gap-4 justify-around">
                <div>
                    <p className='font-bold'>{product.product_name}</p>
                    <p className='text-muted-foreground text-xs'>{product.sku}</p>
                </div>

                <div className='flex gap-2 items-center '>
                    <Button variant="outline" size="icon">
                        <MinusIcon />
                    </Button>
                    <div className='font-semibold'>{product.quantity}</div>
                    <Button variant="outline" size="icon">
                        <PlusIcon />
                    </Button>
                </div>
                <div className='flex flex-col items-end'>
                    <span className='text-black font-semibold'> {product.price} </span>
                    <span className='text-green-700 font-semibold'> {product.price * product.quantity} </span>
                </div>
                <Trash2Icon size={18} />
            </CardContent>

        </Card>
    )
}

export default CartCard