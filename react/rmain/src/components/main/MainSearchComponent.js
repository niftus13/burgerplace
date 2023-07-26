import React from "react";

const MainSearchComponent = ({ moveSearch, queryObj, setSearch }) => {
  return (
    <div className="m-4 p-4 bg-blue-300 border-2">
      <input
        type="text"
        className="border-1 m-2 p-2"
        value={queryObj.keyword}
        onChange={(e) => setSearch({ ...queryObj, keyword: e.target.value })}
      />
      <button onClick={moveSearch}>SEARCH</button>
    </div>
  );
};

export default MainSearchComponent;
