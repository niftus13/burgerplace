import { useParams } from "react-router-dom";
import useQueryObj from "../../hooks/useQueryObj";
import ModifyComponent from "../../components/products/ModifyComponent";
import useCustomLogin from "../../hooks/useCustomLogin";


const ModifyPage = () => {

    const { loginInfo } = useCustomLogin((nav) => {
        nav('../list')
    })
    const { queryObj, setSearch, moveRead, moveList, moveModify } = useQueryObj()
    const { pno } = useParams()


    return (
        <div className="bg-slate-500">
            <div className="text-2xl pb-2 text-center text-white">MODIFY PAGE  {pno}
            </div>
            <div className="text-2xl text-black">
            <ModifyComponent
                pno={pno}
                moveList={moveList}
                moveRead={moveRead}>
            </ModifyComponent>
            </div>
        </div>
    );
}

export default ModifyPage;