import { Button } from '@/components/ui/button'
import { ArrowRight } from 'lucide-react'
import React from 'react'

const ShiftReportHeader = () => {
  return (
    <div className='px-4 bg-gray flex justify-end'>
      <Button variant={"destructive"}>
        <ArrowRight />
        End Shift & Logout
      </Button>

    </div>
  )
}

export default ShiftReportHeader