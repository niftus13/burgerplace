import CountButtons from "../components/counter/CountButtons";
import CountDisplay from "../components/counter/CountDisplay";
import TodoInput from "../components/todo/TodoInput";
import useCustomLogin from "../hooks/useCustomLogin";
import BasicLayout from "../layouts/BasicLayout";


const AboutPage = () => {

    const {loginInfo} = useCustomLogin()
   
    

    return ( 
        <div className="bg-blue-200">
        <BasicLayout></BasicLayout>
        <div className="text-white text-center text-2xl font-mono ">
        

          <CountDisplay></CountDisplay>
          <CountButtons></CountButtons>

          <TodoInput></TodoInput>

          <div className="flex justify-center items-center">
          <img  src={require('../image/backimage.jpg')} ></img>
          </div>
         </div>
      </div>
     );
}
 
export default AboutPage;