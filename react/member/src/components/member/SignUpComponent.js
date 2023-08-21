import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { registerMember } from "../../api/memberAPI";


const initState = {
    id: "",
    pw: "",
    nickname: ""
}
const SignUpComponent = () => {

    const navigate = useNavigate()

    const [userData, setUserData] = useState(initState)

    const handleChange = (e) => {
        const { name, value } = e.target
        setUserData((prevData) => ({ ...prevData, [name]: value }))
    }

    const handleSubmit = async (e) => {
        e.preventDefault()

        try {
            const formData = new FormData()
            formData.append("id", userData.id)
            formData.append("pw", userData.pw)
            formData.append("nickname", userData.nickname)

            const response = await registerMember(formData) // 회원 등록 API 호출

            // 회원 등록 성공 시
            console.log(response)
            alert("회원 등록이 완료되었습니다.")

            // 회원 등록 후 로그인 페이지로 이동
            navigate("/")
        } catch (error) {
            console.error("회원 등록 실패:", error)
        }
    }

    return (
        <div>
            <div className="mt-4 p-4 bg-green-500 text-2xl flex justify-center">
                <h1>회원가입 페이지</h1>
            </div>
            <div className="h-[50vh] w-full border-2 flex justify-center items-center">
                <form onSubmit={handleSubmit} className="w-1/3 p-4 border-2">
                    <div className="m-2 p-2 border-2">
                        <label>ID:</label>
                        <input
                            type="text"
                            name="id"
                            value={userData.id}
                            onChange={handleChange}
                            required
                        />
                    </div>
                    <div className="m-2 p-2 border-2">
                        <label>Password:</label>
                        <input
                            type="password"
                            name="pw"
                            value={userData.pw}
                            onChange={handleChange}
                            required
                        />
                    </div>
                    <div className="m-2 p-2 border-2">
                        <label>Nickname:</label>
                        <input
                            type="text"
                            name="nickname"
                            value={userData.nickname}
                            onChange={handleChange}
                            required
                        />
                    </div>
                    <div  className="m-2 p-2 border-2">
                        <button type="submit" className="Admin-Button">
                            회원가입
                        </button>
                    </div>
                </form>
            </div>
        </div>
    )
}

export default SignUpComponent