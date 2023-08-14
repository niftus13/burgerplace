import { useSelector } from "react-redux";

const CountDisplay = () => {

    // store에서 hooks 가져오기
    const obj = useSelector(state => state.counter)

    console.log(obj)

    return ( 
        <div className="text-4xl text-white">
            COUNT: {obj.num}
        </div>
     );
}
 
export default CountDisplay;