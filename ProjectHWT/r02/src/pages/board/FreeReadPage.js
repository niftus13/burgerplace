import { useParams } from "react-router-dom";
import freeuseQueryObj from "../../hooks/freeuseQueryObj";
import FreeBoardReadComponent from "../../components/board/read/FreeBoardReadComponent";
import FreeReplyWrapper from "../../components/reply/FreeReplyWrapper";

const ReadPage = () => {

    const {queryObj,moveList} = freeuseQueryObj()
    const {fBno} = useParams()

    console.log(fBno)
    console.log(queryObj)

    return ( 
        <div>
        Board Read Page
        <FreeBoardReadComponent fBno={fBno}></FreeBoardReadComponent>
        <FreeReplyWrapper fBno={fBno}></FreeReplyWrapper>
        <button className="m-2 p-2 border-2 bg-orange-300"
         onClick={e => moveList()}>List</button>
        </div>
    );
}
 
export default ReadPage;