import React, { useEffect, useState } from "react";
import { ButtonGroup, Container, Image, Nav, Navbar, Offcanvas } from "react-bootstrap";
import { config } from "../../helpers/config";
import { Link } from "react-router-dom";

import UserMenu from "./user-menu";

const Menubar = () => {
  const [mode, setMode] = useState("white");
  const [showOffcanvas, setShowOffcanvas] = useState(false);

  const handleScroll = () => {
    const scrollYPosition = window.scrollY;
    if (scrollYPosition > 250) {
      setMode("dark");
    } else {
      setMode("white");
    }
  };


  const closeOffcanvas = () => {
    setShowOffcanvas(false);
  };



  useEffect(() => {
    window.addEventListener("scroll", handleScroll);

    return () => {
      window.removeEventListener("scroll", handleScroll);
    };
  }, []);

  return (
    <Navbar
      expand="lg"
      className={`menubar bg-${mode}`}
      sticky="top"
      data-bs-theme={mode}
    >
      <Container>
        <Navbar.Brand as={Link} to="/" title={config.project.name}>
          <Image
            src={`/images/logo/${mode === "white" ? "logo" : "logo-white"}.png`}
            className="img-fluid"
            alt={config.project.name}
          />
        </Navbar.Brand>
        <Navbar.Toggle aria-controls={`offcanvasNavbar-expand-lg`} onClick={() => setShowOffcanvas(!showOffcanvas)}
        /> 
        <Navbar.Offcanvas
          id={`offcanvasNavbar-expand-lg`}
          aria-labelledby={`offcanvasNavbarLabel-expand-lg`}
          placement="end"
          show={showOffcanvas}
        >
          <Offcanvas.Header closeButton>
            <Offcanvas.Title id={`offcanvasNavbarLabel-expand-lg`}>
              <Image
                src="/images/logo/logo.png"
                className="img-fluid"
                alt={config.project.name}
              />
            </Offcanvas.Title>
          </Offcanvas.Header>
          <Offcanvas.Body>
            <Nav className="justify-content-center flex-grow-1 pe-3 text-primary">
              <Nav.Link as={Link} to="/" onClick={closeOffcanvas} >
                {" "}
                Home
              </Nav.Link>
              <Nav.Link as={Link} to="/properties" onClick={closeOffcanvas}>
                {" "}
                Properties
              </Nav.Link>
              <Nav.Link as={Link} to="/about" onClick={closeOffcanvas}>
                {" "}
                About
              </Nav.Link>
              <Nav.Link as={Link} to="/contact" onClick={closeOffcanvas}>
                {" "}
                Contact
              </Nav.Link>
            </Nav>
           

           <div className="m-lg-auto my-3" onClick={closeOffcanvas}>
              <UserMenu/>
           </div>
         
            
            
      
           
         
            <a
              
              href="/add-new-advert"
              className={`btn btn-outline-success  mx-lg-3`}
            >
              Add Property ---
            </a>
          </Offcanvas.Body>
        </Navbar.Offcanvas>
      </Container>
    </Navbar>
  );
};

export default Menubar;
