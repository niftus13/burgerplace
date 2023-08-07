import axios from "axios"

export const getRepliesOfBoard = async (tBno, page = 1, last = false) => {

  const res = await axios.get(`http://localhost:8080/api/treplies/${tBno}/list?page=${page}&last=${last}`)

  return res.data

}

export const postReply = async (reply) => {

  const res = await axios.post('http://localhost:8080/api/treplies/' , reply)

  return res.data

}

export const getReply = async (tRno) => {

  const res = await axios.get(`http://localhost:8080/api/replies/${tRno}`)

  return res.data

}

export const deleteReply = async (tRno) => {

  const res = await axios.delete(`http://localhost:8080/api/replies/${tRno}`)

  return res.data
}

export const putReply = async (reply) => {

  const res = await axios.put(`http://localhost:8080/api/replies/${reply.tRno}`, reply)

  return res.data

}