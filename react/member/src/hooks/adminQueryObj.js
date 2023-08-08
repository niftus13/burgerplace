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

const adminQueryObj = () => {

  const [search, setSearch] = useSearchParams()
  const navigate = useNavigate()

  console.log(search)
  
  const page = search.get("page") || 1
  const size = search.get("size") || 10
  const type = search.get("type")
  const keyword = search.get("keyword")

  const params = checkNull({page,size,type,keyword})

  const moveList = () => {

    navigate(`../members`)
  }

  const moveRead = (id) => {
    console.log("moveRead: " + id)

    navigate(`../read/${id}`)

  }

  const moveModify = (id) => {
    console.log("moveModify: " + id)

    navigate(`../modify/${id}?`)

  }
  
  return {params, setSearch, moveRead, moveList, moveModify}
}

export default adminQueryObj