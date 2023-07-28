import { createSearchParams, useNavigate, useSearchParams } from "react-router-dom"


const checkNull = (obj) => {
    // 매개변수 obj를 받는 함수 생성

    const result = {}
    // 일단 비어있는 result 객체를 생성한다.

    for (const attr in obj) {
        //attr 이름으로 obj를 끝까지
        const attrName = attr
        const attrValue = obj[attr]

        if (attrValue && attrValue !== 'null') {
            result[attrName] = attrValue
        }
    }

    return result
    // 리턴 result는 널을 무시하는 객체를 만든다.
}


const useQueryObj = () => {
    // Query String 처리
    const [search, setSearch] = useSearchParams()

    const navigate = useNavigate()
    // 이동 함수
    
    console.log(search)
    // page size 값은 없다면 초기값 설정
    const page = search.get("page") || 1 // 페이지 값이 있다면 페이지 값 반환, 아니면 1 반환
    const size = search.get("size") || 10 // 사이즈 값이 있다면 사이즈 값 반환, 아니면 10반환
    const type = search.get("type") // type키의 값을 반환하는데 없으면 null 반환
    const keyword = search.get("keyword") // keyword키의 값을 반환하는데 없으면 null 반환

    // object로  묶어주기
    const queryObj = checkNull({ page, size, type, keyword })
    // null check함수로 객체로 묶어서 queryObj에 넣어주기
    // null check 함수로 인해 type, keyword가 초기값이 null이면 생략된다.
    
    const moveList = () => {
        const queryString = createSearchParams(queryObj).toString()
        navigate(`../list/?${queryString}`)
        // navigate()인자로 url를 넣으면 이동함수로 만들어진다.
        // `../list?${쿼리스트링}` 여기 쿼리스트링에는 page=1, size=10, ... 이렇게 들어간다.
    }

    const moveRead = (bno)=>{

        console.log("moveRead: " + bno)

        const queryString = createSearchParams(queryObj).toString()
        // 기존의 queryString에 bno가 붙어서 나간다.
        
        navigate(`../read/${bno}?${queryString}`)
    }
    const moveModify = (bno)=>{

        console.log("moveModify: " + bno)

        const queryString = createSearchParams(queryObj).toString()
        
        navigate(`../modify/${bno}?${queryString}`)
        // Read랑 같은 로직을 이룬다.
    }



    return {queryObj,setSearch, moveList, moveRead, moveModify}
}

export default useQueryObj