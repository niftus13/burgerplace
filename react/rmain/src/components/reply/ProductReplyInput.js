import { useEffect, useState } from "react";
import { postPReply } from "../../api/productRepliesAPI";


const initState = {
    pRno:0,
    replyText:'',
    replyFile:'',
    replyer:''
}

const ProductReplyInput = ({pRno, refreshLast}) => {

    const [reply, setPReply] = useState({...initState})


    const handleChange = (e) => {
        reply[e.target.name] = e.target.value
        setPReply({...reply})
    }

    const handleClickRegister = (e) => {

        reply.pRno = pRno

        // {result:223}
        postPReply(reply).then(data => {

            console.log(data)

            alert(`${data.result}번 댓글이 등록완료` )

            refreshLast()

            setPReply({...initState})

        } )

    }
 
    return ( 
        <div className="m-8 bg-red-200 border-2">
            <div>Reply Input</div>
            <div className="m-2">
                <input type="text" name="replyText" value={reply.replyText} onChange={handleChange}></input>
            </div>
            <div className="m-2">
                <input type="text" name="replyer" value={reply.replyer} onChange={handleChange}></input>
            </div>
            <div className="m-2">
                <button onClick={handleClickRegister}>Register</button>
            </div>
        </div>
     );
}
 
export default ProductReplyInput;