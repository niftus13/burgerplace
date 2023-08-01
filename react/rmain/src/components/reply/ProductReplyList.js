import { useEffect, useState } from "react";
import { getRepliesOfBoard } from "../../api/repliesAPI";
import ListPageComponent from "../common/ListPageComponent";



const initState = {
    dtoList: [],
    end: 0,
    start: 0,
    next: false,
    prev: false,
    pageNums: [],
    page: 0,
    size: 0,
    requestDTO: null
}



const ProductReplyList = ({ pRno, page, last, refresh, movePage, changeCurrent }) => {

    console.log("Reply List...pno: " + pRno)

    // rendering시 에러방지
    const [listData, setListData] = useState(initState)

    useEffect(() => {

        getRepliesOfBoard(pRno, page, last).then(data => {
            console.log(data)
            setListData(data)
        })

    }, [pRno, page, last, refresh])


    return (

        <div>
            <div>
                Reply List
            </div>
            <div>
                <ul>
                    {listData.dtoList.map( reply => 
                    <li 
                    key={reply.pRno}
                    onClick={() => changeCurrent(reply.pRno)}
                    >
                        {reply.pRno} -- {reply.replyText}
                    </li>)}
                </ul>
                <ListPageComponent {...listData} movePage={movePage}></ListPageComponent>
            </div>
        </div>

    );
}

export default ProductReplyList;