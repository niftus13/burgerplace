import { createSearchParams } from "react-router-dom";
import { getList } from "../../api/boardAPI";
import { useEffect, useState } from "react";
import ListPageComponent from "../common/ListPageComponent";


const initState = {
    dtoList:[],
    end:0,
    start:0,
    next: false,
    prev: false,
    pageNums:[],
    page:0,
    size:0,
    requestDTO:null
}

const ListComponent = ({queryObj, movePage, moveRead}) => {

    const [listData, setListData] = useState(initState)

    useEffect(() => {

        getList(queryObj).then(data => {
            console.log(data)
            setListData(data)
        })

    },[queryObj])
    // use*** = 컴포넌트 안에서만. 아닐경우는 함수로 사용가능
    // console.log(createSearchParams(queryObj).toString())


    return ( 
        <div>
            <div>
                <div>ListComponent</div>
                
            </div>
            <div>
                <ul>
                    {listData.dtoList.map(
                        dto => 
                        <li key={dto.bno}
                        onClick={() => moveRead(dto.bno)}
                        >{dto.bno} - {dto.title} - {dto.replyCount}</li>)}
                </ul>
            </div>

            <ListPageComponent movePage={movePage} {...listData}></ListPageComponent>

        </div>
     );
}
 
export default ListComponent;