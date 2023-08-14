
import ListComponent from "../../components/board/ListComponent";
import ListSearchComponent from "../../components/board/ListSearchComponent";
import useQueryObj from "../../hooks/useQueryObj";




const ListPage = () => {



    const {queryObj,setSearch,moveRead,moveRegister} = useQueryObj()




    console.log("queryObj---------------- ")
    console.log(queryObj)

    console.log("Board List go")

    const movePage = (num) => {
        console.log("NUM---------------------------- " + num)
        // URL 변경시 useNavigate, setSearch 
        queryObj.page = num
        setSearch({ ...queryObj })
    }
    // 검색시 이동하는 함수
    const moveSearch = (type, keyword) => {
        queryObj.page = 1
        queryObj.type = type
        queryObj.keyword = keyword

        setSearch({ ...queryObj })
    }



    const changeSize = (size) =>{

        queryObj.size = size

        setSearch({...queryObj})
    }



    console.log("SearchComponent----------------------------")
    console.log(queryObj)

    return (
        <div>
            {/* queryobj를 전달 해준다.  */}
            <div className="text-white text-3xl text-center font-bold">Board</div>
            <ListSearchComponent 
            queryObj={queryObj} 
            moveSearch={moveSearch} 
            changeSize={changeSize}>

            </ListSearchComponent>
            <ListComponent 
            queryObj={queryObj} 
            movePage={movePage} 
            moveRead={moveRead}
            moveRegister={moveRegister}
            ></ListComponent>
        </div>
    );
}

export default ListPage;