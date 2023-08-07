import axios from "axios"


export const getMemberList = async () => {

    const res = await axios.get("http://localhost:8080/api/admin/members")

    return res.data
}