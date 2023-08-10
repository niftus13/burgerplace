import { createBrowserRouter } from "react-router-dom";
import MainPage from "../page/MainPage";
import { lazy } from "react";
import { Suspense } from "react";


const AdminIndex = lazy(() => import("../page/admin/AdminIndexPage"))
const AdminPage = lazy(() => import("../page/admin/AdminPage")) 
const AdminRead = lazy(() => import("../page/admin/AdminReadPage"))


const router = createBrowserRouter([
    {
        path:"",
        element: <MainPage></MainPage>
    },
    {
        path:"admin",
        element: <Suspense><AdminIndex></AdminIndex></Suspense>,
        children: [
            {
                path: "list",
                element:<Suspense><AdminPage></AdminPage></Suspense>
            },
            {
                path: "read/:id",
                element:<Suspense><AdminRead></AdminRead></Suspense>
            }
        ]
    }
])

export default router;