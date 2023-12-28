import React, { useEffect, useState } from "react";
import { Button, Col, Container, Row } from "react-bootstrap";
import PropertyForm from "./property-form";
import PropertyCard from "./property-card";
import featuredProperties from "../../helpers/data/featured-properties.json";
import { getAllAdvertsForUsers } from "../../api/adverts-service";
import { useLocation, useParams } from "react-router-dom";
import { getFavorites } from "../common/favorites";

const Properties = () => {
  const { state } = useLocation();  
  const { atype,categoryId, search } = state || {};
  const [data, setData]= useState([]);
  const [page, setpage]= useState(0);
  const [size, setSize]= useState(10);
  const [sort, setSort]= useState('title');
  const [type, setType]= useState("DESC");
  const [q, setQ]= useState(search || "");
  const [category, setCategory]= useState(categoryId || "");
  const [advertType, setAdvertType]= useState(atype || "");
  const [priceStart, setpriceStart]= useState('');
  const [priceEnd, setpriceEnd]= useState('');
  const [status, setStatus]= useState("");
  const [country, setCountry] = useState("");
  const [city, setCity] = useState("");
  const [district, setDistrict] = useState("");
  const [flag, setFlag]=useState(false);
  const [favorites, setFavorites]= useState([])

  
const loadData = async ()=>{
  try{
    const resp = await getAllAdvertsForUsers(page, size,sort, type,q,category,advertType,priceStart,priceEnd,status,country,city,district);
    setData(resp.content)   
  }catch(error){
console.log(error)
  }
}


const getFavority = async()=>{
  const data = await getFavorites();
  setFavorites(data);
}

useEffect(() =>{
  getFavority();
  loadData();
}, [flag])

const getFeatures =(type, category, search,country, city,districts,priceMin,priceMax)=>{  
  setDistrict(districts)
  setpriceEnd(priceMax)
  setpriceStart(priceMin)
  setCity(city)
  setCountry(country)
  setCategory(category)
  setAdvertType(type)
  setQ(search)
  setFlag(!flag) 
}


const [showPropertyForm, setShowPropertyForm] = useState(false);
const togglePropertyForm = () => {
  setShowPropertyForm(!showPropertyForm);
};
const isFavory = (id) => {
  if (favorites.length === 0) {   
    return false;
  }
  return favorites.some((fav) => {
    if (fav.advertId === id) {    
      return true;
    } else {      
      return false;
    }
  });
};


  return (
    <Container>
      <Button className="d-md-none btn btn-success " onClick={togglePropertyForm}>Filter</Button>

      {showPropertyForm && (
        <Row>
          <Col md={4} className="d-md-none mb-5">
            <PropertyForm search={search} type={atype} category={categoryId} getFeatures={getFeatures}/>
          </Col>
        </Row>
      )}
      <Row>
        <Col md={4} className="d-none d-md-block">
          <PropertyForm search={search} type={atype} category={categoryId} getFeatures={getFeatures}/>
        </Col>
        <Col md={8} className="d-flex flex-wrap ">
          
        {data.map((property) => (
          <Col md={6}   key={property.id}>
            <PropertyCard {...property} favory={isFavory(property.id)}/>
          </Col>
        ))}
        </Col>

      </Row>
    </Container>
  );
};

export default Properties;

