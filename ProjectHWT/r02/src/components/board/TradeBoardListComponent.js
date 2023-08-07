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
} // 초기값이다.

const TradeBoardListComponent = ({queryObj, movePage, moveRead}) => {

    // 내려준 값을 함수 자체의 매개변수로 받는다.

    const [listData, setListData] = useState(initState)
    // 지금 listData에는 초기값 initState가 존재하고 setListData로 change할 수 있다.
    // queryObj 자체는 PageRequestDTO가 되어야 한다.

    useEffect(() => {
        // 페이지가 로드되거나 업데이트 되거나 리렌더링 될 떄 조건에 맞춰 이뤄지는 함수이다.

        getList(queryObj).then(data => {
            // axios로 queryObj가 실행되어 값을 가져오면... data에 담기게 된다.
            console.log(data, "홍원태 전사", "홍원태 워리어", "리버풀 전사")
            // 콘솔로 찍어서 값을 가져온다.
            setListData(data)
            // 함수를 이용해서 상태를 바꿔준다.
        })

    },[queryObj])
    // 검사하고 싶은 [배열]이 들어가면 => 조건은 특정값이 업데이트 될 때마다 이 작업을 수행한다.
    // use*** = 컴포넌트 안에서만. 아닐경우는 함수로 사용가능
    console.log(createSearchParams(queryObj).toString()+"홍원태")
    // initstate의 값이 변했다. => console.log 확인완료


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
 
export default TradeBoardListComponent;