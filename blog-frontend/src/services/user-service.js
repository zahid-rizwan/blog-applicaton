import { myAxios } from "./helper";
export const signup=(user)=>{
    return myAxios.post('/api/auth/register')
    .then((response)=>response.json())
}