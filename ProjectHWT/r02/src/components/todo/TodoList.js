import { useSelector } from "react-redux";


const TodoList = () => {

    const todoArr = useSelector(state => state.todo)


    return ( 
        <div>
            <ul>
                {todoArr.map((ele,idx) => <li key={idx}>{ele}</li>)}
            </ul>
        </div>
     );
}
 
export default TodoList;