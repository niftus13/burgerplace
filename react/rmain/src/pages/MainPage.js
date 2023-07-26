import { useNavigate } from "react-router-dom";
import BasicLayout from "../layouts/BasicLayout";
import { useState } from "react";
import MainSearchComponent from "../components/main/MainSearchComponent";



const MainPage = () => {

  const navigate = useNavigate();
  const [searchData, setSearchData] = useState({ type: "tc", keyword: "" });

  const moveSearch = () => {
    const queryString = new URLSearchParams(searchData).toString();
    navigate(`/board/list?${queryString}`);
  };
  


  return ( 
    <BasicLayout>
      <h2>Main Page</h2>
      <h1>TOP3</h1>
      <MainSearchComponent
        moveSearch={moveSearch}
        queryObj={searchData}
        setSearch={setSearchData}
      />
    </BasicLayout>
   );
}
 
export default MainPage;