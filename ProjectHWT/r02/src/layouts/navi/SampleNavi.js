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
                </div>
                <div className="m-3 p-2 text-3xl border-2">
                    <Link to={"/about"}>About</Link>
                </div>
                <div className="m-3 p-2 text-3xl border-2">
                    <Link to={"/fboard/list"}>FreeBoard</Link>
                </div>
                <div>
                    <Link to={"/tboard/list"}>TradeBoard</Link>
                </div>

            </div>
        </div>
    );
}

export default SampleNavi;