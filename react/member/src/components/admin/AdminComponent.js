import { useEffect, useState } from "react";
import { getMemberList } from "../../api/adminAPI";
import AdminPageComponent from "./AdminPageComponent";

const AdminComponent = ({ params, movePage, moveRead }) => {

    const [memberList, setMemberList] = useState([])

    useEffect(() => {
        getMemberList(params).then(data => {
            console.log(data)
            setMemberList(data)
        }).catch(err => {
            console.log("=============================================================")
            console.log(err)
            console.log("=============================================================")
        })
    }, [params])


    return (
        <div>
            <div className="border-4 border-black">
                <ul>
                    {memberList.map(member =>
                        <li key={member.id}
                            onClick={() => moveRead(member.id)}
                        >
                            {member.nickname} - {member.id}
                        </li>
                    )}
                </ul>
            </div>
            <AdminPageComponent movePage={movePage} {...memberList}></AdminPageComponent>
        </div>
    );
}

export default AdminComponent;