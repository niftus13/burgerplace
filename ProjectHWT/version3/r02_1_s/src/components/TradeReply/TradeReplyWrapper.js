import { useEffect, useState } from "react";
import TradeReplyList from "./TradeReplyList"
import TradeReplyInput from "./TradeReplyInput"
import TradeReplyRead from "./TradeReplyRead";


// 상태값 초기화
const initState = {
    tradeBno: 0,
    page: 1,
    last: false,
    // 상태를 계속 바꾸게하기 위한 변수
    refresh: false,
    // read값의 cmd 작업을 위한 변수
    current: 0
}

// bno 1개가 propertities로 내려온다
const TradeReplyWrapper = ({ tradeBno }) => {

    // 상태 함수 설정
    const [data, setData] = useState(initState)

    // bno가 props니까 바뀔수있게사용
    // bno 및 last를 변경해준다.
    useEffect(() => {

        data.tradeBno = tradeBno
        data.last = true
        data.page = 1
        setData({ ...data })

    }, [tradeBno])

    // page를 바꿔주는 기능 설정 num 을 받아서 변경
    const movePage = (num) => {
        console.log(num)
        data.page = num
        // 변경이 안된다. why? last가 true값으로 고정되있기때문에 last를 false로 변경해 줘야된다.
        data.last = false
        data.refresh = !data.refresh
        setData({ ...data })
    }

    const refreshLast = () => {

        data.last = true
        data.refresh = !data.refresh
        setData({ ...data })
    }

    const changeCurrent = (tradeRno) => {
        data.current = tradeRno
        setData({ ...data })
    }
    const cancelRead = () => {
        data.current = 0
        setData({ ...data })
    }
    // 강제 리로딩 함수
    const refreshPage = (hide) => {
        data.refresh = !data.refresh
        if (hide) {
            data.current = 0
        }
        setData({ ...data })
    }

    return (
        <div>
            <TradeReplyList {...data} movePage={movePage} changeCurrent={changeCurrent}></TradeReplyList>
            {data.current !== 0 ? <TradeReplyRead
                tradeRno={data.current}
                refreshPage={refreshPage}
                cancelRead={cancelRead}></TradeReplyRead> : <></>}
                <TradeReplyInput tradeBno={tradeBno} refreshLast={refreshLast}></TradeReplyInput>
        </div>
    );
}

export default TradeReplyWrapper;