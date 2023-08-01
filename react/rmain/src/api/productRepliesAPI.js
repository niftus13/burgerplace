import axios from "axios"


export const getRepliesOfBoard = async (pno, page = 1, last = false) => {

  const res = await axios.get(`http://localhost:8080/api/productreplies/${pno}/list?page=${page}&last=${last}`)

  return res.data

}

export const postReply = async (reply) => {

  const res = await axios.post('http://localhost:8080/api/productreplies/' , reply)

  return res.data

}

export const getReply = async (pRno) => {

  const res = await axios.get(`http://localhost:8080/api/productreplies/${pRno}`)

  return res.data

}

export const deleteReply = async (pRno) => {

  const res = await axios.delete(`http://localhost:8080/api/productreplies/${pRno}`)

  return res.data
}

export const putReply = async (reply) => {

  const res = await axios.put(`http://localhost:8080/api/productreplies/${reply.pRno}`, reply)

  return res.data

}