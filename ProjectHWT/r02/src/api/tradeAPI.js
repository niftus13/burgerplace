import axios from "axios"
import { createSearchParams } from "react-router-dom"

export const getList = async (queryObj) =>{

    const queryString = createSearchParams(queryObj).toString();

    const res = await axios.get(`http://localhost:8080/api/tboard/list?${queryString}`)

    return res.data
}


export const getOne = async(tBno) =>{
    
    const res = await axios.get(`http://localhost:8080/api/tboard/${tBno}`)

    return res.data
}

export const postBoard = async (board) => {

    const res = await axios.post('http://localhost:8080/api/tboard/' , board)
  
    return res.data
  
  }

