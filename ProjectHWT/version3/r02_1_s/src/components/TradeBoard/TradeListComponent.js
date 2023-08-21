
//실제 axios에  전달하고 responseDTO를 받아서 처리해주는 Component

import { useEffect, useState } from "react";
import { getTradeList } from "../../api/TradeBoardAPI";
import ListPageComponent from "../common/ListPageComponent";
import styles from './TradeCss/TradeBoard.module.css';


const initState = {
    dtoList:[],
    end:0,
    start:0,
    next:false,
    prev:false,
    pageNums:[],
    page:0,
    size:0,
    requestDTO: null
}


const TradeListComponent = ({queryObj,movePage, moveRead, moveRegister}) => {
    // rendering시 에러방지
    const [listData, setListData] = useState(initState)

    // use가 붙으면 component 에서만 쓸수있다.
    // 그외는 자체적으로 제공하는 함수라 생각한다.
    useEffect(()=>{

        getTradeList(queryObj).then(data =>{
            console.log(data)
            setListData(data)
        })

    },[queryObj])

  
    return (  
        <div>

            {/* QueryObj는 필요함 search type keyword가 들어가야되기떄문 */}

            <div>
                <ul >
                    <li className="text-red-400 font-bold flex justify-center">
                        <div className="m-2">Number </div>
                        <div className="m-2">Title </div>
                        <div className="m-2">ReplyCounts</div>
                        <div className="m-2">Register Date</div>
                        </li>
                    {listData.dtoList.map(
                     ({tradeBno,tradeTitle,replyCount,regDate})   =>
                     
                     <li key={tradeBno}
                     className="flex justify-center border-2 border-white text-black text-1xl font-bold"
                     onClick={()=> moveRead(tradeBno)}
                     >{tradeBno} - {tradeTitle}  - [{replyCount}] - {regDate}</li>)}

                </ul>

        <button onClick={() => moveRegister()} className={styles.btn}>Register Page</button>

            </div>
            <div>
            </div>
            <ListPageComponent movePage={movePage} {...listData}></ListPageComponent>
        </div>
    );
}
 
export default TradeListComponent;