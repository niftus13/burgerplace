import React from 'react';
import AdminComponent from '../../components/admin/AdminComponent';
import useQueryObj from '../../hooks/useQueryObj';

const AdminPage = () => {

    const { queryObj, setSearch, moveRead, moveList } = useQueryObj()

    console.log("queryObj --------")
    console.log(queryObj)

    const movePage = (num) => {

        console.log("NUM ------------" + num)
        queryObj.page = num
        setSearch({ ...queryObj })
    }

    // const moveSearch = (type, keyword) => {
    //     queryObj.page = 1
    //     queryObj.type = type
    //     queryObj.keyword = keyword

    //     setSearch({ ...queryObj })
    // }

    return (
        <div>
            <h1>Admin Page</h1>
            <AdminComponent>
                queryObj={queryObj}
                movePage={movePage}
                moveRead={moveRead}
            </AdminComponent>
        </div>
    );
}

export default AdminPage;