import React from "react";
import { loadAllPosts } from "../services/psot-service";
import { useState, useEffect } from "react";
import Post from "./Post";
import { Button, IconButton } from "@material-tailwind/react";
import { toast } from "react-toastify";

const NewFeed = () => {
  const [active, setActive] = React.useState(1);
  const [postContent, setPostContent] = useState({
    content: [],
    totalPages: "",
    totalElements: "",
    pageSize: "",
    lastPage:false,
    pageNumber: "",
  });

  useEffect(() => {
    loadAllPosts(0,15)
      .then((data) => {
        console.log(data);
        setPostContent(data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);
  const changePage=(pageNumber=0,pageSize=10)=>{
    loadAllPosts(pageNumber,pageSize).then((data)=>{
      setPostContent(data)
      setActive(pageNumber+1)
    }).catch(error=>{
      toast.error("Error in loading posts")
    })
  }
 
  
  const getItemProps = (index) =>
    ({
      variant: active === index ? "filled" : "text",
      color: "gray",
      onClick: () => setActive(index),
    });
  
  

  return (
    <div>
      <h1>Blog Count({postContent?.totalElements})</h1>
      <div>
        <div className="grid md:grid-cols-3 sm:grid-cols-2 grid-cols-1 gap-8">
          {postContent.content.map((post,index) => (
            <Post post={post} key={index} />
          ))}
        </div>
      </div>
      <div className="flex items-center gap-4">
        <Button
          variant="text"
          className="flex items-center gap-2"
          onClick={()=>changePage(--postContent.pageNumber)}
          disabled={postContent.pageNumber==0}
        >
          Previous
          {/* <ArrowLeftIcon strokeWidth={2} className="h-4 w-4" /> Previous */}
        </Button>
        <div className="flex items-center gap-2">
        {
          [...Array(postContent.totalPages)].map((item,index)=>(
            <IconButton 
            {...getItemProps(index+1)} 
            key={index}
            onClick={()=>changePage(index,10) }
            >{index + 1}</IconButton>
          ))
        }
          {/* <IconButton {...getItemProps(2)}>2</IconButton>
        <IconButton {...getItemProps(3)}>3</IconButton>
        <IconButton {...getItemProps(4)}>4</IconButton>
        <IconButton {...getItemProps(5)}>5</IconButton> */}
        </div>
        <Button
          variant="text"
          className="flex items-center gap-2"
          onClick={()=>changePage(++postContent.pageNumber)}
          disabled={postContent.lastPage }
        >
          Next
          {/* <ArrowRightIcon strokeWidth={2} className="h-4 w-4" /> */}
        </Button>
      </div>
    </div>
  );
};

export default NewFeed;
