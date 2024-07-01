import api from "@/config/api";
import * as actionTypes  from "./ActionTypes"

export const  getUserSubscription=()=>{
    return async(dispatch)=>{
        dispatch({type:actionTypes.GET_USER_SUBSCRIPTION_REQUEST});
        try{
            const response =await api.get(`/api/subscriptions/user`,{
                headers:{
                    "Authorization":`Bearer ${localStorage.getItem("jwt")}`
                },
            });
            dispatch({
                type:actionTypes.GET_USER_SUBSCRIPTION_SUCCESS,
                payload: response.data,
            })
            console.log("user subscription ",response.data)
        }
        catch(error){
            console.log("error = ",error);
            dispatch({
                type:actionTypes.GET_USER_SUBSCRIPTION_FAILURE,
                payload: error.message,
            });
        }
    };
} 

export const  upgradeSubscription=({planType})=>{
    return async(dispatch)=>{
        dispatch({type: actionTypes.UPGRADE_SUBSCRIPTION_REQUEST});
        try{
            const response =await api.patch("/api/subscriptions/upgrade",null,{
                headers:{
                    "Authorization":`Bearer ${localStorage.getItem("jwt")}`
                },
                params:{
                     planType: planType,
                }
            });
            dispatch({
                type:actionTypes.UPGRADE_SUBSCRIPTION_SUCCESS,
                payload: response.data,
            })
            console.log("upgraded subscription",response.data);
        }
        catch(error){
            console.log("error = ",error.response.data);
            dispatch({
                type:actionTypes.UPGRADE_SUBSCRIPTION_FAILURE,
                payload: error.message,
            });
        }
    };
} 