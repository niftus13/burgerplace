import { useEffect, useState } from "react";
import { deleteReply, getReply, putReply } from "../../api/repliesAPI";
import { useRef } from "react";

const initState = {
    freeRno: 0,
    freeBno: 0,
    replyText: '',
    nickname:'',
    freeImages : []
}


const TradeReplyRead = ({freeRno,cancelRead,refreshPage}) => {
    
    console.log("ReplyRead............... " + freeRno)

    const [reply, setReply] =useState(initState)

    const fileRef = useRef()


    useEffect(()=>{

        getReply(freeRno).then(data =>{
            console.log(data)
            setReply(data)
        })


    },[freeRno])



    const handleClickDelete =  () =>{

        deleteReply(freeRno).then(data =>{
            alert(`${data.result}번 댓글이 삭제되었습니다.`)
            refreshPage(true)
        })
    }

    const handleClickDelImg = (imageName) => {

        const newArr = reply.freeImages.filter(ele => ele !== imageName)

        reply.freeImages = newArr

        setReply({ ...reply })
    }

    const handleChange = (e) => {

        reply[e.target.name] = e.target.value
        setReply({ ...reply })
    }


    const handleClickSave = (e) => {

        const formData = new FormData();

        formData.append("freeRno", reply.freeRno)
        formData.append("replyText", reply.replyText)
        formData.append("nickname", reply.nickname)

        if (reply.freeImages) {
            for (let pi of reply.freeImages) {
                formData.append("freeImages", pi)
            }
            console.log(formData+" if문에 있는 것")
        }

        console.dir(fileRef.current+" console.dir")

        const arr = fileRef.current.files

        for (let freeFiles of arr) {
            formData.append("freeFiles", freeFiles)
        }

    putReply(formData).then(data => {

            const rno = data.result
            alert(`${rno}번 댓글이 수정되었습니다.`)

            refreshPage(true)


        })

    }

    if(reply.replyText === '해당 글은 삭제 되었습니다.'){
        return <></>
    }

    
    return (  
        <div className="m-8 bg-gradient-to-r from-indigo-400 to-sky-300 border-4  text-zinc-100">
            <div className=" text-xl text-center">Reply Read {freeRno}</div>

            <div>
                <div className=" pl-2 pb-2">Reply Number - {freeRno}</div>
                <div className=" pl-2 pb-2">   
                    <span>Reply Text - </span>             
                    <input 
                    type="text" 
                    name="replyText"
                    className="text-black"
                    onChange={handleChange} 
                    value={reply.replyText}>
                    </input>
                    </div>
                <div className=" pl-2">Replyer -{reply.nickname}</div>

                <div className="m-2 p-2">
                        <input className=" border-2 border-gray-500" type="file" ref={fileRef} multiple name="freeImages" ></input>
                    </div>

                <div className="m-2 p-2 ">
                    <ul className="list-none ">
                        {reply.freeImages.map((imageName, idx) =>
                            <li key={idx}
                            className=" w-[50vh]"
                            >
                                <img 
                                src={`http://localhost/${imageName}`} alt="No image"></img>
                                <button className="bg-red-500 m-2 p-2" onClick={() => handleClickDelImg(imageName)}>X</button>
                            </li>)}
                    </ul>
                </div>
            </div>

            <div className="pl-2">
                <button className="border-2 border-slate-600 ml-2 mr-2" onClick={handleClickSave}>MODIFY</button>
                <button className="border-2 border-slate-600 ml-2 mr-2" onClick={handleClickDelete}>DELETE</button>
                <button className="border-2 border-slate-600 ml-2 mr-2" onClick={cancelRead}>CANCEL</button>
            </div>
        
        </div>
    );
}
 
export default TradeReplyRead;