import ListComponent from "../../components/products/ListComponent";
import useQueryObj from "../../hooks/useQueryObj";

const ListPage = () => {

    const {queryObj,setSearch, moveList, moveRead} = useQueryObj()

    const movePage = (num) => {
        console.log("NUM---------------------------- " + num)
        // URL 변경시 useNavigate, setSearch 
        queryObj.page = num
        setSearch({ ...queryObj })
    }


    return ( 
        <div className="text-3xl ">



            <ListComponent 
            queryObj={queryObj} 
            movePage={movePage} 
            moveRead={moveRead}
            ></ListComponent>
        </div>
     );
}
 
export default ListPage;