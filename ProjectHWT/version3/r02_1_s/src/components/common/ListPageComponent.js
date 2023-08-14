


const ListPageComponent = ({ movePage, start, end, prev, next, pageNums,page }) => {

    const handleClickPage = (pageNum) => {
        movePage(pageNum)
    }




    return (
        <div className="flex m-4 p-2 justify-center">
            {/* 페이지클릭시 movePage 호출이 되는지 || 파라미터 갯수들이 얼마나 필요한지  */}
            <ul className="flex">
                {prev ? <li className="m-2 p-2 bg-blue-500 border-2 text-white font-bold rounded-lg"
                    onClick={() => handleClickPage(start - 1)}
                >PREV</li> : <></>}
                {pageNums.map(num => <li
                    className="m-2 p-2 bg-blue-500 border-2 text-white font-bold rounded-lg"

                    key={num}
                    onClick={() => handleClickPage(num)}
                >
                    {page === num ? <span className="text-red-500">{num}</span> : <span>{num}</span>}
                 
                </li>)}
                {next ? <li className="m-2 p-2 bg-blue-500 border-2 text-white font-bold rounded-lg"
                    onClick={() => handleClickPage(end + 1)}
                >NEXT</li> : <></>}

            </ul>
        </div>

    );
}

export default ListPageComponent;