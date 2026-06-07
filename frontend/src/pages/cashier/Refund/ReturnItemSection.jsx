import { Card, CardContent } from '@/components/ui/card'
import { Label } from '@/components/ui/label'
import React from 'react'

const ReturnItemSection = () => {
    return (
        <div className='p-4 w-1/2 '>
            <Card>
                <CardContent className="p-4">
                    <div className='space-y-4'>
                        <div>
                            <Label>
                                Return Reason
                            </Label>
                        </div>
                    </div>
                </CardContent>
            </Card>
        </div>
    )
}

export default ReturnItemSection