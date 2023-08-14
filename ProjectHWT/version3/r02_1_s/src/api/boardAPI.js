import axios from "axios"
import { createSearchParams } from "react-router-dom"


export const getList = async (queryObj) =>{

    const queryString = createSearchParams(queryObj).toString()

    const res = await axios.get(`http://localhost:8080/api/board/list?${queryString}`)

    return res.data
}

export const postBoard = async (board) => {

    const res = await axios.post('http://localhost:8080/api/board/', board)
  
    return res.data
  
  }



export const getOne = async (freeBno) =>{

    const res = await axios.get(`http://localhost:8080/api/board/${freeBno}`)

    return res.data
}


export const deleteBoard = async (freeBno) => {

    const res = await axios.delete(`http://localhost:8080/api/board/${freeBno}`)
  
    return res.data
  }


  export const putBoard = async (board) => {

    const res = await axios.put(`http://localhost:8080/api/board/${board.freeBno}`, board)
  
    return res.data
  
  }


