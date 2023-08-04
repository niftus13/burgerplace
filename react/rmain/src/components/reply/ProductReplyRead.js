import { useEffect, useState } from "react";
import { deletePReply, getPReply, putPReply } from "../../api/productRepliesAPI";

const initState = {
    pRno:0,
    pno:0,
    replyText:'',
    replyFile:'',
    replyer:''
}

const ProductReplyRead = ({pRno,cancelRead,refreshPage}) => {

    console.log("ReplyRead..........." + pRno)

    const [reply,setPReply] = useState(initState)

    useEffect(() =>{
        getPReply(pRno).then(data => {
            console.log(data)
            setPReply(data)
        })
    },[pRno])

    const handleClickDelete = () => {

        deletePReply(pRno).then(data => {
            alert(`${data.result}번 댓글이 삭제되었습니다.`)
            refreshPage(true)
        })

    }

    const handleChange = (e) => {
        reply[e.target.name] = e.target.value
        setPReply({...reply})
    }

    const handleClickModify = () => {

        putPReply(reply).then(data => {
            alert(`${data.result} 수정되었습니다`)
            refreshPage(true)
        })

    }

    if(reply.replyText === '해당 글은 삭제되었습니다.'){
        return <></>
    }


    return ( 
        <div className="m-8 bg-blue-200 border-2">
            <div>Reply Read {pRno}</div>
            <div>
                <div>{pRno}</div>
                <div> 
                    <input 
                    type="text" 
                    name="replyText" 
                    onChange={handleChange} 
                    value={reply.replyText} > 
                    </input>
                </div>
                <div>{reply.replyer}</div>
            </div>
            <div>
                <button onClick={handleClickModify}>MODIFY</button>
                <button onClick={handleClickDelete}>DELETE</button>
                <button onClick={cancelRead}>CANCEL</button>
            </div>

        </div>
     );
}
 
export default ProductReplyRead;