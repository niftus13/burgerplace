import { useEffect, useState } from "react";
import TradeReplyList from "./TradeReplyList";
import TradeReplyInput from "./TradeReplyInput";
import TradeReplyRead from "./TradeReplyRead";

const initState = {
    tBno: 0,
    page: 1,
    last: false,
    refresh: false,
    current:0
}

const TradeReplyWrapper = ({tBno}) => {

    const [data, setData] = useState(initState)

    useEffect(() => {

        data.tBno = tBno
        data.last = true
        data.page = 1
    
        setData({...data})

    }, [tBno])

    const changeCurrent = (tRno) => {

        data.current = tRno
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
            <TradeReplyInput tBno={tBno} refreshLast={refreshLast}></TradeReplyInput>
            {data.current != 0 ? <TradeReplyRead fRno={data.current} cancelRead={cancelRead} refreshPage={refreshPage}></TradeReplyRead>:<></>}
            <TradeReplyList {...data} movePage={movePage} changeCurrent={changeCurrent}></TradeReplyList>
        </div>
     );
}
 
export default TradeReplyWrapper;