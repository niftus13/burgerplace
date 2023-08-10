import { createBrowserRouter } from "react-router-dom";
import MainPage from "../page/MainPage";
import { lazy } from "react";
import LoadingPage from "../page/LoadingPage";
import { Suspense } from "react";


const Loading = <LoadingPage></LoadingPage>
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
        element: <Suspense fallback={Loading}><AdminIndex></AdminIndex></Suspense>,
        children: [
            {
                path: "list",
                element:<Suspense fallback={Loading}><AdminPage></AdminPage></Suspense>
            },
            {
                path: ":id",
                element:<Suspense fallback={Loading}><AdminRead></AdminRead></Suspense>
            }
        ]
    }
])

export default router;