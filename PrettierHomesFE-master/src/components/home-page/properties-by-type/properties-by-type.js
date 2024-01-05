import React from "react";
import { Col, Row } from "react-bootstrap";
import properties from "../../../helpers/data/properties-by-type.json";
import PropertiesByTypeCard from "./properties-by-type-card";
const PropertiesByType = () => {

  
  return (
    <div className="properties-by-type">
      <h2>Explore Properties</h2>
      <h5 className="text-success ">By Type</h5>
      <Row className="d-flex justify-content-between mt-4">
        {properties.map((property) => (
          <Col lg={2} md={3} sm={6} xs={6} className="mb-4" key={property.id}>
            <PropertiesByTypeCard {...property} />
          </Col>
        ))}
      </Row>

    </div>
  );
};

export default PropertiesByType;
