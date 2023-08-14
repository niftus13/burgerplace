import { useEffect, useState } from "react";


const ListSearchComponent = ({ queryObj, moveSearch, changeSize }) => {

    const [chSize, setChSize] = useState({ size: 10 })
    const [searchObj, setSearchObj] = useState({ type: '', keyword: '' })

    // 무한 렌더링 방지 처리
    useEffect(() => {

        searchObj.type = queryObj.type || ''
        searchObj.keyword = queryObj.keyword || ''
        chSize.size = queryObj.size || 10
        setSearchObj({ ...searchObj })
        setChSize({ ...chSize })
    }, [queryObj])

    const handleSize = (e) => {
        chSize.size = e.target.value
        changeSize(chSize.size)
    }

    console.log(chSize.size)
    return (
        <div className="m-2 p-4 bg-blue-300 border-2 ">
            <select className="boarder-2 m-2 p-2 font-medium "
                value={searchObj.type}
                onChange={e => {
                    searchObj.type = e.target.value
                    setSearchObj({ ...searchObj })
                }}
            >
                <option value={''}>------</option>
                <option value={'t'}>제목</option>
                <option value={'c'}>내용</option>
                <option value={'w'}>작성자</option>
                <option value={'tc'}>제목+내용</option>
                <option value={'tcw'}>제목+내용+작성자</option>
            </select>

            <input
                type="text"
                className="border-1 m-2 p-2"
                value={searchObj.keyword}
                onChange={e => {
                    searchObj.keyword = e.target.value
                    setSearchObj({ ...searchObj })
                }}
            ></input>

            <button
                className="border-2 m-2 p-2 text-white font-extrabold rounded-lg"
                onClick={e => moveSearch(searchObj.type, searchObj.keyword)}
            >SEARCH</button>

            <select className="boarder-2 m-2 p-2 font-bold"
                value={chSize.size}
                onChange={e => handleSize(e) }
            >
                <option value={5}>5</option>
                <option value={10}>10</option>
                <option value={15}>15</option>
                <option value={20}>20</option>
            </select>
        </div>
    );
}

export default ListSearchComponent;