import { useRef, useState } from "react";
import { postReply } from "../../api/repliesAPI";


const initState = {
    freeBno: 0,
    replyText: '',
    nickname: ''
}

const TradeReplyInput = ({ freeBno,refreshLast }) => {

    const fileRef = useRef()


    const [reply, setReply] = useState({...initState})

    const handleChange = (e) => {

        reply[e.target.name] = e.target.value
        setReply({ ...reply })
    }

    const handleClickSave = (e) => {

        reply.freeBno = freeBno;

        const formData = new FormData();

        formData.append("freeBno", reply.freeBno)
        formData.append("replyText", reply.replyText)
        formData.append("nickname", reply.nickname)

        console.dir(fileRef.current)

        const arr = fileRef.current.files

        for (let freeFiles of arr) {
            formData.append("freeFiles", freeFiles)
        }

        postReply(formData).then(data => {

            const rno = data.result
            alert(`${rno}번 게시글이 등록되었습니다.`)

            refreshLast()

            setReply({...initState})

        })

    }

    const handleClickClear = (e) => {

        fileRef.current.value = ''
    }


    return (

        <div className="m-8 bg-gradient-to-r from-red-300 to-amber-400 border-4 ">
            <div className="text-white font-extrabold ">Reply Input</div>
            <div className="m-2">
                <div>  <input type="text"  className="border-2 border-slate-500" name="replyText" value={reply.replyText} onChange={handleChange}></input></div>
                <br></br>
                <div >  <input type="text" className="border-2 border-slate-500" name="nickname" value={reply.nickname} onChange={handleChange}></input></div>
            </div>
            <div className="m-2 p-2">
                        <input className=" border-2 border-gray-500" type="file" ref={fileRef} multiple name="freeImages" ></input>
                    </div>
            <div >
                <button className=" border-2 border-slate-500 text-white font-semibold" onClick={handleClickSave}>Register</button>
                <button className="m-2 p-2 border-2 border-gray-500 " onClick={handleClickClear}>CLEARFILES</button>
            </div>
        </div>

    );
}

export default TradeReplyInput;