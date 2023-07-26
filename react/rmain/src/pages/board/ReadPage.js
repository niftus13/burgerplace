import { useParams } from "react-router-dom";
import useQueryObj from "../../hooks/useQueryObj";
import ReplyWrapper from "../../components/reply/ReplyWrapper";


const ReadPage = () => {

  const [queryObj, setSearch,moveRead, moveList] = useQueryObj()
  const {bno} = useParams()

  console.log(bno)
  console.log(queryObj)

  return ( 
    <div>
      Board Read Page

      <ReplyWrapper bno={bno}></ReplyWrapper>

      <button onClick={e => moveList()}>List</button>
    </div>
  );
}
 
export default ReadPage;