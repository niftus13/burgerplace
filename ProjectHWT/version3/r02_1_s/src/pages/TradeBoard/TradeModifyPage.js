import { useNavigate, useParams } from "react-router-dom";
import useQueryObj from "../../hooks/useQueryObj";
import TradeBoardModifyComponent from "../../components/TradeBoard/read/TradeBoardModifyComponent"

const ModifyPage = () => {

    const { queryObj, moveList, moveRead } = useQueryObj()
    const { tradeBno } = useParams()


    return (
        <div>

            <div>TradeBoard Modify Page {tradeBno}</div>
            <TradeBoardModifyComponent tradeBno={tradeBno} moveList={moveList} moveRead={moveRead}></TradeBoardModifyComponent>

        </div>);
}

export default ModifyPage;