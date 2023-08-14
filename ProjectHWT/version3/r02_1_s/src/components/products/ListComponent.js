
//실제 axios에  전달하고 responseDTO를 받아서 처리해주는 Component

import { useEffect, useState } from "react";
import { getList } from "../../api/productAPI";
import ListPageComponent from "../common/ListPageComponent";


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


const ListComponent = ({queryObj,movePage, moveRead}) => {
    // rendering시 에러방지
    const [listData, setListData] = useState(initState)

    // use가 붙으면 component 에서만 쓸수있다.
    // 그외는 자체적으로 제공하는 함수라 생각한다.
    useEffect(()=>{

        getList(queryObj).then(data =>{
            console.log(data)
            setListData(data)
        })

    },[queryObj])

  
    return (  
        <div>

            {/* QueryObj는 필요함 search type keyword가 들어가야되기떄문 */}

            <div>
                <div>List Component</div>
                <ul  className="flex flex-wrap container justify-center">
               
                    {listData.dtoList.map(
                     ({pno,pname,price,fname,reviewCnt,reviewAvg})   =>
                     
                     <li
                      key={pno}
                     className="border-2 border-white text-white text-2xl font-bold w-1/6 h-[280px] bg-sky-300 m-2 p-2 rounded-md shadow-lg"
                     onClick={()=> moveRead(pno)}
                     >  
                        <div className="">
                            <div className="font-extrabold">{pno}</div>
                            <div className="flex justify-center items-center">
                                <img src={`http://localhost/s_${fname}`} className="w-20"  alt="No image"></img>
                            </div>
                            <div>
                                <div className="text-center text-xl">{pname} - {price}</div>
                                <div  className="text-right text-xl">리뷰 {reviewCnt} - {reviewAvg}</div>
                            </div>
                          
                           
                        </div>

                         </li>)}
                </ul>
            </div>
            <ListPageComponent movePage={movePage} {...listData}></ListPageComponent>
        </div>
    );
}
 
export default ListComponent;