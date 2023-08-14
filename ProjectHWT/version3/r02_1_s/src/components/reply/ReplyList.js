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



const ReplyList = ({ freeBno, page, last, refresh, movePage, changeCurrent }) => {

    console.log("Reply List...bno: " + freeBno)

    // rendering시 에러방지
    const [listData, setListData] = useState(initState)

    useEffect(() => {

        getRepliesOfBoard(freeBno, page, last).then(data => {
            console.log(data)
            setListData(data)
        })

    }, [freeBno,page, last, refresh])


    return (

        <div className="border-2 border-black text-white">
            <div className="border-b-2">
                Reply List
            </div>
            <div>
                <ul>
                <li className="text-white border-2">
                        <span className="mr-2">freeRno </span>
                        <span className="m-2 mr-8 text-center">ReplyContent </span>
                 
                        </li>
                    {listData.dtoList.map( reply => <li key={reply.freeRno}
                    className="border-2"
                    onClick={ ()=>changeCurrent(reply.freeRno)}
                    >
                        {reply.freeRno} -- {reply.replyText}
                    </li>)}
                </ul>
                <ListPageComponent {...listData} movePage={movePage}></ListPageComponent>
            </div>
        </div>

    );
}

export default ReplyList;