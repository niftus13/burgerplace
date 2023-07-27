import { useEffect, useState } from "react";
import ReplyList from "./ReplyList";
import ReplyInput from "./ReplyInput";
import ReplyRead from "./ReplyRead";

const initState = {
    bno: 0,
    page: 1,
    last: false,
    refresh: false,
    current:0
}

const ReplyWrapper = ({bno}) => {

    const [data, setData] = useState(initState)

    useEffect(() => {

        data.bno = bno
        data.last = true
        data.page = 1
    
        setData({...data})

    }, [bno])

    const changeCurrent = (rno) => {

        data.current = rno
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
            <ReplyInput bno={bno} refreshLast={refreshLast}></ReplyInput>
            {data.current != 0 ? <ReplyRead rno={data.current} cancelRead={cancelRead} refreshPage={refreshPage}></ReplyRead>:<></>}
            <ReplyList {...data} movePage={movePage} changeCurrent={changeCurrent}></ReplyList>
        </div>
     );
}
 
export default ReplyWrapper;