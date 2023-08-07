import { useEffect, useState } from "react";
import { getOne } from "../../../api/tradeAPI";

const initState = {
    tBno:0,
    tTitle:'',
    tContent:'',
    nickname:'',
    regDate:'',
    modDate:''
}

const TradeReadComponent = ({tBno}) => {

    const [board, setBoard] = useState(initState)

    useEffect(() => {
        getOne(tBno).then(data => {
            setBoard(data)
        })
    },[tBno])


    return ( 
        <div>
            <div>{board.tBno}</div>
            <div>{board.tTitle}</div>
            <div>{board.tContent}</div>
            <div>{board.nickname}</div>
            <div>{board.regDate}</div>
            <div>{board.modDate}</div>
        </div>
     );
}
 
export default TradeReadComponent;