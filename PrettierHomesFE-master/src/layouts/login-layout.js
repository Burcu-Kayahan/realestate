import React, { useEffect, useState } from "react";

import { Outlet, useLocation } from "react-router-dom";
import ScrollToTopButton from "../components/common/scroll-to-top-button";
import MenubarLogin from "../components/common/menubar-login";
import { useDispatch, useSelector } from "react-redux";
import { Button, Nav, Offcanvas, Row, Col } from "react-bootstrap";
import { AiOutlineUser, AiOutlineMenu } from "react-icons/ai";
import { Link, useNavigate } from "react-router-dom";
import { logout } from "../store/slices/auth-slice";
import { removeFromLocalStorage } from "../helpers/functions/encrypted-storage";
import { swalConfirm } from "../helpers/functions/swal";
import { FcPrevious } from "react-icons/fc";
import "./login-layout.scss";
import { getMenuItems } from "../helpers/functions/user-menu";
const LoginLayout = () => {
  const { pathname } = useLocation();
  const { isUserLogin, user} = useSelector((state) => state.auth);
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const [showMenu, setShowMenu] = useState(false);
  const [isVisible, setIsVisible] = useState(true);
   const isUserLoginLocal = localStorage.getItem("isUserLogin" || false);
   const userLocal = JSON.parse(localStorage.getItem("user" || false));
  const [menu, setMenu] = useState(getMenuItems(userLocal?.role));
  const handleMenuToggle = () => {
    setIsVisible(!isVisible);
  };

  const handleClose = () => setShowMenu(false);
  const handleOpen = () => setShowMenu(true);

  const handleMenuClick = (link) => {
    navigate(link);
    handleClose();
  };

  const handleLogout = async () => {
    const resp = await swalConfirm("Are you sure to logout?", "");
    if (!resp.isConfirmed) return;

    dispatch(logout());
    removeFromLocalStorage("token");
    navigate("/");
  };

  


  useEffect(() => {
    window.document.documentElement.scrollTo({ top: 0 });
  }, [pathname]);

  return (
    <div className="login-layout">
    
      <Row>
     {!isVisible &&
        <Col  className={`menu  p-5`}>
          <img
            src="/images/logo/logo-white-2.png"
            className="d-block align-top"
            alt="Prettier Homes logo"
          />
          <Nav className="flex-column  px-4 py-3 fs-5">
            <Nav.Link
              className="text-dark"
              onClick={() => {
                handleMenuClick("/dashboard");
                handleMenuToggle();
              }}
            >
              Dashboard
            </Nav.Link>

            {menu.map((item) => (
              <Nav.Link
                className="text-dark"
                key={item.title}
                onClick={() => {
                  handleMenuClick(item.link);
                  handleMenuToggle();}}
             >
                {item.title}
              </Nav.Link>
            ))}

            <Nav.Link className="text-dark " onClick={handleLogout}>
              Logout
            </Nav.Link>
          </Nav>
        </Col>
        }
        <Col  className="p-0">
          <Col>
            <div className="bg-primary ">
              <Button variant="primary" size="lg" onClick={handleMenuToggle}>
                <AiOutlineMenu />
              </Button>
            
            </div>
          </Col>
          <Col>
            <Outlet />
          </Col>
        </Col>

      
      </Row>
    </div>
  );
};
export default LoginLayout;
