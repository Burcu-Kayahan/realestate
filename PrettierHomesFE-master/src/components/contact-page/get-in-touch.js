import React from "react";
import { config } from "../../helpers/config";
import { FiHeadphones, FiMail, FiMapPin } from "react-icons/fi";
import { Col, Nav } from "react-bootstrap";
import "./get-in-touch.scss";

const GetInTouch = () => {
  return (
    <div className="get-in-touch">
      <Col md={3} className="mx-3 p-4">
        <h2> We’d Love To Hear From You.</h2>
        <p>
          {" "}
          We are here to answer any question you may have. As a partner of
          corporates, realton has more than 9,000 offices of all sizes and all
          potential of session.
        </p>
      </Col>
    </div>
  );
};

export default GetInTouch;
