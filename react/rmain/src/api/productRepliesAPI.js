import axios from "axios"


export const getpRepliesOfProduct = async (pno, page = 1, last = false) => {

  const res = await axios.get(`http://localhost:8080/api/productreplies/${pno}/list?page=${page}&last=${last}`)

  return res.data

}

export const postPReply = async (PReply) => {

  const res = await axios.post('http://localhost:8080/api/productreplies/' , PReply)

  return res.data

}

export const getPReply = async (pRno) => {

  const res = await axios.get(`http://localhost:8080/api/productreplies/${pRno}`)

  return res.data

}

export const deletePReply = async (pRno) => {

  const res = await axios.delete(`http://localhost:8080/api/productreplies/${pRno}`)

  return res.data
}

export const putPReply = async (PReply) => {

  const res = await axios.put(`http://localhost:8080/api/productreplies/${PReply.pRno}`, PReply)

  return res.data

}