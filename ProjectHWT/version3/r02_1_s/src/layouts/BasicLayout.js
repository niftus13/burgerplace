import SampleNav from "./nav/SampleNav";

const BasicLayout = ({children}) => {
    return (
        <div className="container mx-auto min-w-[1280px]">
            <div className=" bg-gradient-to-r bg-blue-300">
            
               <SampleNav></SampleNav>
            </div>
            
            <div>
                {children}
            </div>
        </div>

      );
}
 
export default BasicLayout;