import { useEffect, useState } from "react";
import { getOne } from "../../../api/boardAPI";


const initState = {
    freeBno: 0,
    freeTitle: '',
    freeContent: '',
    nickname: '',
    regDate: '',
    modDate: ''
}

const ReadComponent = ({ freeBno,moveList,moveModify}) => {

    const [board, setBoard] = useState(initState)
    
    useEffect(() => {
        getOne(freeBno).then(data => {
            setBoard(data)
        })
    }, [freeBno])

    return (
        <div className="text-white text-2xl font-bold m-2 p-2">
            <div className="border-2 border-gray-400">
                <span>Number - </span>
                {board.freeBno}
            </div>
            <div className="border-2 border-gray-400">
                <span>Title - </span>
                {board.freeTitle}
            </div>
            <div className="border-2 border-gray-400">
                <div className="border-b-2">Content</div>
                
                <textare>
                {board.freeContent}
                </textare>
            </div>
            <div className="border-2 border-gray-400">
                <span>Writer - </span>
                {board.nickname}
            </div>
            <div>
                <span>Regist Date - </span>
                {board.regDate}
            </div>

            <div>
            <button 
                className="bg-sky-400 border-2 m-2 p-2 text-white font-bold"
                onClick={moveList}
                >List</button>
                <button onClick={() => moveModify(freeBno)} className="bg-sky-400 border-2 m-2 p-2 text-white font-bold">Modify</button>
            </div>

        </div>
    );
}

export default ReadComponent;