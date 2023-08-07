import { useEffect, useState } from "react";
import { deleteReply, getReply, putReply } from "../../api/repliesAPI";


const initState = {
    fRno:0,
    fBno:0,
    replyText:'',
    replyFile:'',
    nickname:''
}

const FreeReplyRead = ({fRno, cancelRead, refreshPage}) => {

    console.log("replyRead...." + fRno)

    const [reply, setReply] = useState(initState)

    useEffect(() => {

        getReply(fRno).then(data => {
            console.log(data)
            setReply(data)
        })
    }, [fRno])

    const   handleClickDelete = () => {

        deleteReply(fRno).then(data => {
            alert(`${data.result} 번 댓글이 삭제되었습니다`)
            refreshPage()
        })
    }

    const handleChange = (e) => {

        reply[e.target.name] = e.target.value
        setReply({...reply})
    }

    const handleClickModify = () => {

        putReply(reply).then(data => {
            alert(`${data.result} 수정되었습니다`)
            refreshPage(true)
        })
    }

    if(reply.replyText === '해당 글은 삭제되었습니다') {
        
        return <></>
    }

    return ( 
        <div className="m-8 bg-blue-200 border-2">
            <div>Reply Read {fRno}</div>
            <div>
                <div>{fRno}</div>
                <div>
                    <input 
                    type="text" 
                    name="nickname" 
                    onChange={handleChange} 
                    value={reply.replyText}>
                    </input>
                </div>
                <div>{reply.nickname}</div>

            </div>
            <div>
                <button onClick={handleClickModify}>Modify</button>
                <button onClick={handleClickDelete}>Delete</button>
                <button onClick={cancelRead}>Cancel</button>
            </div>
        </div>
     );
}
 
export default FreeReplyRead;