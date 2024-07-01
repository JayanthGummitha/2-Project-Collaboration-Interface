import { API_BASE_URL } from "@/config/api";
import { GET_USER_REQUEST, GET_USER_SUCCESS, LOGIN_REQUEST, LOGOUT, REGISTER_REQUEST, REGISTER_SUCCESS } from "./ActionTypes"
import axios from "axios";


export const register = userData => async (dispath) => {
    dispath({ type: REGISTER_REQUEST })
    try {
        const { data } = await axios.post(`${API_BASE_URL}/auth/signup`, userData);
        if (data.jwt) {
            localStorage.setItem("jwt", data.jwt)
            dispath({ type: REGISTER_SUCCESS, payload: data });
        }
        console.log("register success", data)
    } catch (error) {
        console.log(error)
    };
};
export const login = userData => async (dispath) => {
    dispath({ type: LOGIN_REQUEST })
    try {
        const { data } = await axios.post(`${API_BASE_URL}/auth/signing`, userData);
        if (data.jwt) {
            localStorage.setItem("jwt", data.jwt)
            dispath({ type: REGISTER_SUCCESS, payload: data })
        }
        console.log("login succes", data)
    } catch (error) {
        console.log(error)
    }
}
export const getUser = () => async (dispath) => {
    dispath({ type: GET_USER_REQUEST });
    try {
        const { data } = await axios.get(`${API_BASE_URL}/api/users/profile`,
            {
                headers: {
                    Authorization: `Bearer ${localStorage.getItem("jwt")}`,
                }
            }
        );
       
        dispath({ type: GET_USER_SUCCESS, payload: data })
        console.log("user succes", data)
    } catch (error) {
        console.log(error)
    }
}

export const logout = () => async (dispath) => {
    dispath({
        type: LOGOUT
    })
    localStorage.clear();

}