import { useEffect, useRef, useState } from "react"
import { deleteProduct, getProduct, putProduct } from "../../api/productAPI"

const initState = {
    pno: 0,
    pname: '',
    pdesc: '',
    price: 0,
    images: []
}



const ModifyComponent = ({ pno, moveList, moveRead }) => {

    const fileRef = useRef()
    const [product, setProduct] = useState(initState)

    useEffect(() => {

        getProduct(pno).then(data => {
            setProduct(data)
        })


    }, [pno])
    
    const handleClickDelete = () => {

        deleteProduct(pno).then(data => {
            alert("상품삭제")
            moveList()
        })
    }
    const handleChage = (e) => {

        product[e.target.name] = e.target.value
        setProduct({ ...product })
    }

    const handleClickModify = () => {

        const formData = new FormData();
        formData.append("pno",product.pno)
        formData.append("pname", product.pname)
        formData.append("pdesc", product.pdesc)
        formData.append("price", product.price)

        if (product.images) {
            for (let pi of product.images) {
                formData.append("images", pi)
            }
        }

        const arr = fileRef.current.files

        for (let file of arr) {
            formData.append("files", file)
        }
        putProduct(formData).then(data=>{
            console.log(data)
            alert(pno +"게시글이 수정되었습니다.")
            moveRead(pno)
        })

    }
    const handleClickDelImg = (fname)=>{

        const newArr = product.images.filter(ele => ele !== fname)

        product.images = newArr

        setProduct({...product})
    }


    return (

        <div>

            <div className="m-2 p-2 text-white font-bold">
                <div className="m-2 p-2">
                    {product.pno}
                </div>
                <div className="m-2 p-2 ">
                    <input
                        className="bg-black"
                        type="text"
                        name="pname"
                        value={product.pname}
                        onChange={handleChage}
                    ></input>
                </div>
                <div className="m-2 p-2 ">
                    <input
                        className="bg-black"
                        type="text"
                        name="pdesc"
                        value={product.pdesc}
                        onChange={handleChage}
                    ></input>
                </div>
                <div className="m-2 p-2 ">
                    <input
                        className="bg-black"
                        type="number"
                        name="price"
                        value={product.price}
                        onChange={handleChage}
                    ></input>
                </div>
                <div className="m-2 p-2">
                    <input className=" border-2 border-gray-500" type="file" ref={fileRef} multiple name="images" ></input>
                </div>
                <div className="m-2 p-2 ">
                    <ul className="list-none flex">
                        {product.images.map((fname, idx) =>
                            <li key={idx} className="m-2">
                                <img src={`http://localhost/s_${fname}`} alt="No image"></img>
                                <button className="bg-red-500 m-2 p-2" onClick={()=>handleClickDelImg(fname)}>X</button>
                            </li>)}
                    </ul>
                </div>
            </div>

            <div>
                <button
                    className="bg-amber-400 border-2 m-2 p-2 text-white font-bold"
                    onClick={handleClickModify}
                >
                    Modify
                </button>
                <button
                    className="bg-sky-400 border-2 m-2 p-2 text-white font-bold"
                    onClick={moveList}
                >
                    List
                </button>
                <button
                    className="bg-red-400 border-2 m-2 p-2 text-white font-bold"
                    onClick={handleClickDelete}
                >
                    Delete
                </button>

                

            </div>
        </div>

    );
}

export default ModifyComponent;