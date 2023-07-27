import { useParams } from "react-router-dom"
import useQueryObj from "../../hooks/useQueryObj"
import ReadComponent from "../../components/products/ReadComponent"
import useCustomLogin from "../../hooks/useCustomLogin"


const ReadPage = () => {

    const {queryObj, setSearch, moveRead, moveList, moveModify} = useQueryObj()
    const {pno} = useParams()

    useCustomLogin(() => {
        alert("로그인을 하세요")
    })

    console.log(pno)
    console.log(queryObj)


    return ( 
        <div className="text-left text-2xl">
            <ReadComponent 
            pno={pno} 
            moveModify={moveModify} 
            moveList={moveList}>
            </ReadComponent>
        </div>
     );
}
 
export default ReadPage;