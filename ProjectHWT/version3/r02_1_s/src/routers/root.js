import { createBrowserRouter } from"react-router-dom";

import MainPage from "../pages/MainPage";
import AboutPage from "../pages/AboutPage";

// Code Splitting  시작
import { Suspense, lazy } from "react";
import LoadingPage from "../pages/LoadingPage";

const  Loading = <LoadingPage></LoadingPage>
const Board_Index = lazy(()=> import("../pages/board/IndexPage"))
const Board_List = lazy(()=> import("../pages/board/ListPage"))
const Board_Read = lazy(()=> import("../pages/board/ReadPage"))
const Board_Register = lazy(()=> import("../pages/board/RegisterPage"))
const Board_Modify = lazy(()=> import("../pages/board/ModifyPage"))

const TradeBoard_Index = lazy(() => import("../pages/TradeBoard/TradeIndexPage"))
const TradeBoard_List = lazy(() => import("../pages/TradeBoard/TradeListPage"))
const TradeBoard_Read = lazy(() => import("../pages/TradeBoard/TradeReadPage"))
const TradeBoard_Register = lazy(() => import("../pages/TradeBoard/TradeRegisterPage"))
const TradeBoard_Modify = lazy(() => import("../pages/TradeBoard/TradeModifyPage"))


const Member_Login =  lazy(()=> import("../pages/member/LoginPage"))



const router = createBrowserRouter([
    {
        path: "",
        element: <MainPage></MainPage>
    },
    {
        path: "about",
        element: <AboutPage></AboutPage>
    },
    {
        path: "member/login",
        element: <Suspense fallback={Loading}><Member_Login/></Suspense>,
    },
    {
        path: "board",
        element: <Suspense fallback={Loading}><Board_Index/></Suspense>,
        children: [
            {
                path: "list",
                element: <Suspense fallback={Loading}><Board_List/></Suspense>
            },
            {
                path: "read/:freeBno",
                element: <Suspense fallback={Loading}><Board_Read/></Suspense>
            },
            {
                path:"register",
                element : <Suspense fallback={Loading}><Board_Register></Board_Register></Suspense>
            },
            {
                path : "modify/:freeBno",
                element : <Suspense fallback={Loading}><Board_Modify></Board_Modify></Suspense>
            }

        ]
    },

    {
        path : "tboard",
        element : <Suspense fallback={Loading}><TradeBoard_Index></TradeBoard_Index></Suspense>,
        children : [
            {
                path : "list",
                element : <Suspense fallback={Loading}><TradeBoard_List></TradeBoard_List></Suspense>
            },
            {
                path : "read/:tradeBno",
                element : <Suspense fallback={Loading}><TradeBoard_Read></TradeBoard_Read></Suspense>
            },
            {
                path : "register",
                element : <Suspense fallback={Loading}><TradeBoard_Register></TradeBoard_Register></Suspense>
            },
            {
                path : "modify/:tradeBno",
                element : <Suspense fallback={Loading}><TradeBoard_Modify></TradeBoard_Modify></Suspense>
            }
        ]
    }



])

export default router;