import { useRef, useState } from "react";
import { postProduct } from "../../api/productAPI";


const initState = {
    pname:'Ice Coffee',
    pdesc:'Coffee....',
    price:4000
}

const RegisterComponent = ({moveList}) => {

    const fileRef = useRef()
    const [product, setProduct] = useState({...initState})

    const handleChange = (e) => {
        product[e.target.name] = e.target.value
        setProduct({...product})
    }

    const handleClickSave = (e) => {
        const formData = new FormData()

        formData.append("pname", product.pname)
        formData.append("pdesc", product.pdesc)
        formData.append("price", product.price)

        console.dir(fileRef.current)

        const arr = fileRef.current.files

        for(let file of arr) {
            formData.append("files", file)
        }

        postProduct(formData).then(data => {
            const rno = data.result
            alert(`${rno} 번이 등록되었습니다.`)
            moveList()
        })
        
        
    }

    const handleCilckClear = (e) => {
        fileRef.current.value = ''
    }




    return ( 
        <div className="bg-slate-400 text-2xl text-white">
            <h1>Input</h1>
            <div className="m-2 p-2">
                <input type="text" name="pname" value={product.pname} onChange={handleChange}></input>
            </div>
            <div className="m-2 p-2">
                <input type="text" name="pdesc" value={product.pdesc} onChange={handleChange}></input>
            </div>
            <div className="m-2 p-2">
                <input type="number" name="price" value={product.price} onChange={handleChange}></input>
            </div>
            <div className="m-2 p-2">
                <input type="file" name="images" ref={fileRef} onChange={handleChange} multiple></input>
            </div>
            <div>
                <button onClick={handleClickSave} className="m-2 p-2">SAVE</button>
                <button onClick={handleCilckClear} className="m-2 p-2">CLEAR FILES</button>
            </div>
        </div>
     );
}
 
export default RegisterComponent;