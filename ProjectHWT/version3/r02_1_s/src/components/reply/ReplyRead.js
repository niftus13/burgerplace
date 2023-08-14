import { useEffect, useState } from "react";
import { deleteReply, getReply, putReply } from "../../api/repliesAPI";

const initState = {
    freeRno: 0,
    freeBno: 0,
    replyText: '',
    replyFile: '',
    nickname:''
}


const ReplyRead = ({freeRno,cancelRead,refreshPage}) => {
    
    console.log("ReplyRead............... " + freeRno)

    const [reply, setReply] =useState(initState)

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
    const handleChange = (e) =>{

        reply[e.target.name] = e.target.value
        setReply({...reply})
        
    }

    const handleClickModify= () =>{

        putReply(reply).then ( data =>{
            alert(`${data.result}번 댓글이 수정되었습니다.`)
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
            </div>

            <div className="pl-2">
                <button className="border-2 border-slate-600 ml-2 mr-2" onClick={handleClickModify}>MODIFY</button>
                <button className="border-2 border-slate-600 ml-2 mr-2" onClick={handleClickDelete}>DELETE</button>
                <button className="border-2 border-slate-600 ml-2 mr-2" onClick={cancelRead}>CANCEL</button>
            </div>
        
        </div>
    );
}
 
export default ReplyRead;