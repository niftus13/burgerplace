

const ListPageComponent = ({movePage,start,end,prev,next,pageNums,page}) => {

    const handleClickPage = (pageNum) => {
        movePage(pageNum)
    }

    console.log(page)

    return ( 
        <div className="flex m-4 p-2 justify-center">
            <ul className="flex m-4 p-2">
                {prev ? <li
                className="m-2 p-2 bg-blue-200 border-2  font-bold rounded-2xl"
                onClick={() => handleClickPage(start -1)}
                >PREV</li>:<></>}

                {pageNums.map(num => 
                <li 
                className="m-2 p-2 bg-blue-200 border-2  font-bold rounded-2xl"
                onClick={() => handleClickPage(num)}
                key={num}>
                    {page === num ? <span className=" text-red-600">{num}</span> : <span className=" text-white">{num}</span>}

                    </li>)}

                {next ? <li
                className="m-2 p-2 bg-blue-200 border-2  font-bold rounded-2xl"
                onClick={() => handleClickPage(end +1)}
                >NEXT</li>:<></>}

            </ul>
        </div>

     );
}
 
export default ListPageComponent;