import { useEffect, useState } from "react";
import { getProduct } from "../../api/productAPI";
import { useDispatch, useSelector } from "react-redux";
import { addCartThunk } from "../../reducers/cartSlice";

const initState = {
    pno: 0,
    pname: '',
    pdesc: '',
    price: 0,
    images: []
}

const ReadComponent = ({ pno, moveModify, moveList }) => {

    const {email} = useSelector(state => state.login)
    const dispatch = useDispatch()

    const [product, setProduct] = useState(initState)

    useEffect(() => {

        getProduct(pno).then(data => {
            setProduct(data)
        }).catch(e => {
            console.log(e)
        })
    }, [pno])

    return (
        <div>
            <div className="m-2 p-2 border-2">
                <h1>Input</h1>
                <div className="m-2 p-2 border-2">
                    {product.pname}
                </div>
                <div className="m-2 p-2 border-2">
                    {product.pdesc}
                </div>
                <div className="m-2 p-2 border-2">
                    {product.price}
                </div>
                <div className="m-2 p-2 border-2">
                    <ul className="list-none">
                        {product.images.map((fname, idx) =>
                            <li key={idx}>
                                <img src={`http://localhost/${fname}`} alt="빵통조림"></img>
                            </li>)}
                    </ul>
                </div>
                <div>
                    <button className="bg-blue-400 border-2 m-2 p-2 font-extrabold"
                        onClick={moveList}>
                        List
                    </button>
                    <button className="bg-orange-400 border-2 m-2 p-2 font-extrabold"
                        onClick={() => moveModify(product.pno)}>
                        Modify
                    </button>
                    <button
                        className="bg-blue-300 border-2 m-2 p-2 font-bold"
                        onClick={moveList}>
                        List
                    </button>
                    <button
                        className="bg-blue-300 border-2 m-2 p-2 text-white font-bold"
                        onClick={() => {
                            dispatch(addCartThunk({ email, pno }))
                        }}>
                        addCart
                    </button>
                </div>
            </div>
        </div>
    );
}

export default ReadComponent;