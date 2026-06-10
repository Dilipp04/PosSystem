import { Button } from '@/components/ui/button'
import { Dialog, DialogContent, DialogFooter, DialogHeader, DialogTitle } from '@/components/ui/dialog'
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from '@/components/ui/table'
import { PrinterIcon } from 'lucide-react'
import React from 'react'

const ReturnReceiptDialog = ({ selectedOrder, showReturnReceiptDialog, setShowReturnReceiptDialog }) => {
    return (
        <div>
            <Dialog open={showReturnReceiptDialog} onOpenChange={setShowReturnReceiptDialog}>
                <DialogContent className="max-w-4xl">
                    <DialogHeader>
                        <DialogTitle>
                            Return Receipt
                        </DialogTitle>
                    </DialogHeader>

                    <div className='bg-background max-h-96 overflow-y-auto '>
                        <div className='mb-4'>
                            <h3 className='font-bold text-lg '>POS SYSTEM</h3>
                            <p>123, main street , city</p>
                            <p>Tel : 123-456-7890</p>
                        </div>
                        <div>
                            <h4 className='text-center font-bold'>Return Receipt</h4>
                            <div>
                                <p>Return #: RTN - {Date.now().toString().substring(8)}</p>
                                <p>Original Order : {selectedOrder.id}</p>
                                <p>Date : {new Date().toLocaleString()}</p>
                                <p>Customer : {selectedOrder?.customer?.fullName}</p>
                            </div>
                            <Table className="text-xs">
                                <TableHeader>
                                    <TableRow>
                                        <TableHead className="w-[150x]">Image</TableHead>
                                        <TableHead className="w-[150x]">Item</TableHead>
                                        <TableHead className="w-[150x]">Quantity</TableHead>
                                        <TableHead className="text-right">Price</TableHead>
                                        <TableHead className="text-right">Total</TableHead>
                                    </TableRow>
                                </TableHeader>
                                <TableBody>
                                    {
                                        selectedOrder.items.map((item) => (
                                            <TableRow key={item.id}>
                                                <TableCell>
                                                    <div className='w-10 h-10'>
                                                        {
                                                            item.product?.image &&
                                                            <img src={item.product?.image}
                                                                className='w-10 h-10 object-cover rounded-md' />
                                                        }
                                                    </div>
                                                </TableCell>
                                                <TableCell>
                                                    <div className='flex flex-col'>
                                                        <span>{item.product.name.slice(0, 20)}...</span>
                                                        <span className='text-xs text-gray-500'>SKU : {item.product?.sku}</span>
                                                    </div>
                                                </TableCell>
                                                <TableCell>{item.quantity}</TableCell>
                                                <TableCell>{item.product?.sellingPrice}</TableCell>
                                                <TableCell className="text-right">
                                                    {(item.product?.sellingPrice * item.quantity)?.toFixed(2)}
                                                </TableCell>
                                            </TableRow>
                                        ))
                                    }

                                </TableBody>
                            </Table>

                        </div>

                    </div>
                    <DialogFooter>
                        <Button>
                            <PrinterIcon />
                            Print & Complete
                        </Button>
                    </DialogFooter>
                </DialogContent>
            </Dialog>
        </div>
    )
}

export default ReturnReceiptDialog