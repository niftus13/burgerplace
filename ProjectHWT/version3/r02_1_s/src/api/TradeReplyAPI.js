import axios from "axios"


export const getRepliesOfBoard = async (freeBno, page = 1, last = false) => {

  const res = await axios.get(`http://localhost:8080/api/treplies/${freeBno}/list?page=${page}&last=${last}`)

  return res.data
} // 완료



export const postReply = async (fordata) => {

  const header = {
    headers: {
        "Content-Type": "multipart/form-data",
    }
}

    const res = await axios.post('http://localhost:8080/api/treplies/', fordata, header)
  
    return res.data
  
  } 

  
export const getReply = async (freeRno) => {

  const res = await axios.get(`http://localhost:8080/api/treplies/${freeRno}`)

  return res.data

}

export const deleteReply = async (freeRno) => {

  const res = await axios.delete(`http://localhost:8080/api/treplies/${freeRno}`)

  return res.data
}


export const putReply = async(formdata) => {


  const header = {
       headers: {
           "Content-Type": "multipart/form-data",
       }
   }
  
   const res = await axios.post('http://localhost:8080/api/treplies/modify', formdata, header)
   
   return res.data

}