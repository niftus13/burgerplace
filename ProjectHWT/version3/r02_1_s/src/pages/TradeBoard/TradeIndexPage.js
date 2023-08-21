import { Link, Outlet } from "react-router-dom";
import BasicLayout from "../../layouts/BasicLayout";


const TradeIndexPage = () => {
    return ( 
        <BasicLayout>

            <div className="mt-4 p-4 bg-gradient-to-l from-red-300 to-red-400 text-2xl
             text-white flex justify-center">
            <Link to="/tboard/list"> <div className="underline font-extrabold m-2 p-2">List</div> </Link>
                <Link to="/tboard/register"><div className="underline font-extrabold m-2 p-2">Register</div></Link>
            </div>

            <div className=" bg-white w-full border-2">
                <Outlet></Outlet>
            </div>
        </BasicLayout>
     );
}
 
export default TradeIndexPage;