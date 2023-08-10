import { createSearchParams, useNavigate, useSearchParams } from "react-router-dom"

const checkNull = (obj) => {

  const result = {}

  for (const attr in obj) {
    const attrName = attr
    const attrValue = obj[attr]

    if( attrValue && attrValue !== 'null'){
      result[attrName] = attrValue
    }
  }

  return result
}

const useQueryObj = () => {

  const [search, setSearch] = useSearchParams()
  const navigate = useNavigate()

  console.log(search)
  
  const page = search.get("page") || 1
  const size = search.get("size") || 10
  const type = search.get("type")
  const keyword = search.get("keyword")

  const queryObj = checkNull({page,size,type,keyword})

  const moveList = () => {
    const queryString = createSearchParams (queryObj).toString()

    navigate(`../list?${queryString}`)
  }

  const moveRead = (id) => {
    console.log("moveRead: " + id)

    const queryString = createSearchParams (queryObj).toString()

    navigate(`../${id}?${queryString}`)

  }

  const moveModify = (bno) => {
    console.log("moveModify: " + bno)

    const queryString = createSearchParams (queryObj).toString()

    navigate(`../modify/${bno}?${queryString}`)

  }


  return {queryObj, setSearch, moveRead, moveList, moveModify}
}

export default useQueryObj