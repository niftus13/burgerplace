import { useSelector } from "react-redux";
import { Link } from "react-router-dom";
import LoginNavi from "./LoginNavi";

const SampleNavi = () => {

    const todoArr = useSelector(state => state.todo)


    return ( 
        <div className="flex m-3 p-4 mx-auto text-white  bg-slate-800">
            <div className="flex ml-10 font-bold">
                <div className="m-3 p-2 text-3xl border-2">
                    <Link to={"/"}>Main</Link>
                    <span className="bg-emerald-700 ">{todoArr.length}</span>
                </div>
                <div className="m-3 p-2 text-3xl border-2">
                    <Link to={"/about"}>About</Link>
                </div>
                <div className="m-3 p-2 text-3xl border-2">
                    <Link to={"/products/list"}>Products</Link>
                </div>
                <div className="m-3 p-2 text-3xl border-2">
                    <Link to={"/board/list"}>Board</Link>
                </div>
                <div className=" ml-[60vh]" >
                    <LoginNavi></LoginNavi>
                </div>
            </div>
        </div>
     );
}
 
export default SampleNavi;