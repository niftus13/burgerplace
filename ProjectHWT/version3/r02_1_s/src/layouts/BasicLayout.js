import SampleNav from "./nav/SampleNav";

const BasicLayout = ({children}) => {
    return (
        <div className="container mx-auto min-w-[1000px]">
            <div className=" bg-gradient-to-r">
            
               <SampleNav></SampleNav>
            </div>
            
            <div>
                {children}
            </div>
        </div>

      );
}
 
export default BasicLayout;