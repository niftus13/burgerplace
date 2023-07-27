import { useSelector } from "react-redux";
import { Link } from "react-router-dom";
import CartNavi from "./CartNavi";


const LoginNavi = () => {

    const {email,nickname} = useSelector(state => state.login)

    console.log("LoginNavi..........", email)

    if(email !== '') {
        return (
            <div>
                <div>
                    {email} - {nickname}
                </div>
                <CartNavi></CartNavi>
            </div>

        )
    }


    return ( 
        <div>
            <div>
                <Link to="/member/login">LOGIN</Link>
            </div>

        </div>
     );
}
 
export default LoginNavi;