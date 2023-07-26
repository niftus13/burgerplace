import { Cookies } from "react-cookie";


const cookies = new Cookies()

export const setCookie = (cookieName, value, days) =>{

    const expires = new Date()
    expires.setUTCDate(expires.getUTCDate() + days)

    cookies.set(cookieName, value, { path: "/", expires: expires})

}

export const getCookies = (cookieName) => {

    return cookies.get(cookieName)
}

export const removeCookies = (cookieName, path="/") => {

    cookies.remove(cookieName, {path:path})
}