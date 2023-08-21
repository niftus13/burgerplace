import { useEffect, useState } from "react";
import { deleteTradeReply, getTradeReply, putTradeReply } from "../../api/TradeReplyAPI";
import { useRef } from "react";

const initState = {
    tradeRno: 0,
    tradeBno: 0,
    replyText: '',
    nickname:'',
    tradeImages : []
}


const TradeReplyRead = ({tradeRno,cancelRead,refreshPage}) => {
    
    console.log("ReplyRead............... " + tradeRno)

    const [reply, setReply] =useState(initState)

    const fileRef = useRef()


    useEffect(()=>{

        getTradeReply(tradeRno).then(data =>{
            console.log(data)
            setReply(data)
        })


    },[tradeRno])



    const handleClickDelete =  () =>{

        deleteTradeReply(tradeRno).then(data =>{
            alert(`${data.result}번 댓글이 삭제되었습니다.`)
            refreshPage(true)
        })
    }

    const handleClickDelImg = (imageName) => {

        const newArr = reply.tradeImages.filter(ele => ele !== imageName)

        reply.freeImages = newArr

        setReply({ ...reply })
    }

    const handleChange = (e) => {

        reply[e.target.name] = e.target.value
        setReply({ ...reply })
    }


    const handleClickSave = (e) => {

        const formData = new FormData();

        formData.append("tradeRno", reply.tradeRno)
        formData.append("replyText", reply.replyText)
        formData.append("nickname", reply.nickname)

        if (reply.tradeImages) {
            for (let pi of reply.tradeImages) {
                formData.append("tradeImages", pi)
            }
            console.log(formData+" if문에 있는 것")
        }

        console.dir(fileRef.current+" console.dir")

        const arr = fileRef.current.files

        for (let tradeFiles of arr) {
            formData.append("tradeFiles", tradeFiles)
        }

    putTradeReply(formData).then(data => {

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
            <div className=" text-xl text-center">TradeReply Read {tradeRno}</div>

            <div>
                <div className=" pl-2 pb-2">Reply Number - {tradeRno}</div>
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
                        <input className=" border-2 border-gray-500" type="file" ref={fileRef} multiple name="tradeImages" ></input>
                    </div>

                <div className="m-2 p-2 ">
                    <ul className="list-none ">
                        {reply.tradeImages.map((imageName, idx) =>
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