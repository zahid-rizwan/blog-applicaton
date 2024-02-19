import React, { useState } from "react";
import blogData from "../model/BlogData";
import BlogCards from "./BlogCards";
import Pagination from "./Pagination";
import CategorySelecton from "./CategorySelecton";
import SideBar from "./SideBar";

const BlogPage = () => {
  const [blogs, setBlogs] = useState(blogData);
  const [currentPage, setCurrentPage] = useState(1);
  const pageSize = 12;
  const [selectedCategory, setSelectedCategory] = useState(null);
  const [activeCategory, setActiveCategory] = useState(null);
  const handlePageChange = (pageNumber) => {
    setCurrentPage(pageNumber);
  };
  const handleCategoryChange = (category) => {
    setSelectedCategory(category);
    setCurrentPage(1);
    setActiveCategory(category);
  };
  return (
    <div>
      <div>
        <CategorySelecton
          onSelectCategory={handleCategoryChange}
          selectedCategory={selectedCategory}
          activeCategory={activeCategory}
        />
      </div>
      <div className="flex flex-col lg:flex-row gap-12">
        <BlogCards
          blogs={blogs}
          currentPage={currentPage}
          selectedCategory={selectedCategory}
          pageSize={pageSize}
        />
        <div>
          <SideBar/>
        </div>
      </div>
      <div>
        <Pagination
          onPageChange={handlePageChange}
          currentPage={currentPage}
          blogs={blogs}
          pageSize={pageSize}
        />
      </div>
    </div>
  );
};

export default BlogPage;
