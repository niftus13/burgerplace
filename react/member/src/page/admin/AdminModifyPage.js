import { useParams } from "react-router-dom";
import useQueryObj from "../../hooks/useQueryObj";
import AdminModifyComponent from "../../components/admin/AdminModifyComponent";

const AdminModifyPage = () => {

    const {queryObj, setSearch, moveRead, moveList, moveModify} = useQueryObj()
    const {id} = useParams()
    
    return ( 
        <div>
            <div>Member Modify Page {id}</div>
            <AdminModifyComponent id={id} moveList={moveList} moveRead={moveRead}/>
        </div>
     );
}
 
export default AdminModifyPage;