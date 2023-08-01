import { useParams } from "react-router-dom"
import useQueryObj from "../../hooks/useQueryObj"
import ReadComponent from "../../components/products/ReadComponent"
import useCustomLogin from "../../hooks/useCustomLogin"
import ProductReplyWrapper from "../../components/reply/ProductReplyWrapper"
import ReplyWrapper from "../../components/reply/ReplyWrapper"


const ReadPage = () => {

    const { queryObj, setSearch, moveRead, moveList, moveModify } = useQueryObj()
    const { pno } = useParams()

    // useCustomLogin(() => {
    //     alert("로그인 좀 부탁해요")
    // })

    console.log(pno)
    console.log(queryObj)

    return (
        <div>
            <ReadComponent
                pno={pno}
                moveModify={moveModify}
                moveList={moveList}>
            </ReadComponent>
        </div>
    );
}

export default ReadPage;