import { useDispatch } from "react-redux";
import { dec, inc } from "../../reducers/countSlice";


const CountButtons = () => {

    const dispatch = useDispatch()

    const handleClickInc = () => {
        
        dispatch(inc(2, "INC"))
    }

    const handleClickDec = () => {

        dispatch(dec(2, "DEC"))
    }

    return ( 
        <div >
            <button className="m-2" onClick={handleClickInc}> INC </button>
            <button className="m-2" onClick={handleClickDec}> DEC </button>

        </div>
     );
}
 
export default CountButtons;