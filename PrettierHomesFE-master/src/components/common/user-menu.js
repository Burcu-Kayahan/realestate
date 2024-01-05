import React from "react";
import {  useSelector } from "react-redux";
import { AiOutlineUser } from "react-icons/ai";
import { Link } from "react-router-dom";
import "./user-menu.scss";

const UserMenu = () => {
  const { isUserLogin, user } = useSelector((state) => state.auth);
  // const isUserLoginLocal = localStorage.getItem("isUserLogin") === "true";
  const userLocal = JSON.parse(localStorage.getItem("user"));
  
  return (
    <>
      <div className="user-menu ">
        {isUserLogin ? (
          <Link to="/dashboard">
            <AiOutlineUser /> Hello, {userLocal?.firstName}
            👋
          </Link>
        ) : (
          <Link to="/login">
            <AiOutlineUser /> Login
          </Link>
        )}
      </div>
    </>
  );
};

export default UserMenu;
