import useCustomLogin from "../hooks/useCustomLogin";
import BasicLayout from "../layouts/BasicLayout";


const AboutPage = () => {

    const {loginInfo} = useCustomLogin()
   
    

    return ( 
        <div className="bg-red-300">
        <BasicLayout></BasicLayout>
        <div className="text-white text-center text-2xl font-mono ">
        


          <div className="flex justify-center items-center">
          <img  src={require('../image/backimage.jpg')} ></img>
          </div>
         </div>
      </div>
     );
}
 
export default AboutPage;