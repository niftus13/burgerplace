import axios from "axios"
import { createSearchParams } from "react-router-dom";


export const getMemberList = async (queryObj) => {

    const queryString = createSearchParams(queryObj).toString();

    const res = await axios.get(`http://localhost:8080/api/admin/list?${queryString}`)

    return res.data
}

export const postMember = async (formData) => {

    const header = {
        headers: {
            "Content-Type": "multipart/form-data",
        }
    }

    const res = await axios.post('http://localhost:8080/api/admin/', formData, header )

    return res.data
}

export const getMember = async (id) => {

    const res = await axios.get(`http://localhost:8080/api/admin/${id}`)

    return res.data
}

export const deleteMember = async (id) => {

    const res = await axios.delete(`http://localhost:8080/api/admin/${id}`)

    return res.data
}

export const putMember = async (formData) => {

    const header = {
        headers: {
            "Content-Type": "multipart/form-data",
        }
    }

    const res = await axios.post('http://localhost:8080/api/admin/modify', formData, header )

    return res.data

}

