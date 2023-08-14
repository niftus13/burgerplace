import TodoList from "../components/todo/TodoList";
import BasicLayout from "../layouts/BasicLayout";



const MainPage = () => {

    console.log("main go")
    return (
        <div  className="bg-blue-200">
          <BasicLayout></BasicLayout>
          <div className="text-white text-center text-2xl font-mono ">

            <TodoList></TodoList>
            <div className="flex justify-center items-center bg-blue-200">
            <img  src={require('../image/backimage.jpg')} ></img>
            </div>
           </div>
        </div>
      );
}
 
export default MainPage;
