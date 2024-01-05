import React, { useState } from "react";
import { Card, Col, Row } from "react-bootstrap";
import { FaHeart,FaRegHeart  } from "react-icons/fa";
import { Link } from "react-router-dom";
import "./Featured-properties-cart.scss";
import { addOrRemoveFavorite } from "../../../api/favorites-service";
import { createOrDelete } from "../../common/favorites";

const FeaturedPropertyCard = ({id,slug,countryName, cityName, districtName, url, title, location, price, favory }) => {
  const [isFavory, setIsFavory]=useState(favory)
  

  const handleClick = async() => {
    setIsFavory(!isFavory);
    try{
  const res = await createOrDelete(id);
    }catch(err){
  alert(`${title} : can not add to favory`);
    }
    };
   let shortTitle =title;
    if(title.length > 40){
      shortTitle=title.substring(0, 40);
    }
  return (
    <Row>
      <Col className=" d-flex m-1">
        <Card className="properties-card-feature col-12 col-md-6 col-lg-4">
        <Link to={`/property/${slug}`}>
          <Card.Img
            className="properties-page-image img-fluit"
            // src={`/images/featured-properties/${image}`}
            src={url}
            alt={title}
          /> </Link>
            {isFavory ? <span className="heart-icon-full btn" onClick={()=>handleClick()}>
         <FaHeart />            
          </span> : <span className="heart-icon btn" onClick={()=>handleClick()}>
          <FaRegHeart />           
          </span> }
          <div className=" properties-card-body ">
            <div className="col-8  cartText">
              <p className="fw-bold  ms-2 title">{shortTitle} </p>
              <p className="location  ms-2"> {cityName}, {districtName} </p>
              <p className="properties-card-small-cost ms-2">$ {price}.00</p>
            </div>
            <div>
              <div className="properties-card-cost">
                {" "}
                <span> $ </span> {price}
              </div>
            </div>
          </div>
        </Card>
      </Col>
    </Row>
  );
};

export default FeaturedPropertyCard;
