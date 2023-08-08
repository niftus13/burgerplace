import React from 'react';
import AdminComponent from '../../components/admin/AdminComponent';
import BasicLayout from '../../layouts/BasicLayout';

const AdminPage = () => {

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