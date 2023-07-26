import { Link } from "react-router-dom";
import kakao from "../../image/kakao_login_large_wide.png";


const REST_KEY='afda205eafa16e4bcbad4ef1fdca99aa'
const REDIRECT_URI='http://localhost:3000/member/kakao'
const kakaoURL=`https://kauth.kakao.com/oauth/authorize?client_id=${REST_KEY}&redirect_uri=${REDIRECT_URI}&response_type=code`

const KakaoLoginComponent = () => {
    return ( 
        
        <Link to={kakaoURL}><img className="m-auto w-[45vh]" src={kakao} alt=""></img></Link>
        
     );
}
 
export default KakaoLoginComponent;