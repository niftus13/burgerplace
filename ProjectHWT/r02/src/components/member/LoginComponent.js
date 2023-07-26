import { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { postLoginThunk, requestLogin } from "../../reducers/loginSlice";



const initState = {
    email: 'dnjsxoghd@naver.com',
    pw: '1111'
}

const LoginComponent = () => {
    const loginState = useSelector(state => state.login)

    const [loginInfo, setLoginInfo] = useState({...initState})

    const dispatch = useDispatch()

    const errorMsg = loginState.errorMsg

    return ( 
        <div className="text-2xl">
            <div className=" bg-red-400">
                {loginState.loading ? '로그인중' : ''}</div>
            <div>
                {errorMsg ? <div className="bg-red-300">이메일과 패스워드를 확인하세요</div> : ''}
            </div>
            <div className="h-[40vh] bg-white w-[45vh] my-20 mx-auto">
                <div className="pt-[7vh] px-2 ">
                    <div className="overflow-hidden ">
                        <label className="float-left">email</label>
                        <input type="text" name="email" 
                        className="p-1 float-right border-2 border-black rounded text-center "
                        value={loginInfo.email}
                        onChange={() => {}}>
                        </input>
                    </div>
                    <div className="overflow-hidden">
                        <label className="float-left">pw</label>
                        <input type="password" name="pw" 
                        className="p-1 float-right border-2 border-black rounded text-center"
                        value={loginInfo.pw}
                        onChange={() => {}}></input>
                    </div>
                    <div >
                        <button 
                        className="mt-5 border-slate-700 border-2 p-2 "
                        onClick={() => dispatch(postLoginThunk(loginInfo))}>LOGIN</button>
                    </div>
                </div>
            </div>
        </div>


     );
}
 
export default LoginComponent;