import { useEffect, useState } from "react";
import { getMemberList } from "../../api/adminAPI";



const AdminComponent = (params) => {

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
        <div className="border-4 border-black">
            <ul>
                {memberList.map(member => 
                    <li key={member.id}>
                        {member.nickname} - {member.id}
                    </li>
                )}
            </ul>
        </div>
    );
}

export default AdminComponent;