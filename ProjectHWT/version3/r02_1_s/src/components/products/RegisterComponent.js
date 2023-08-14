import { useRef, useState } from "react";
import { postProduct } from "../../api/productAPI";

const initState = {
    pname: 'Ice Coffee',
    pdesc: 'Coffee..... ',
    price: 4000

}

const RegisterComponent = ({ moveList }) => {

    // document.getElementById 처럼 쓰고싶을때 사용
    const fileRef = useRef()

    const [product, setProduct] = useState({ ...initState })

    const handleChage = (e) => {

        product[e.target.name] = e.target.value
        setProduct({ ...product })
    }
    
    const handleClickSave = (e) => {

        const formData = new FormData();

        formData.append("pname", product.pname)
        formData.append("pdesc", product.pdesc)
        formData.append("price", product.price)

        console.dir(fileRef.current)

        const arr = fileRef.current.files

        for (let file of arr) {
            formData.append("files", file)
        }

        postProduct(formData).then(data => {

            const rno = data.result
            alert(`${rno}번 상품이 등록되었습니다.`)

            moveList()
        })

    }
    const handleClickClear = (e) => {

        fileRef.current.value = ''
    }

    return (
        <div className="m-2 p-2 text-white">
            <div className="m-2 p-2">
                상품명 :  <input className=" bg-black border-2 border-gray-500" type="text" name="pname" value={product.pname} onChange={handleChage}></input>
            </div>
            <div className="m-2 p-2 ">
                상품설명: <input className="bg-black border-2 border-gray-500" type="text" name="pdesc" value={product.pdesc} onChange={handleChage}></input>
            </div>
            <div className="m-2 p-2 ">
                가격:   <input className="bg-black border-2 border-gray-500" type="number" name="price" value={product.price} onChange={handleChage} ></input>
            </div>
            {/* 첨부파일은 기존의 처리방식과 확연히 달라진다. 
                처리시에 ref를 처리한다.
                Form data만들어서 처리해주어야된다.
            */}
            <div className="m-2 p-2">
                <input className=" border-2 border-gray-500" type="file" ref={fileRef} multiple name="images" onChange={handleChage} ></input>
            </div>
            <div className="m-2 p-2">
                <button className="m-2 p-2 border-2 border-gray-500 " onClick={handleClickSave}>SAVE</button>
                <button className="m-2 p-2 border-2 border-gray-500 " onClick={handleClickClear}>CLEARFILES</button>
            </div>
        </div>
    );
}

export default RegisterComponent;