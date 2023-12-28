import React from "react";
import { useSelector } from "react-redux";
import { Navigate } from "react-router-dom";

const PrivateRoute = ({ children, roles }) => {
  const { isUserLogin, user } = useSelector((state) => state.auth);


  const isUserLoginLocal = localStorage.getItem("isUserLogin" || false);
  const userLocal = JSON.parse(localStorage.getItem("user" || false));
  if(roles===null){
    roles=userLocal.role;
  }
  if (!isUserLoginLocal) return <Navigate to="/login" />;
  if (!roles || !Array.isArray(roles) || !roles.includes(userLocal.role))
    return <Navigate to="/unauthorized" />;

  return children;
};

export default PrivateRoute;
