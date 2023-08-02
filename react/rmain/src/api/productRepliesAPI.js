import axios from "axios"


export const getpRepliesOfBoard = async (pno, page = 1, last = false) => {

  const res = await axios.get(`http://localhost:8080/api/productreplies/${pno}/list?page=${page}&last=${last}`)

  return res.data

}

export const postpReply = async (reply) => {

  const res = await axios.post('http://localhost:8080/api/productreplies/' , reply)

  return res.data

}

export const getpReply = async (pRno) => {

  const res = await axios.get(`http://localhost:8080/api/productreplies/${pRno}`)

  return res.data

}

export const deletepReply = async (pRno) => {

  const res = await axios.delete(`http://localhost:8080/api/productreplies/${pRno}`)

  return res.data
}

export const putpReply = async (reply) => {

  const res = await axios.put(`http://localhost:8080/api/productreplies/${reply.pRno}`, reply)

  return res.data

}