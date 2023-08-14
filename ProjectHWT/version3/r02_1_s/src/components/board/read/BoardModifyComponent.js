import { useState } from "react"
import { deleteBoard, getOne, putBoard } from "../../../api/boardAPI"
import { useEffect } from "react"

const initState = {
    freeBno: 0,
    freeTitle: "",
    freeContent: "",
    nickname: ""
}

const BoardModifyComponent = ({ freeBno, moveList, moveRead }) => {

    const [board, setBoard] = useState(initState)

    useEffect(() => {

        getOne(freeBno).then(data => {
            console.log(data)
            setBoard(data)
        })

    }, [freeBno])

    const handleClickDelete = () => {

        deleteBoard(freeBno).then(data => {
            console.log(data)
            alert(`${data.result}번 게시글이 삭제되었습니다.`)
            moveList()
        })
    }

    const handleChange = (e) => {

        board[e.target.name] = e.target.value
        setBoard({ ...board })
    }


    const handleClickModify = () => {

        putBoard(board).then(data => {
            console.log(data+" putBoard")
            alert(`${data.result}번 게시글이 수정되었습니다.`)
            moveRead()
        })
    }



    return (
        <div>
            <div className="m-2 p-2">
                {board.freeBno}
            </div>
            <div className="m-2 p-2 ">
                <input
                    className="bg-white"
                    type="text"
                    name="freeTitle"
                    value={board.freeTitle}
                    onChange={handleChange}
                ></input>

                <div className="m-2 p-2 ">
                    <input
                        className="bg-white"
                        type="text"
                        name="freeContent"
                        value={board.freeContent}
                        onChange={handleChange}
                    ></input>
                </div>

                <div className="m-2 p-2 ">
                    <input
                        className="bg-white"
                        type="text"
                        name="nickname"
                        value={board.nickname}
                        onChange={handleChange}
                    ></input>
                </div>
                <div>
                <button
                    className="bg-amber-400 border-2 m-2 p-2 text-white font-bold"
                    onClick={handleClickModify}
                >
                    Modify
                </button>
                <button
                    className="bg-sky-400 border-2 m-2 p-2 text-white font-bold"
                    onClick={moveList}
                >
                    List
                </button>
                <button
                    className="bg-red-400 border-2 m-2 p-2 text-white font-bold"
                    onClick={handleClickDelete}
                >
                    Delete
                </button>

            </div>

            </div>
        </div>
    );
}

export default BoardModifyComponent;