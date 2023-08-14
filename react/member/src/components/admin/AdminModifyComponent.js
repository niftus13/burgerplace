import { useEffect } from "react";
import { useState } from "react";
import { deleteMember, getMember, putMember } from "../../api/adminAPI";

const initState = {
    id: '',
    pw: '',
    nickname: '',
    admin: false,
    delFlag: false
}

const AdminModifyComponent = ({id, moveList, moveRead}) => {

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

    // const handleChange = (e) => {
    //     console.log("handle change..........")
    //     member[e.target.name] = e.target.value

    //     setMember({ ...member })
    // }

    const handleChange = (e) => {
        const { name, value, type, checked } = e.target;
      
        // If it's a checkbox, update the boolean value
        const newValue = type === 'checkbox' ? checked : value;
      
        setMember(prevState => ({
          ...prevState,
          [name]: newValue
        }));
      };

    const handleClickModify = () => {

        const formData = new FormData();

        formData.append("id", member.id)
        formData.append("pw", member.pw)
        formData.append("nickname", member.nickname)
        formData.append("admin", member.admin);
        formData.append("delFlag", member.delFlag);

        putMember(formData).then(data => {
            console.log(data)
            alert("수정되었습니다")
            moveRead(id)
        })
    }

    return (
        <div>
            <div className="m-2 p-2 border-2">
                아이디: {member.id}
            </div>
            <div className="m-2 p-2 border-2">
                닉네임: 
                <input type="text"
                    name="nickname"
                    value={member.nickname}
                    onChange={handleChange} />
            </div>
            <div className="m-2 p-2 border-2">
                패스워드: 
                <input type="text"
                    name="pw"
                    value={member.pw}
                    onChange={handleChange} />
            </div>
            <div className="m-2 p-2 border-2">
                <label>
                    관리자 여부
                <input type="checkbox"
                    name="admin"
                    value={member.admin}
                    checked={member.admin}
                    onChange={handleChange} />
                    </label>
            </div>
            <div className="m-2 p-2 border-2">
                <label>
                    삭제 여부
                <input type="checkbox"
                    name="delFlag"
                    checked={member.delFlag} />
                    </label>
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