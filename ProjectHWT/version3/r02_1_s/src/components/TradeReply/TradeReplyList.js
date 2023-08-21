import { useEffect, useState } from "react";
import { } from "../../api/repliesAPI";
import ListPageComponent from "../common/ListPageComponent";
import { getTradeRepliesOfBoard } from "../../api/TradeReplyAPI";



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



const TradeReplyList = ({ tradeBno, page, last, refresh, movePage, changeCurrent }) => {

    console.log("Reply List...bno: " + tradeBno)

    // rendering시 에러방지
    const [listData, setListData] = useState(initState)

    useEffect(() => {

        getTradeRepliesOfBoard(tradeBno, page, last).then(data => {
            console.log(data)
            setListData(data)
        })

    }, [tradeBno,page, last, refresh])


    return (

        <div className="border-2 border-black text-white">
            <div className="border-b-2">
                Reply List
            </div>
            <div>
                <ul>
                <li className="text-white border-2">
                        <span className="mr-2">tradeRno </span>
                        <span className="m-2 mr-8 text-center">ReplyContent </span>
                 
                        </li>
                    {listData.dtoList.map( reply => <li key={reply.tradeRno}
                    className="border-2"
                    onClick={ ()=>changeCurrent(reply.tradeRno)}
                    >
                        {reply.tradeRno} -- {reply.replyText}
                    </li>)}
                </ul>
                <ListPageComponent {...listData} movePage={movePage}></ListPageComponent>
            </div>
        </div>

    );
}

export default TradeReplyList;