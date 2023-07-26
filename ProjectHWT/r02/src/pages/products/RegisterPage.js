import { useNavigate } from "react-router-dom";
import RegisterComponent from "../../components/products/RegisterComponent";


const RegisterPage = () => {

    const navigate = useNavigate();

    const moveList = () => {

        navigate("../list")

    }


    return (
        <div className="bg-slate-500">
            <div className="text-2xl pb-2 text-center text-white">
                Product Register page
            </div>
            <RegisterComponent moveList={moveList}></RegisterComponent>
        </div>
    );
}

export default RegisterPage;