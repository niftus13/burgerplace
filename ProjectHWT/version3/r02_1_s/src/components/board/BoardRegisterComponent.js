import { useState } from "react";
import { postBoard } from "../../api/boardAPI";

const initState = {
    freeBno:0,
    freeTitle: "free Title",
    freeContent: "free Content",
    nickname:""
}

const BoardRegisterComponent = ({moveList}) => {


    const [board, setBoard] = useState({ ...initState })



    const handleChange = (e) => {

        board[e.target.name] = e.target.value
        setBoard({ ...board })
    }

    const handleClickRegister = (e) =>{

        postBoard(board).then(data =>{
            
            console.log(data)

            alert(`${data.result}번 게시글 등록완료`)
            
            setBoard({...initState})

            moveList()

        })
    }





    return (
        <div className="m-8 bg-gradient-to-r from-red-300 to-amber-400 border-4 ">
            <div className="text-black font-extrabold ">Board Register</div>
            <div className="m-2 text-black">
                <div>
                    <input type="text" className="border-2 border-slate-500" name="freeTitle" value={board.freeTitle} onChange={handleChange}></input>
                </div>
                <br></br>
                <div >
                    <input type="text" className="border-2 border-slate-500" name="freeContent" value={board.freeContent} onChange={handleChange}></input>
                </div>
            </div>
            <div >
                <button onClick={handleClickRegister} className=" border-2 border-slate-500 text-white font-semibold">Register</button>
            </div>
        </div>
    );
}

export default BoardRegisterComponent;