import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { useEffect } from "react";

const useCustomLogin = (fn) => {

    const loginInfo = useSelector(state => state.login)

    const navigate = useNavigate()

    useEffect(() => {

        if (fn) {
            if (!loginInfo.signed) {
                fn(navigate)
            }
            return
        }

    //     console.log("signed" + loginInfo.signed)

    //     if (!loginInfo.signed) {
    //         navigate("/member/login")
    //     }

    // }, [loginInfo.signed])

    console.log(loginInfo.email)
        if(!loginInfo.email){
            navigate("/member/login")
        }
    },[loginInfo.email])

    return (loginInfo)
}

export default useCustomLogin;