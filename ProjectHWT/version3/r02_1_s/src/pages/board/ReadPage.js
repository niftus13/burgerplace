import { useParams } from "react-router-dom";
import useQueryObj from "../../hooks/useQueryObj";
import ReadComponent from "../../components/board/read/ReadComponent";
import ReplyWrapper from "../../components/reply/ReplyWrapper";

const ReadPage = () => {

    const {queryObj,moveList,moveModify} = useQueryObj()
    const {freeBno} = useParams()
    
    
    console.log(freeBno)
    console.log(queryObj)

    return (  
        <div>

            <ReadComponent freeBno={freeBno}
             moveList={moveList}
             moveModify={moveModify}
             >
             </ReadComponent>

            
            <ReplyWrapper freeBno={freeBno}></ReplyWrapper>
        </div>
    );
}
 
export default ReadPage;