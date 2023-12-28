import React from "react";
import { Col, Image, Row } from "react-bootstrap";
const VisitOurOffice = () => {
  return (
    <div className="visit-our-office text-center">
      <h3>Visit Our Office</h3>
      <p className="mb-5">
        Realton has more than 9,000 offices of all sizes and all potential of
        session
      </p>
      <Row className="d-flex justify-content-between ">
        <Col xs={12} md={4}>
          <Image src="/images/our-offices/1.png" rounded  width={132}/>
          <h5>PARİS</h5>
          <span>1301 2nd Ave, Seattle, WA 98101 (315) 905-2321</span>
        </Col>
        <Col xs={12} md={4}>
          <Image src="/images/our-offices/2.png" rounded  width={150} />
          <h5>LONDON</h5>
          <span>1301 2nd Ave, Seattle, WA 98101 (315) 905-2321</span>
        </Col>
        <Col xs={12} md={4}>
          <Image src="/images/our-offices/3.png" roundedCircle  width={155}/>
          <h5>ISTANBUL</h5>
          <span>1301 2nd Ave, Seattle, WA 98101 (315) 905-2321</span>
        </Col>
      </Row>
    </div>
  );
};

export default VisitOurOffice;
