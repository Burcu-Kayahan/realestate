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


  return (
    <Row>
      <Col className=" d-flex m-1">
        <Card className="properties-card col-12 col-md-6 col-lg-4" >
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
        
         
          <div className="row-cols-2 properties-card-body ">
            <div className="col-8 p-3 ">
              <p className="fw-bold mb-0">{title}</p>
              <p className="mb-0 ">{countryName} /{cityName}/{districtName}</p>
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
