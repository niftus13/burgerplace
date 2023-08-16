import { useNavigate } from "react-router-dom";
import BoardRegisterComponent from "../../components/board/BoardRegisterComponent";

const RegisterPage = () => {

    const navigate = useNavigate()

    const moveRegister =()=>{

        navigate(("../register"))
    }

    const moveList = () =>{
        navigate(("../list"))
    }


    return ( 
        <div 
        className="text-3xl text-white text-center">
            Board Register Page
            <BoardRegisterComponent moveRegister={moveRegister} moveList={moveList}></BoardRegisterComponent>
        </div>
     );
}
 
export default RegisterPage;