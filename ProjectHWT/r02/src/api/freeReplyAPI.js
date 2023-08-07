import axios from "axios"

export const getRepliesOfBoard = async (fBno, page = 1, last = false) => {

  const res = await axios.get(`http://localhost:8080/api/freplies/${fBno}/list?page=${page}&last=${last}`)

  return res.data

}

export const postReply = async (reply) => {

  const res = await axios.post('http://localhost:8080/api/freplies/' , reply)

  return res.data

}

export const getReply = async (fRno) => {

  const res = await axios.get(`http://localhost:8080/api/freplies/${fRno}`)

  return res.data

}

export const deleteReply = async (fRno) => {

  const res = await axios.delete(`http://localhost:8080/api/freplies/${fRno}`)

  return res.data
}

export const putReply = async (reply) => {

  const res = await axios.put(`http://localhost:8080/api/freplies/${reply.fRno}`, reply)

  return res.data

}