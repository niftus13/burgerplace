import BasicLayout from "../layouts/BasicLayout";



const MainPage = () => {

    console.log("main go")
    return (
        <div  className="bg-red-200">
          <BasicLayout></BasicLayout>
          <div className="text-white text-center text-2xl font-mono h-max ">

            <div className="flex justify-center items-center bg-red-200">
            <img  src={require('../image/MainBurger2.jpg')} className="w-2/4 h-1/4 object-fill"
            ></img>
            </div>
           </div>
        </div>
      );
}
 
export default MainPage;
