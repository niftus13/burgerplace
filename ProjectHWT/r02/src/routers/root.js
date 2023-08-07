import {createBrowserRouter} from "react-router-dom";
import MainPage from "../pages/MainPage";
import AboutPage from "../pages/AboutPage";


import { Suspense, lazy } from "react";


const Loading = <LoadingPage></LoadingPage>
const Board_Index = lazy(() => import("../pages/board/IndexPage"))
const Free_Board_List = lazy(() => import("../pages/board/FreeListPage"))
const Free_Board_Read = lazy(() => import("../pages/board/FreeReadPage"))

const Trade_Board_List = lazy(()=> import("../pages/board/FreeListPage"))
const Trade_Board_Read = lazy(()=> import("../pages/board/FreeReadPage"))



const router = createBrowserRouter([
  {
    path:"",
    element: <MainPage></MainPage>
  },
  {
    path: "about",
    element: <AboutPage></AboutPage>
  },



  {
    path: "fboard",
    element: <Suspense fallback={Loading}><Board_Index/></Suspense>,
    children: [
      {
        path: "list",
        element: <Suspense fallback={Loading}><Free_Board_List/></Suspense>
      },
      {
        path: "read/:fBno",
        element: <Suspense fallback={Loading}><Free_Board_Read/></Suspense>
      }
    ]
  },

  {
    path: "tboard",
    element: <Suspense fallback={Loading}><Board_Index/></Suspense>,
    children: [
      {
        path: "list",
        element: <Suspense fallback={Loading}><Trade_Board_List/></Suspense>
      },
      {
        path: "read/:tBno",
        element: <Suspense fallback={Loading}><Trade_Board_Read/></Suspense>
      }
    ]
  },



  

])

export default router;