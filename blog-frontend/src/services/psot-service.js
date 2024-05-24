import { privateAxios } from "./helper"
import { myAxios } from "./helper"
export const createPost=(postData)=>{
    // console.log(postData)
    return privateAxios.post(
        `/api/user/${postData.userId}/category/${postData.categoryId}/posts`,postData
    )
    .then(response=>response.data)
}

//Get all post
export const loadAllPosts=()=>{
    return myAxios.get('/api/posts').then((response)=>response.data)
}
