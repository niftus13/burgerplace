import { useParams } from "react-router-dom"
import useQueryObj from "../../hooks/useQueryObj"
import ReadComponent from "../../components/products/ReadComponent"
import ProductReplyWrapper from "../../components/reply/ProductReplyWrapper"


const ReadPage = () => {

    const { queryObj, moveList, moveModify } = useQueryObj()
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
            <ProductReplyWrapper pno={pno} />
        </div>
    );
}

export default ReadPage;