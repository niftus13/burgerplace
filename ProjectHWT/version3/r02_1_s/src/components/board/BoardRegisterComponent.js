import { useState } from "react";
import { postBoard } from "../../api/boardAPI";
import { useRef } from "react";

const initState = {
    freeTitle: "free Title",
    freeContent: "free Content",
    nickname: "Register NickName"
}

const BoardRegisterComponent = ({ moveList }) => {

    const fileRef = useRef()


    const [board, setBoard] = useState({ ...initState })


    const handleChange = (e) => {

        board[e.target.name] = e.target.value
        setBoard({ ...board })
    }


    const handleClickSave = (e) => {

        const formData = new FormData();

        formData.append("freeTitle", board.freeTitle)
        formData.append("freeContent", board.freeContent)
        formData.append("nickname", board.nickname)

        console.dir(fileRef.current)

        const arr = fileRef.current.files

        for (let freeFiles of arr) {
            formData.append("freeFiles", freeFiles)
        }

        postBoard(formData).then(data => {

            const rno = data.result
            alert(`${rno}번 게시글이 등록되었습니다.`)

            moveList()
        })

    }

    const handleClickClear = (e) => {

        fileRef.current.value = ''
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
                <div >
                    <input type="text" className="border-2 border-slate-500" name="nickname" value={board.nickname} onChange={handleChange}></input>
                </div>
                <div className="m-2 p-2">
                    <input className=" border-2 border-gray-500" type="file" ref={fileRef} multiple name="freeImages" onChange={handleChange} ></input>
                </div>
            </div>

            <div className="m-2 p-2">
                <button className="m-2 p-2 border-2 border-gray-500 " onClick={handleClickSave}>Register</button>
                <button className="m-2 p-2 border-2 border-gray-500 " onClick={handleClickClear}>CLEARFILES</button>
            </div>
        </div>
    );
}

export default BoardRegisterComponent;