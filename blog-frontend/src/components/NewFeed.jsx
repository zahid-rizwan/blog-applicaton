import React from "react";
import { loadAllPosts } from "../services/psot-service";
import { useState, useEffect } from "react";
import Post from "./Post";

const NewFeed = () => {
  const [postContent, setPostContent] = useState({
    content:[]
  });
  useEffect(() => {
    loadAllPosts()
      .then((data) => {
        console.log(data);
        setPostContent(data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);
  return (
    <div>
      <h1>Blog Count({postContent?.totalElements})</h1>
      <div className="grid grid-cols-1 gap-5">
        <div>
         {
          postContent.content.map((post)=>(
           
            <Post post={post} key={post.postId}/>
            
          ))

         }
        </div>
      </div>
    </div>
  );
}

export default NewFeed;
