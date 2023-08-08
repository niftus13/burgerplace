import React from 'react';
import AdminComponent from '../../components/member/AdminComponent';
import BasicLayout from '../../layouts/BasicLayout';

const AdminPage = ({ memberList }) => {

    return (
        <div>
            <BasicLayout>
                <h1>Admin Page</h1>
                <AdminComponent></AdminComponent>
            </BasicLayout>
        </div>
    );
}

export default AdminPage;