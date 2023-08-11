import { useEffect, useState } from "react";
import { getMemberList } from "../../api/adminAPI";
import AdminPageComponent from "./AdminPageComponent";
import useQueryObj from "../../hooks/useQueryObj";

const initState = {
    dtoList:[],
    end:0,
    start:0,
    next:false,
    prev:false,
    pageNums:[],
    page:0,
    size:0,
    requestDTO: null
  }

const AdminComponent = ({ queryObj, movePage, moveRead }) => {

    const [memberList, setMemberList] = useState(initState)

    useEffect(() => {
        getMemberList(queryObj).then(data => {
            console.log(data)
            setMemberList(data)
        }).catch(err => {
            console.log("=============================================================")
            console.log(err)
            console.log("=============================================================")
        })
    }, [queryObj])


    return (
        <div>
            <div className="border-4 border-black">
                <ul>
                    {memberList.dtoList.map(dto =>
                        <li key={dto.id}
                            onClick={() => moveRead(dto.id)}
                        >
                            {dto.nickname} - {dto.id}
                        </li>
                    )}
                </ul>
            </div>
            <AdminPageComponent movePage={movePage} {...memberList}></AdminPageComponent>
        </div>
    );
}

export default AdminComponent;