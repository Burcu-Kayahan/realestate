import React, { useEffect } from "react";
import Menubar from "../components/common/menubar";
import { Outlet, useLocation } from "react-router-dom";
import ScrollToTopButton from "../components/common/scroll-to-top-button";
import Footer from "../components/common/footer";

const UserLayout = () => {
  const { pathname } = useLocation();

  useEffect(() => {
    window.document.documentElement.scrollTo({ top: 0 });
  }, [pathname]);

  return (
    <>
      <Menubar />
      <Outlet />
      <Footer />
      <ScrollToTopButton />
    </>
  );
};

export default UserLayout;
