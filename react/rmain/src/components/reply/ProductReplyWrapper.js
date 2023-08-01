import { useEffect, useState } from "react";
import ProductReplyInput from "./ProductReplyInput";
import ProductReplyRead from "./ProductReplyRead";
import ProductReplyList from "./ProductReplyList";

// 상태값 초기화
const initState = {
    pno: 0,
    page: 1,
    last: false,
    refresh: false,
    current: 0
}

// bno 1개가 propertities로 내려온다
const ProductReplyWrapper = ({ pno }) => {

    // 상태 함수 설정
    const [data, setData] = useState(initState)

    // bno가 props니까 바뀔수있게사용
    // bno 및 last를 변경해준다.
    useEffect(() => {

        data.pno = pno
        data.last = true
        data.page = 1
        setData({ ...data })

    }, [pno])

    const changeCurrent = (pRno) => {
        data.current = pRno
        setData({ ...data })
    }

    // page를 바꿔주는 기능 설정 num 을 받아서 변경
    const movePage = (num) => {

        console.log("num: " + num)
        data.page = num
        data.last = false
        data.refresh = !data.refresh
        setData({ ...data })
    }

    const refreshLast = () => {
        data.last = true
        data.refresh = !data.refresh
        setData({ ...data })
    }

    const cancelRead = () => {
        data.current = 0
        setData({ ...data })
    }

    const refreshPage = (hide) => {
        data.refresh = !data.refresh

        if (hide) {
            data.current = 0
        }

        setData({ ...data })
    }

    return (
        <div>
            <ProductReplyInput pno={pno} refreshLast={refreshLast}></ProductReplyInput>

            {data.current !== 0 ? <ProductReplyRead
                rno={data.current}
                cancelRead={cancelRead}
                refreshPage={refreshPage}></ProductReplyRead> : <></>}

            <ProductReplyList {...data} movePage={movePage} changeCurrent={changeCurrent}></ProductReplyList>
        </div>
    );
}

export default ProductReplyWrapper;