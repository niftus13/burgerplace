import { createSearchParams, useNavigate, useSearchParams } from "react-router-dom"


const checkNull = (obj) => {

    const result = {}

    for (const attr in obj) {
        const attrName = attr
        const attrValue = obj[attr]

        if (attrValue && attrValue !== 'null') {
            result[attrName] = attrValue
        }
    }

    return result
}


const useQueryObj = () => {
    // Query String 처리
    const [search, setSearch] = useSearchParams()

    const navigate = useNavigate()
    
    console.log(search)
    // page size 값은 없다면 초기값 설정
    const page = search.get("page") || 1
    const size = search.get("size") || 10
    const type = search.get("type")
    const keyword = search.get("keyword")

    // object로  묶어주기
    const queryObj = checkNull({ page, size, type, keyword })
    
    const moveList = () => {
        const queryString = createSearchParams(queryObj).toString()
        navigate(`../list/?${queryString}`)
    }

    const moveRead = (Bno)=>{

        console.log("moveRead: " + Bno)

        const queryString = createSearchParams(queryObj).toString()
        
        navigate(`../read/${Bno}?${queryString}`)
    }

    const moveModify = (Bno)=>{

        console.log("moveModify: " + Bno)

        const queryString = createSearchParams(queryObj).toString()
        
        navigate(`../modify/${Bno}?${queryString}`)
    }

    const moveRegister = () => {

        console.log("moveRegister: ")

        navigate(`../register`)

    }



    return {queryObj,setSearch, moveList, moveRead, moveModify, moveRegister}
}

export default useQueryObj