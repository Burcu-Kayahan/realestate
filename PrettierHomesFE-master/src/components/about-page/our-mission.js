import React from "react";
import { Card, Col, Row } from "react-bootstrap";
import "./our-mission.scss";

const OurMission = () => {
  return (
    <div className="our-mission">
      <Card>
        <Row>
          <Col lg={6}>
            <Card.Img
              src="/images/about/home.jpg"
              alt="image"
              className="img-fluid"
            />
          </Col>
          <Col lg={6}>
            <Card.Body>
              <h2 className="cardTitle">
                We're on a Mission to Change <br /> View of Real Estate Field.
              </h2>
              <p className="py-3">
                At the heart of our vision lies a resolute commitment to
                transform the landscape of the real estate industry. We're not
                just a company; we're on a mission to change the very essence of
                how real estate is perceived and experienced. Our journey is
                defined by innovation, transparency, and a relentless pursuit of
                excellence. With a bold and forward-thinking approach, we seek
                to revolutionize the traditional norms of the real estate field,
                making it more accessible, efficient, and customer-centric. Our
                aspiration is to shape a future where buying, selling, or
                investing in real estate is a seamless and empowering experience
                for all. Join us on this transformative journey as we rewrite
                the narrative of real estate
              </p>
              <div className="d-flex gap-5">
                <div >
                  <div className="circle mx-3"></div>
                  <h6>Modern Architect</h6>
                </div>
                <div>
                  <div className="circle mx-2"></div>
                  <h6>Green Building</h6>
                </div>
              </div>
            </Card.Body>
          </Col>
        </Row>
      </Card>
    </div>
  );
};

export default OurMission;
