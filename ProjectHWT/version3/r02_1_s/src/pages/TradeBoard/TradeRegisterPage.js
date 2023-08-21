import { useNavigate } from "react-router-dom";
import TradeBoardRegisterComponent from "../../components/TradeBoard/TradeBoardRegisterComponent"


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
            TradeBoard Register Page
            <TradeBoardRegisterComponent moveRegister={moveRegister} moveList={moveList}></TradeBoardRegisterComponent>
        </div>
     );
}
 
export default RegisterPage;