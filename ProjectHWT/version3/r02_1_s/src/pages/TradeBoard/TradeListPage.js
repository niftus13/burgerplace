import useQueryObj from "../../hooks/useQueryObj";
import TradeListSearchComponent from "../../components/TradeBoard/TradeListSearchComponent"
import TradeListComponent from "../../components/TradeBoard/TradeListComponent"





const TradeListPage = () => {



    const { queryObj, setSearch, moveRead, moveRegister } = useQueryObj()




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



    const changeSize = (size) => {

        queryObj.size = size

        setSearch({ ...queryObj })
    }



    console.log("SearchComponent----------------------------")
    console.log(queryObj)

    return (
        <div>
            <div className="text-red-300 text-3xl text-center font-bold">Trade Board</div>
            <TradeListSearchComponent
                queryObj={queryObj}
                moveSearch={moveSearch}
                changeSize={changeSize}
            >
            </TradeListSearchComponent>

            <TradeListComponent
                queryObj={queryObj}
                movePage={movePage}
                moveRead={moveRead}
                moveRegister={moveRegister}
            ></TradeListComponent>




        </div>
    );
}

export default TradeListPage;