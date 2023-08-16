import axios from "axios"
import { createSearchParams } from "react-router-dom"


export const getList = async (queryObj) =>{

    const queryString = createSearchParams(queryObj).toString()

    const res = await axios.get(`http://localhost:8080/api/tboard/list?${queryString}`)

    return res.data
} // 완료


export const postBoard = async (fordata) => {

  const header = {
    headers: {
        "Content-Type": "multipart/form-data",
    }
}

    const res = await axios.post('http://localhost:8080/api/tboard/', fordata, header)
  
    return res.data
  
  } // 등록 작업 완료



export const getOne = async (tradeBno) =>{

    const res = await axios.get(`http://localhost:8080/api/tboard/${tradeBno}`)

    return res.data
} // regDate 외 완료



export const deleteBoard = async (tradeBno) => {

    const res = await axios.delete(`http://localhost:8080/api/tboard/${tradeBno}`)
  
    return res.data
  }



export const putBoard = async(formdata) => {


    const header = {
         headers: {
             "Content-Type": "multipart/form-data",
         }
     }
    
     const res = await axios.post('http://localhost:8080/api/tboard/modify', formdata, header)
     
     return res.data
 
 }


