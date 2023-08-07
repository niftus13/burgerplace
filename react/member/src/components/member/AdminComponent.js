import { useEffect, useState } from "react";
import AdminPage from "../../page/member/AdminPage";
import { getMemberList } from "../../api/adminAPI";



const AdminComponent = () => {

    const [memberList, setMemberList] = useState([])

    useEffect(() => {
        const fetchData = async () => {
            try {
                const data = await getMemberList();
                setMemberList(data);
            } catch (error) {
                console.error('Error fetching member list:', error);
            }
        };

        fetchData();
    }, []);

    return (
        <div>
            <AdminPage memberList={memberList} />
        </div>
    );
}

export default AdminComponent;