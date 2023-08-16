import { useParams } from "react-router-dom";
import useQueryObj from "../../hooks/useQueryObj";
import TradeReadComponent from "../../components/TradeBoard/read/TradeReadComponent";
import TradeReplyWrapper from "../../components/TradeReply/TradeReplyWrapper";


const ReadPage = () => {

    const { queryObj, moveList, moveModify } = useQueryObj()
    const { tradeBno } = useParams()


    console.log(tradeBno)
    console.log(queryObj)

    return (
        <div>
            <TradeReadComponent
                moveList={moveList}
                moveModify={moveModify}
                tradeBno={tradeBno}
            ></TradeReadComponent>

            <TradeReplyWrapper tradeBno={tradeBno}></TradeReplyWrapper>

        </div>
    );
}

export default ReadPage;