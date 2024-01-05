import React from "react";
import { Col, Image, Row } from "react-bootstrap";
import "./dream-house.scss"
const DreamHouse = () => {
  return (
    <div className="dream-house ">
      <Row>
        <Col lg={6}>
          {" "}
          <div className="p-5 m-lg-5">
            <h2>Get your dream house </h2>
            <p className="pb-3">
              Turn your aspirations into reality with 'Get Your Dream House
              where your perfect home becomes a possibility.
            </p>
            <button className="btn btn-info text-light"> Register Now</button>
          </div>
        </Col>
        <Col lg={6}>
          <Image
            src="/images/dream-house/highlight.png"
            alt="image"
            className="img-fluid"
            rounded
          />
        </Col>
      </Row>
    </div>
  );
};

export default DreamHouse;
