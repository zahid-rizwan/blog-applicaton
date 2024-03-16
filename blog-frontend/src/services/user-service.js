// import axios from "axios";
import { myAxios } from "./helper";
export const signup=async(user)=>{
    return myAxios.post('/api/auth/register',user)
    .then((response)=>response.data)
    // try{
    //     const response=await axios.post('http://localhost:9090/api/auth/register',user);
    //     console.log(response)
    //     if(response.status===201){
    //         return response.data
    //     }
    // }catch(error){
    //     console.log(error);
        
    // }
   
}