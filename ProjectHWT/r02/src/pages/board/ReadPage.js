import { useParams } from "react-router-dom";
import useQueryObj from "../../hooks/useQueryObj";
import ReadComponent from "../../components/board/read/ReadComponent";
import ReplyWrapper from "../../components/reply/ReplyWrapper";

const ReadPage = () => {

    const {queryObj,moveList} = useQueryObj()
    const {bno} = useParams()

    console.log(bno)
    console.log(queryObj)

    return ( 
        <div>
        Board Read Page
        <ReadComponent bno={bno}></ReadComponent>
        <ReplyWrapper bno={bno}></ReplyWrapper>
        <button className="m-2 p-2 border-2 bg-orange-300"
         onClick={e => moveList()}>List</button>
        </div>
    );
}
 
export default ReadPage;