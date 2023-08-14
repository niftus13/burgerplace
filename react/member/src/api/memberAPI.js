import axios from 'axios';

export const registerMember = async (memberData) => {

    const response = await axios.post(`http://localhost:8080/api/member/register`, memberData);
    return response.data;
}

export const postRegister = async (formData) => {

    const header = {
        headers: {
            "Content-Type": "multipart/form-data",
        }
    }

    const res = await axios.post('http://localhost:8080/api/member/', formData, header )

    return res.data
}

