import React from "react";
import { Card } from "react-bootstrap";
import "./properties-by-type-card.scss";
const PropertiesByTypeCard = ({ type, number, image }) => {
  return (
    <Card className="properties-by-type-card">
      <div className="image">
        <img src={`/images/properties-by-type/${image}`} width={70} alt=""  />
        
      </div>
      <Card.Title>
        <h4 >{type}</h4>
        <h6>{number} Properties</h6>
      </Card.Title>
    </Card>
  );
};

export default PropertiesByTypeCard;
