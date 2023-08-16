import { useNavigate, useParams } from "react-router-dom";
import useQueryObj from "../../hooks/useQueryObj";
import BoardModifyComponent from "../../components/board/read/BoardModifyComponent";

const ModifyPage = () => {

    const { queryObj, moveList, moveRead } = useQueryObj()
    const { tradeBno } = useParams()


    return (
        <div>

            <div>TradeBoard Modify Page {tradeBno}</div>
            <BoardModifyComponent freeBno={tradeBno} moveList={moveList} moveRead={moveRead}></BoardModifyComponent>

        </div>);
}

export default ModifyPage;