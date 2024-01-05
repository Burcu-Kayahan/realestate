import React from "react";
import { Card } from "react-bootstrap";
import "./properties-by-cities-card.scss";
const PropertiesByCitiesCard = ({ city, number }) => {
  return (
    <Card className="properties-by-cities-card">
      <div className="image">
        <h4>{city}</h4>
        <h3>{number} Properties</h3>
      </div>
    </Card>
  );
};

export default PropertiesByCitiesCard;
