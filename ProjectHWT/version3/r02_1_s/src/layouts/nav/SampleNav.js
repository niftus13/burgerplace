import { useSelector } from "react-redux";
import { Link } from "react-router-dom";
import LoginNav from "./LoginNav";

const SampleNav = () => {

    const todoArr = useSelector(state => state.todo)

    return (
        <div className="flex m-6 p-6 text-black font-serif ">

            <div className="m-5 text-3xl border-2 ">
                <Link to="/">Main</Link>

            </div>
            <div className="m-5 text-3xl ">
                <span className="bg-red-300 font-extrabold">{todoArr.length}</span>
            </div>
            <div className="m-5 text-3xl border-2 ">
                <Link to="/about">About</Link>
            </div>
            <div className="m-5 text-3xl border-2">
                <Link to="/products/list">Products</Link>
            </div>
            <div className="m-5 text-3xl border-2 ">
                <Link to="/board/list">Board</Link>
            </div>
            <div className="ml-80  text-2xl text-right">
                <LoginNav></LoginNav>
            </div>

        </div>
    );
}

export default SampleNav;