import { Outlet } from "react-router-dom";
import BasicLayout from "../../layouts/BasicLayout";

const IndexPage = () => {

    
    return ( 

            <BasicLayout>
                <div className=" p-4 bg-slate-800 text-2xl flex text-white">
                    <div className="font-extrabold m-4 p-2">List</div>
                    <div className="font-extrabold m-4 p-2">Regist</div>
                </div>
                <div className="h-[50vh] w-full border-2 bg-white">
                    <Outlet></Outlet>
                </div>
            </BasicLayout> 

     );
}
 
export default IndexPage;