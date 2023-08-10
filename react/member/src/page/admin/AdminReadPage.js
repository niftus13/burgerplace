import { useParams } from "react-router-dom"
import useQueryObj from "../../hooks/useQueryObj"
import ReadComponent from "../../components/admin/ReadComponent"

const AdminReadPage = () => {

    const {queryObj, setSearch, moveRead, moveList} = useQueryObj()
    const { id } = useParams()

    console.log(id)
    console.log(queryObj)

    return (
        <div>
            <ReadComponent>
                Board Read Page
                <button onClick={e => moveList()}>List</button>
            </ReadComponent>
        </div>
    );
}

export default AdminReadPage;