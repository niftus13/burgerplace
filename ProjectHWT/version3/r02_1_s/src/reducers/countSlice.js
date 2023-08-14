import { createSlice } from "@reduxjs/toolkit";

// store와 reducer를 연결 역활 = createSlice
const countSlice = createSlice({

    name: "CountSlice",
    initialState: {num: 5},

    //  reducesrs 동기화처리 extraReducers는 비동기화처리
    reducers: {
        // 파라미터로 상태와 전달하려는 값을 던진다.
        // 다양한 데이터를 던져 줄려면 객체로 만들어서 던져줘야된다.
        inc: (state, param, third) => {
            console.log(state)
            console.log(param)
            console.log(third)
            console.log("------------------------")
            return {num: state.num + param.payload}
            
        },
        dec: (state, param, third) => {
            console.log(state)
            console.log(param)
            console.log(third)
            console.log("------------------------")
            return {num: state.num - param.payload}
        }
    }
})
//  reducers 내부에 들어 가있으면 actions 내부
export const {inc, dec} = countSlice.actions
// 실제로 노출되는 부분
export default countSlice.reducer