import React, { useState } from "react";
import { Card, Col, Row } from "react-bootstrap";
import { CiHeart } from "react-icons/ci";
import { FaHeart,FaRegHeart  } from "react-icons/fa";
import "./property-card.scss";
import { Link } from "react-router-dom";
import {  addOrRemoveFavorite } from "../../api/favorites-service";
import { createOrDelete } from "../common/favorites";


const PropertyCard = ({slug,id,url,price, title, countryName,cityName,districtName, favory }) => {
const [isFavory, setIsFavory]=useState(favory) 


const handleClick = async() => {
  try{  
    const res = await createOrDelete(id);    
  }catch(err){
    console.log(err)
  }
  setIsFavory(!favory); 
  };

  let shortTitle =title;
  if(title.length > 40){
    shortTitle=title.substring(0, 40);
  }


  return (
    <Row>
      <Col className=" d-flex m-1">
        <Card className="properties-card-properties col-12 col-md-6 col-lg-4" >
        <Link to={`/property/${slug}`}>
         
          <Card.Img
            className="properties-page-image img-fluit"
            src={url ? url :`https://images.pexels.com/photos/19453408/pexels-photo-19453408/free-photo-of-kent-peyzaj-su-yaz.jpeg?auto=compress&cs=tinysrgb&w=600&lazy=load`}
            alt={title}
          />
            </Link>
            {isFavory ? <span className="heart-icon-full btn" onClick={()=>{
             
              handleClick()}}>
         <FaHeart />            
          </span> : <span className="heart-icon btn" onClick={()=>{
           
           handleClick()}}>
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

export default PropertyCard;
