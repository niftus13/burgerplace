import { useDispatch, useSelector } from "react-redux";
import { Link, useNavigate } from "react-router-dom";
import CartNav from "./CartNav";
import { logout } from "../../reducers/loginSlice";


const LoginNav = () => {

    // const {email, signed} = useSelector(state => state.login)

    // console.log("loginNav.....", email, signed)

    // if(signed) {
    //     return ( 
    //         <div>
    //             <div>
    //                 {email}
    //             </div>
    //         </div>
    //      );
    // }

    const { email, nickname } = useSelector(state => state.login)
    const navigate = useNavigate()
    const dispatch = useDispatch()
 
    console.log("LoginNav...........", email, nickname)
    
    const handleLogOut = () => {
       
       dispatch(logout())
       navigate("/")
       
       
    }
    

    if (email !== '') {
        return(
        <div className="font-serif">
           <div>{email} - {nickname} </div>
           <CartNav></CartNav>
           <div><button onClick={handleLogOut}>Logout</button></div>
        </div>)
     }
  
  
     return (
        <div>
           <Link to="/member/login"> <div>LOGIN</div></Link>
        </div>
     );
  }
 
export default LoginNav;