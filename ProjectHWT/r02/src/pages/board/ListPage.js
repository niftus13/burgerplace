import ListComponent from "../../components/board/ListComponent";
import ListSearchComponent from "../../components/board/ListSearchComponent";
import useQueryObj from "../../hooks/useQueryObj";


const ListPage = () => {

    const {queryObj, setSearch, moveRead} = useQueryObj()
    // 객체 형식으로 useQueryObj 있는 것을 가져온다.

    console.log(queryObj)
    

    const movePage = (num) => {
        console.log("num===" , num)
        queryObj.page = num
        setSearch({...queryObj})
        // 쿼리스트링을 여기서 바꿔준다.
    }

    const moveSearch = (type, keyword) => {
        queryObj.page = 1
        queryObj.type = type
        queryObj.keyword = keyword
        setSearch({...queryObj})
        // 쿼리 스트링을 여기서 바꿔준다.
    }



    return ( 
        <div>
            Board List Page
            <ListSearchComponent 
            queryObj={queryObj} 
            moveSearch={moveSearch}>
            </ListSearchComponent>
            {/* 이 컴포넌트는 검색창 컴포넌트이다.
                속성으로 내려준 값은 쿼리스트링 객체, 
            */}

            <ListComponent 
            queryObj={queryObj} 
            movePage={movePage} 
            moveRead={moveRead}>
            </ListComponent>
            {/* 이 컴포넌트는 리스트를 나타내는 컴포넌트이다. 
                속성으로 내려준 값은 쿼리스트링 객체, page이동 함수, read 이동 함수이다.
            */}

        </div>
     );
}
 
export default ListPage;