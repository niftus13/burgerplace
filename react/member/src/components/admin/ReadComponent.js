import { useEffect } from "react";
import { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { getMember } from "../../api/adminAPI";


const initState = {
    id: '',
    pw: '',
    nickname: '',
    admin: 0
}

const ReadComponent = ({ id, moveModify, moveList }) => {

    // const { nickname } = useSelector(state => state.login)

    const [member, setMember] = useState(initState)

    

    useEffect(() => {

        getMember(id).then(data => {
            
            console.log("data: " )
            console.log(data)

            setMember(data)

        }).catch(e => {
            console.log(e)
        })
    }, [id])

    return (
        <div>
        <div className="m-2 p-2 border-2">
            <h1>Input</h1>
            <div className="m-2 p-2 border-2">
                {member.nickname}
            </div>
            <div className="m-2 p-2 border-2">
                {member.id}
            </div>
            <div className="m-2 p-2 border-2">
                {member.pw}
            </div>
            <div className="m-2 p-2 border-2">
                {member.admin}
            </div>
            {/* <div>
                <button className="bg-blue-400 border-2 m-2 p-2 font-extrabold"
                    onClick={moveList}>
                    List
                </button>
                <button className="bg-orange-400 border-2 m-2 p-2 font-extrabold"
                    onClick={() => moveModify(member.id)}>
                    Modify
                </button>
                <button
                    className="bg-blue-300 border-2 m-2 p-2 font-bold"
                    onClick={moveList}>
                    List
                </button>
            </div> */}
        </div>
    </div>
);
}

export default ReadComponent;