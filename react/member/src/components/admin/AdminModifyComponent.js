import { useEffect } from "react";
import { useState } from "react";
import { useRef } from "react";
import { deleteMember, getMember, putMember } from "../../api/adminAPI";

const initState = {
    id: '',
    pw: '',
    nickname: '',
    admin: 0
}

const AdminModifyComponent = ({id, moveList, moveRead}) => {

    const fileRef = useRef()
    const [member, setMember] = useState(initState)

    useEffect(() => {
        getMember(id).then(data => {
            setMember(data)
        })
    }, [id])

    const handleClickDelete = () => {
        deleteMember(id).then(data => {
            alert("계정삭제")
        })
    }

    const handleChange = (e) => {
        console.log("handle change..........")
        member[e.target.name] = e.target.value

        setMember({ ...member })
    }

    const handleClickModify = () => {

        const formData = new FormData();

        formData.append("id", member.id)
        formData.append("pw", member.pw)
        formData.append("nickname", member.nickname)
        formData.append("admin", member.admin);

        putMember(formData).then(data => {
            console.log(data)
            alert("수정되었습니다")
            moveRead(id)
        })
    }

    return (
        <div>
            <div className="m-2 p-2 border-2">
                {member.id}
            </div>

            <div className="m-2 p-2 border-2">
                <input type="text"
                    name="nickname"
                    value={member.nickname}
                    onChange={handleChange} />
            </div>

            <div className="m-2 p-2 border-2">
                <input type="text"
                    name="pw"
                    value={member.pw}
                    onChange={handleChange} />
            </div>

            <div className="m-2 p-2 border-2">
                <input type="number"
                    name="admin"
                    value={member.admin}
                    onChange={handleChange} />
            </div>

            <div>
                <button
                    className="bg-fuchsia-600 border-2 m-2 p-2 font-bold"
                    onClick={handleClickModify}
                >
                    Modify
                </button>

                <button
                    className="bg-blue-500 border-2 m-2 p-2 font-bold"
                    onClick={moveList}
                >
                    List
                </button>

                <button
                    className="bg-red-500 border-2 m-2 p-2 font-bold"
                    onClick={handleClickDelete}
                >
                    Delete
                </button>
            </div>




        </div>
    );
}

export default AdminModifyComponent;