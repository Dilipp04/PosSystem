import { Button } from '@/components/ui/button'
import { Card, CardContent } from '@/components/ui/card'
import { Label } from '@/components/ui/label'
import { Select, SelectContent, SelectGroup, SelectItem, SelectLabel, SelectTrigger, SelectValue } from '@/components/ui/select'
import { Textarea } from '@/components/ui/textarea'
import React, { useState } from 'react'

const reasons = [
    "Wrong Product",
    "Damage Product",
    "Not needed Any More"
    , "other"
]
const methods = ["UPI", "CASH", "CARD"]

const ReturnItemSection = ({ selectedOrder, setShowReturnReceiptDialog }) => {
    const [returnReason, setReturnReason] = useState("")
    const [returnMethod, setReturnMethod] = useState("")
    const [otherReason, setOtherReason] = useState("")


    const processRefund = () => {
        setShowReturnReceiptDialog(true)
    }
    return (
        <div className='p-4 w-1/2 '>
            <Card>
                <CardContent className="p-4">
                    <div className='space-y-4 '>
                        <div >
                            <Label className={"mb-2 block"}>Return Reason</Label>
                            <Select
                                value={returnReason}
                                onValueChange={(value) => setReturnReason(value)}>
                                <SelectTrigger className={"w-full"}>
                                    <SelectValue placeholder="Select A Reason..." />
                                </SelectTrigger>
                                <SelectContent >
                                    <SelectGroup >
                                        <SelectLabel>Select the Reason</SelectLabel>
                                        {reasons.map((reason, i) => {
                                            return (
                                                <SelectItem key={i} value={reason}>{reason}</SelectItem>
                                            )
                                        })}

                                    </SelectGroup>
                                </SelectContent>
                            </Select>

                        </div>
                        {
                            returnReason == "other" && (<div>
                                <Label className={"mb-2 block"}>Specify Reason</Label>
                                <Textarea id="other-reason"
                                    placeholder="Please Specify the Return Reason"
                                    value={otherReason}
                                    onChange={(e) =>
                                        setOtherReason(e.target.value)
                                    }
                                />

                            </div>)
                        }
                        <div >
                            <Label className={"mb-2 block"}>Refunds Method</Label>
                            <Select
                                value={returnMethod}
                                onValueChange={(value) => setReturnMethod(value)}>
                                <SelectTrigger className={"w-full"} >
                                    <SelectValue placeholder="Select Method..." />
                                </SelectTrigger>
                                <SelectContent >
                                    <SelectGroup >
                                        <SelectLabel>Select Method</SelectLabel>
                                        {methods.map((method, i) => {
                                            return (
                                                <SelectItem key={i} value={method}>{method}</SelectItem>
                                            )
                                        })}

                                    </SelectGroup>
                                </SelectContent>
                            </Select>

                        </div>

                        <div className='pt-4 border-t'>
                            <h1 className='text-bold text-md'>Total Refund Amount  : {selectedOrder.totalAmount}</h1>
                            <Button onClick={processRefund} className={"w-full py-6 mt-5"}> Process Refund </Button>
                        </div>

                    </div>
                </CardContent>
            </Card>
        </div>
    )
}

export default ReturnItemSection