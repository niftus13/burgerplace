import { useEffect, useState } from "react";
import { getProduct } from "../../api/productAPI";

const initState = {
    pno: 0,
    pname: '',
    pdesc: '',
    price: 0,
    images: []
}



const ReadComponent = ({ pno, moveModify, moveList }) => {

    const [product, setProduct] = useState(initState)

    useEffect(() => {

        getProduct(pno).then(data => {
            setProduct(data)
        }).catch(e =>{
            console.log(e)
            moveList()
        })


    }, [pno])

    return (
        <div>
            <div className="m-2 p-2 text-white font-bold">
                <div className="m-2 p-2 text-3xl">
                    <span>  상품명 :  {product.pname}</span>
                </div>
                <div className="m-2 p-2 text-3xl">
                    <span>상품설명: {product.pdesc} </span>
                </div>
                <div className="m-2 p-2 text-3xl">
                    <span> 가격:   {product.price} </span>
                </div>
                <div className="m-2 p-2 ">
                    <ul className="list-none ">
                        {product.images.map((fname, idx) =>
                            <li key={idx}
                            className=" w-[50vh]"
                            >
                                <img 
                                src={`http://localhost/${fname}`} alt="No image"></img>
                            </li>)}
                    </ul>
                </div>
                <div>
                    <button
                        className="bg-sky-400 border-2 m-2 p-2 text-white font-bold"
                        onClick={moveList}
                    >List</button>
                    <button
                        className="bg-amber-400 border-2 m-2 p-2 text-white font-bold"
                        onClick={() => moveModify(product.pno)}
                    >modify</button>

                </div>
            </div>

        </div>
    );
}

export default ReadComponent;