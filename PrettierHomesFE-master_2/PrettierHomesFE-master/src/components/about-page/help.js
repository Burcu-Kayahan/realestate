import React from "react";
import { Button, Col, Container, Row } from "react-bootstrap";
import { FiPhone } from "react-icons/fi";
import { BsArrowUpRight } from "react-icons/bs";

const Help = () => {
  return (
    <Container>
      <Row >
        <Col md={6}>
          <h2> Need help? Talk to our expert .</h2>
          <span>Talk to our experts or Browse through more properties</span>
        </Col>
        <Col md={6} className=" d-flex  g-5">
        <Button variant="outline-primary" className="w-50 mx-3 rounded-5 text-dark" >Contact Us <BsArrowUpRight /></Button>
        <Button variant="secondary"  className="w-50 mx-3 rounded-5"><FiPhone /> Contact Us</Button>
        

        </Col>
        </Row>
    </Container>
  );
};

export default Help;
