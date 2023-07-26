import { useState } from "react";
import BasicLayout from "../layouts/BasicLayout";
import { useNavigate } from "react-router-dom";
import MainSearchComponent from "../components/main/MainSearchComponent";


const MainPage = () => {

  const navigate = useNavigate();

  const moveSearch = (type, keyword) => {

    const queryString = `?type=${type}&keyword=${keyword}`;

    navigate.push("/board/list" + queryString);
  }

  return ( 
    <BasicLayout>
      <h2>Main Page</h2>
      <h1>TOP3</h1>
      <MainSearchComponent moveSearch={moveSearch} queryObj={{ type: "", keyword: "" }}/>
    </BasicLayout>
   );
}
 
export default MainPage;