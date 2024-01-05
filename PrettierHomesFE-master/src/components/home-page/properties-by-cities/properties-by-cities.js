import React from "react";
import { Col, Row } from "react-bootstrap";
import properties from "../../../helpers/data/properties-by-cities.json";
import PropertiesByCitiesCard from "./properties-by-cities-card";
const PropertiesByCities = () => {
  return (
    <div className="properties-by-type">
      <h2>Explore Properties </h2>
      <h5 className="text-success ">By Cities</h5>
      <Row className="d-flex justify-content-between mt-4 p-5 p-md-0">
        {properties.map((property) => (
          <Col lg={2} md={3} sm={6} xs={6} className="mb-4 px-md-" key={property.id}>
            <PropertiesByCitiesCard {...property} />
          </Col>
        ))}
      </Row>
    </div>
  );
};

export default PropertiesByCities;
