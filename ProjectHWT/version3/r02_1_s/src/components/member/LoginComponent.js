import { useState } from "react";
import { useDispatch } from "react-redux";
import { requestLogin } from "../../reducers/loginSlice";

const initState = {
    email: 'user00@aaa.com',
    pw: '1111'
}

const LoginComponenet = () => {

    const [loginInfo, setLoginInfo] = useState({ ...initState })

    const dispatch = useDispatch()

    return (
        <div className="m-3 p-3  flex justify-center">
            <div>
                <div className="m-2 p-2 border-4 border-orange-300/75">
                    <div>
                        <span>ID Login</span>
                    </div>
                    <div className="m-2 p-2">
                        
                        <input className="bg-black" type="text" name="email" value={loginInfo.email} onChange={() => { }}></input>
                    </div>
                    <div className="m-2 p-2">

                        <input className="bg-black" type="password" name="pw" value={loginInfo.pw} onChange={() => { }}></input>
                    </div>
                
                <div>
                    <button className="border-2 m-2  mb-10" onClick={() => dispatch(requestLogin(loginInfo))}>LOGIN</button>
                </div>
                </div>
                <div className="flex justify-center items-center">
                    <img src={require('../../image/backimage.jpg')} ></img>
                </div>
            </div>
        </div>
    );
}

export default LoginComponenet;