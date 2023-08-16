import { useState } from "react"
import { deleteBoard, getOne, putBoard } from "../../../api/boardAPI"
import { useEffect } from "react"
import { useRef } from "react"

const initState = {
    freeBno: 0,
    freeTitle: "",
    freeContent: "",
    nickname: "",
    freeImages: []
}

const BoardModifyComponent = ({ freeBno, moveList, moveRead }) => {

    const [board, setBoard] = useState(initState)

    const fileRef = useRef()


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

        const formData = new FormData();
        formData.append("freeBno", board.freeBno)
        formData.append("freeTitle", board.freeTitle)
        formData.append("freeContent", board.freeContent)
        formData.append("nickname", board.nickname)

        if (board.freeImages) {
            for (let pi of board.freeImages) {
                formData.append("freeImages", pi)
            }
        }

        const arr = fileRef.current.files

        for (let freeFiles of arr) {
            formData.append("freeFiles", freeFiles)
        }

        putBoard(formData).then(data => {
            console.log(data)
            alert(freeBno + "게시글이 수정되었습니다.")
            moveRead(freeBno)
        })

    }


    const handleClickDelImg = (imageName) => {

        const newArr = board.freeImages.filter(ele => ele !== imageName)

        board.freeImages = newArr

        setBoard({ ...board })
    }



    return (
        <div>
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

                    <div className="m-2 p-2">
                        <input className=" border-2 border-gray-500" type="file" ref={fileRef} multiple name="freeImages" ></input>
                    </div>

                    <div className="m-2 p-2 ">
                        <ul className="list-none flex">
                            {board.freeImages.map((imageName, idx) =>
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

export default BoardModifyComponent;