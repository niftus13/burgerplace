import { useEffect, useState } from "react";
import { getOne } from "../../../api/TradeBoardAPI";


const initState = {
    tradeBno: 0,
    tradeTitle: '',
    tradeContent: '',
    nickname: '',
    regDate: '',
    modDate: '',
    tradeImages : []
}

const TradeReadComponent = ({ tradeBno,moveList,moveModify}) => {

    const [board, setBoard] = useState(initState)
    
    useEffect(() => {
        getOne(tradeBno).then(data => {
            setBoard(data)
        }).catch(e => {
            console.log(e)
           
        })
    }, [tradeBno])

    return (
        <div className="text-white text-2xl font-bold m-2 p-2">
            <div className="border-2 border-gray-400">
                <span>Number - </span>
                {board.tradeBno}
            </div>
            <div className="border-2 border-gray-400">
                <span>Title - </span>
                {board.tradeTitle}
            </div>
            <div className="border-2 border-gray-400">
                <div className="border-b-2">Content</div>
                
                <textare>
                {board.tradeContent}
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

            <div className="m-2 p-2 ">
                    <ul className="list-none ">
                        {board.tradeImages.map((imageName, idx) =>
                            <li key={idx}
                            className=" w-[50vh]"
                            >
                                <img 
                                src={`http://localhost/${imageName}`} alt="No image"></img>
                            </li>)}
                    </ul>
                </div>

            <div>
            <button 
                className="bg-sky-400 border-2 m-2 p-2 text-white font-bold"
                onClick={moveList}
                >List</button>
                <button onClick={() => moveModify(tradeBno)} className="bg-sky-400 border-2 m-2 p-2 text-white font-bold">Modify</button>
            </div>

        </div>
    );
}

export default TradeReadComponent;