
import TodoList from "../components/todo/TodoList";
import BasicLayout from "../layouts/BasicLayout";



const MainPage = () => {

    

    return ( 

        <BasicLayout>
            <div className="text-2xl pb-2 text-center text-white">
            <h2>MainPage</h2>
            <hr></hr>
            </div>


            <TodoList></TodoList>
        </BasicLayout>

     );
}
 
export default MainPage;