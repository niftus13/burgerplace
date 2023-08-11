import { useParams } from "react-router-dom"
import useQueryObj from "../../hooks/useQueryObj"
import ReadComponent from "../../components/admin/ReadComponent"

const AdminReadPage = () => {

    const {queryObj, setSearch, moveRead, moveList, moveModify} = useQueryObj()
    const { id } = useParams()

    console.log(id)
    console.log(queryObj)

    return (
        <div>
            <div>Board Read Page</div>
            <ReadComponent 
            id={id}
            moveModify={moveModify} 
            moveList={moveList}>
            </ReadComponent>
        </div>
    );
}

export default AdminReadPage;