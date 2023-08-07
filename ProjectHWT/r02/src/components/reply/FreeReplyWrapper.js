import { useEffect, useState } from "react";
import FreeReplyInput from "./FreeReplyInput";
import FreeReplyList from "./FreeReplyList";
import FreeReplyRead from "./FreeReplyRead"

const initState = {
    fBno: 0,
    page: 1,
    last: false,
    refresh: false,
    current:0
}

const FreeReplyWrapper = ({fBno}) => {

    const [data, setData] = useState(initState)

    useEffect(() => {

        data.fBno = fBno
        data.last = true
        data.page = 1
    
        setData({...data})

    }, [fBno])

    const changeCurrent = (fRno) => {

        data.current = fRno
        setData({...data})
    }

    const movePage = (num) => {

        console.log("num...." + num)

        data.page = num
        data.last = false
        data.refresh = !data.refresh

        setData({...data})
    }

    const refreshLast = () => {
        data.last = true
        data.refresh = !data.refresh
        setData({...data})
    }

    const cancelRead = () => {
        
        data.current = 0
        setData({...data})
    }

    const refreshPage = (hide) => {
        data.refresh = !data.refresh

        if(hide) {
            data.current = 0
        }
        
        setData({...data})
    }

    return ( 
        <div>
            <FreeReplyInput fBno={fBno} refreshLast={refreshLast}></FreeReplyInput>
            {data.current != 0 ? <FreeReplyRead fRno={data.current} cancelRead={cancelRead} refreshPage={refreshPage}></FreeReplyRead>:<></>}
            <FreeReplyList {...data} movePage={movePage} changeCurrent={changeCurrent}></FreeReplyList>
        </div>
     );
}
 
export default FreeReplyWrapper;