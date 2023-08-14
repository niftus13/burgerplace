import { useEffect } from "react";
import { useState } from "react";

const AdminSearchComponent = ({ moveSearch, queryObj }) => {

    const [searchObj, setSearchObj] = useState({ type: '', keyword: '' });
    useEffect(() => {
        setSearchObj({ type: queryObj.type || 'nickname', keyword: queryObj.keyword || '' });
    }, [queryObj]);

    return (
        <div className="m-4 p-4 bg-blue-300 border-2">
            <select
                className="border-1 m-2 p-2"
                value={searchObj.type}
                onChange={e => setSearchObj({ ...searchObj, type: e.target.value })}
            >
                <option value={'nickname'}>nickname</option>
                <option value={'id'}>id</option>
                <option value={'in'}>id&nickname</option>
            </select>
            <input
                type='text'
                className="border-1 m-2 p-2"
                value={searchObj.keyword}
                onChange={e => setSearchObj({ ...searchObj, keyword: e.target.value })}
            ></input>

            <button
                className="p-2 m-2 border-2"
                onClick={e => moveSearch(searchObj.type, searchObj.keyword)}
            >SEARCH</button>
        </div>
    );
}

export default AdminSearchComponent;