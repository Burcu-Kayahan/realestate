import React from "react";
import { Button, Col, Container, Image, Nav, Row } from "react-bootstrap";
import { config } from "../../helpers/config";
import "./footer.scss";
import { Link } from "react-router-dom";
import { FaApple, FaGooglePlay } from "react-icons/fa";

const Footer = () => {
  return (
    <footer>
      <Container>
        <Row className="g-5 d-flex justify-content-between">
          <Col lg={4}>
            <Link to="/">
              <Image
                src="/images/logo/logo-white.png"
                className="img-fluid"
                alt={config.project.name}
              />
            </Link>
            <p className="mt-3">{config.project.description}</p>
            <div className="d-flex">
              <Button variant="warning" className="fs-6 ">
                App Store <FaApple />{" "}
              </Button>
              <Button variant="warning" className="fs-6 mx-4">
                {" "}
                Google Play <FaGooglePlay />
              </Button></div>
        
          </Col>
          <Col md={4} >
            <h3>Quick Links</h3>

            <Nav className="flex-column ">
              <Nav.Link as={Link} to="/" className="link">
                Home
              </Nav.Link>
              <Nav.Link as={Link} to="/properties" className="link">
                Properties
              </Nav.Link>
              <Nav.Link as={Link} to="/about" className="link">
                About
              </Nav.Link>
              <Nav.Link as={Link} to="/contact" className="link">
                Contact
              </Nav.Link>
              <Nav.Link as={Link} to="/Privacy-Policy" className="link">
                Privacy Policy
              </Nav.Link>
            </Nav>
          </Col>

          <Col md={4}>
            <h3 >Contact</h3>

            <Nav className="flex-column">
              <Nav.Link href={config.contact.mapURL} target="_blank" className="link">
                {config.contact.address}
              </Nav.Link>
              <Nav.Link href={`tel:${config.contact.phone}`} className="link">
                {config.contact.phone}
              </Nav.Link>

              <Nav.Link href={`mailto:${config.contact.email}`} className="link">
                {config.contact.email}
              </Nav.Link>
            </Nav>
          </Col>
        </Row>
      </Container>
    </footer>
  );
};

export default Footer;
