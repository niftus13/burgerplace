import { useParams } from "react-router-dom"
import adminQueryObj from "../../hooks/adminQueryObj"

const AdminReadPage = () => {

    const [params, setSearch, moveRead, moveList] = adminQueryObj()
    const { id } = useParams()

    console.log(id)
    console.log(params)

    return (
        <div>
            Board Read Page
            <button onClick={e => moveList()}>List</button>
        </div>
    );
}

export default AdminReadPage;