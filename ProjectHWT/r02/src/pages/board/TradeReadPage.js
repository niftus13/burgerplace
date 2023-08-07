import { useParams } from "react-router-dom";
import tradeuseQueryObj from "../../hooks/tradeuseQueryObj";
import TradeBoardReadComponent from "../../components/board/read/TradeBoardReadComponent";
import TradeReplyWrapper from "../../components/reply/TradeReplyWrapper";

const ReadPage = () => {

    const {queryObj,moveList} = tradeuseQueryObj()
    const {tBno} = useParams()

    console.log(tBno)
    console.log(queryObj)

    return ( 
        <div>
        Board Read Page
        <TradeBoardReadComponent tBno={tBno}></TradeBoardReadComponent>
        <TradeReplyWrapper tBno={tBno}></TradeReplyWrapper>
        <button className="m-2 p-2 border-2 bg-orange-300"
         onClick={e => moveList()}>List</button>
        </div>
    );
}
 
export default ReadPage;