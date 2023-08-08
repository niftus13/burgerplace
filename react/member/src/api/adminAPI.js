import axios from "axios"


export const getMemberList = async (params) => {

    const res = await axios.get("http://localhost:8080/api/admin/members", params)

    return res.data
}