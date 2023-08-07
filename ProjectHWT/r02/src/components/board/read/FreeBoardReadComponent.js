import { useEffect, useState } from "react";
import { getOne } from "../../../api/freeAPI";

const initState = {
    fBno:0,
    fTitle:'',
    fContent:'',
    nickname:'',
    regDate:'',
    modDate:''
}

const FreeReadComponent = ({fBno}) => {

    const [board, setBoard] = useState(initState)

    useEffect(() => {
        getOne(fBno).then(data => {
            setBoard(data)
        })
    },[fBno])


    return ( 
        <div>
            <div>{board.fBno}</div>
            <div>{board.fTitle}</div>
            <div>{board.fContent}</div>
            <div>{board.nickname}</div>
            <div>{board.regDate}</div>
            <div>{board.modDate}</div>
        </div>
     );
}
 
export default FreeReadComponent;