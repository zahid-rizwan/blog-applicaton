import React from "react";
import Banner from "../components/Banner";
import NewFeed from "../components/NewFeed";

// import BlogPage from "../components/BlogPage";

const Home = () => {
  return (
    <div>
      <Banner />
      <div className="max-w-7xl mx-auto">
        <div className="flex flex-col lg:flex-row gap-12">
          <NewFeed />
        </div>
      </div>

      {/* <div ">
        <BlogPage />
      </div> */}
    </div>
  );
};

export default Home;
