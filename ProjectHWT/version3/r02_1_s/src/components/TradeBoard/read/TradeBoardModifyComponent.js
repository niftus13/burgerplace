import { useState } from "react"
import { deleteTradeBoard, getTradeOne, putTradeBoard } from "../../../api/TradeBoardAPI"
import { useEffect } from "react"
import { useRef } from "react"

const initState = {
    tradeBno: 0,
    tradeTitle: "",
    tradeContent: "",
    nickname: "",
    tradeImages: []
}

const TradeBoardModifyComponent = ({ tradeBno, moveList, moveRead }) => {

    const [board, setBoard] = useState(initState)

    const fileRef = useRef()


    useEffect(() => {

        getTradeOne(tradeBno).then(data => {
            console.log(data)
            setBoard(data)
        })

    }, [tradeBno])


    const handleClickDelete = () => {

        deleteTradeBoard(tradeBno).then(data => {
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

        const formData = new FormData();
        formData.append("tradeBno", board.tradeBno)
        formData.append("tradeTitle", board.tradeTitle)
        formData.append("tradeContent", board.tradeContent)
        formData.append("nickname", board.nickname)

        if (board.tradeImages) {
            for (let pi of board.tradeImages) {
                formData.append("tradeImages", pi)
            }
        }

        const arr = fileRef.current.files

        for (let tradeFiles of arr) {
            formData.append("tradeFiles", tradeFiles)
        }

        putTradeBoard(formData).then(data => {
            console.log(data)
            alert(tradeBno + "게시글이 수정되었습니다.")
            moveRead(tradeBno)
        })

    }


    const handleClickDelImg = (imageName) => {

        const newArr = board.tradeImages.filter(ele => ele !== imageName)

        board.tradeImages = newArr

        setBoard({ ...board })
    }



    return (
        <div>
            <div>
                <div className="m-2 p-2">
                    {board.tradeBno}
                </div>
                <div className="m-2 p-2 ">

                    <input
                        className="bg-white"
                        type="text"
                        name="tradeTitle"
                        value={board.tradeTitle}
                        onChange={handleChange}
                    ></input>

                    <div className="m-2 p-2 ">
                        <input
                            className="bg-white"
                            type="text"
                            name="tradeContent"
                            value={board.tradeContent}
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

                    <div className="m-2 p-2">
                        <input className=" border-2 border-gray-500" type="file" ref={fileRef} multiple name="tradeImages" ></input>
                    </div>

                    <div className="m-2 p-2 ">
                        <ul className="list-none flex">
                            {board.tradeImages.map((imageName, idx) =>
                                <li key={idx} className="m-2">
                                    <img src={`http://localhost/s_${imageName}`} alt="No image"></img>
                                    <button className="bg-red-500 m-2 p-2" onClick={() => handleClickDelImg(imageName)}>X</button>
                                </li>)}
                        </ul>
                    </div>
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

export default TradeBoardModifyComponent;