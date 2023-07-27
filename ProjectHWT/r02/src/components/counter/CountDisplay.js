import { useSelector } from "react-redux";


const CountDisplay = () => {


    const obj = useSelector(state => state.counter)

    return ( 
        <div className="text-4xl">
            COUNT : {obj.num}
        </div>
     );
}
 
export default CountDisplay;