import countSlice from "../reducers/countSlice";
import todoSlice from "../reducers/todoSlice";
import loginSlice from "../reducers/loginSlice";
import cartSlice from "../reducers/cartSlice";

const { configureStore } = require("@reduxjs/toolkit");

export default configureStore({
    reducer: { 
        counter: countSlice,
        todo: todoSlice,
        login: loginSlice,
        cart: cartSlice
     }
})

