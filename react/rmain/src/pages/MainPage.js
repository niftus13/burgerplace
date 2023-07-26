import { useNavigate } from "react-router-dom";
import BasicLayout from "../layouts/BasicLayout";
import { useState } from "react";
import MainSearchComponent from "../components/main/MainSearchComponent";
import ListSearchComponent from "../components/board/ListSearchComponent";
import useQueryObj from "../hooks/useQueryObj";
import MainListComponent from "../components/main/MainListComponent";



const MainPage = () => {

  const navigate = useNavigate();
  const [searchData, setSearchData] = useState({ type: "tc", keyword: "" });

  const moveSearch = () => {
    const queryString = new URLSearchParams(searchData).toString();
    navigate(`/board/list?${queryString}`);
  };

  const { queryObj, setSearch, moveRead, moveList } = useQueryObj();

  const moveSearch2 = (type, keyword) => {
    queryObj.page = 1;
    queryObj.type = type;
    queryObj.keyword = keyword;
    setSearch({ ...queryObj });
  };
  const queryObjWithSize = { ...queryObj, size: 5 };

  return (
    <BasicLayout>
      <h2>Main Page</h2>
      <h1>============TOP3============</h1>
      <h1>H-------------------------------------------H</h1>
      <h1>H-------------------------------------------H</h1>
      <h1>H-------------------------------------------H</h1>
      <h1>H-------------------------------------------H</h1>
      <h1>============TOP3============</h1>
      <MainSearchComponent
        moveSearch={moveSearch}
        queryObj={searchData}
        setSearch={setSearchData}
      />
      <h1>============작은지도============</h1>
      <h1>H-----------------------------------------------H</h1>
      <h1>H-----------------------------------------------H</h1>
      <h1>H-----------------------------------------------H</h1>
      <h1>H-----------------------------------------------H</h1>
      <h1>============작은지도============</h1>
      <h1>행사상품</h1>
      <MainListComponent queryObj={queryObjWithSize} movePage={moveList} moveRead={moveRead} />
      <h1>거래게시판</h1>
      <h1>자유게시판</h1>
    </BasicLayout>
  );
}

export default MainPage;