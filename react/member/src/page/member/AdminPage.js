import React from 'react';

const AdminPage = ({memberList}) => {

    return (
        <div>
            <h1>Admin Page</h1>
            <ul>
                {memberList.map(member => (
                    <li key={member.id}>
                        {member.nickname}
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default AdminPage;