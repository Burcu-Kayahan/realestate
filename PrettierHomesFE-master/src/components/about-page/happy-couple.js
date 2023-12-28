import React from "react";
import { Card, Col, Image, Row } from "react-bootstrap";
import "./happy-couple.scss";
const HappyCouple = () => {
  return (
    <div className="happy-couple ">
      <Row >
        <Col
          lg={6} 
          className="d-flex flex-column  p-5 "
        >
          <h2 className="px-5 mb-5">
            Let’s Find The Right <br /> Selling Option For You{" "}
          </h2>

          <div className="p-lg-5">
            <div className="d-flex gap-3 ">
              <div className="icons"> <img src="/images/about/1.png" alt="icons" /></div>
              <div className="pt-4">
                <h5>Tech-Driven Marketing </h5>
                <p>
                  Real estate is embracing technology with virtual tours, 3D
                  models, and blockchain transactions.
                </p>
              </div>
            </div>
            <div className="d-flex gap-3">
              <div className="icons">
                <img src="/images/about/2.png" alt="icons" /> </div>
              <div>
                <h5> Sustainability Matters</h5>
                <p>
                  Green building practices and eco-friendly features are gaining
                  popularity for environmentally conscious buyers
                </p>
              </div>
            </div>
            <div className="d-flex gap-3 ">
              <div className="icons">  <img src="/images/about/3.png" alt="icons" /></div>
              <div className="pt-3">
                <h5>Remote Work Impact</h5>
                <p>
                  Changing work patterns are reshaping housing preferences,
                  favoring suburban and urban mixed use developments.
                </p>
              </div>
            </div>
          </div>
        </Col>
        <Col lg={6} >
          <Image
            src="/images/about/happy-couple.jpg"
            alt="image"
            className="img-fluid couple"
         
          />
        </Col>
      </Row>
    </div>
  );
};

export default HappyCouple;
