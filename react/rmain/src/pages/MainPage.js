
import useQueryObj from "../hooks/useQueryObj";
import BasicLayout from "../layouts/BasicLayout";


const MainPage = () => {

  const {queryObj, setSearch, moveRead, moveList} = useQueryObj()

  const moveSearch = (type, keyword) => {
    queryObj.page = 1
    queryObj.type = type
    queryObj.keyword = keyword

    setSearch({...queryObj})
  }

  return ( 
    <BasicLayout>
      <h2>Main Page</h2>
      <h1>TOP3</h1>
    </BasicLayout>
   );
}
 
export default MainPage;