import { createBrowserRouter } from "react-router-dom";
import MainPage from "../page/MainPage";
import AdminPage from "../page/admin/AdminPage";

const router = createBrowserRouter([
    {
        path:"",
        element: <MainPage></MainPage>
    },
    {
        path:"admin/members",
        element: <AdminPage></AdminPage>
    }
])

export default router;