import React, { useEffect, useState } from "react";
import FeaturedPropertyCard from "./Featured-property-card";
import featuredProperties from "../../../helpers/data/featured-properties.json";
import { Col, Row } from "react-bootstrap";
import Spacer from "../../common/spacer";
import { getPopularAdvertList } from "../../../api/adverts-service";
import { getFavorites } from "../../common/favorites";

const FeaturedProperties = () => {
const [popularList, setPopularList] = useState([]);
const [favorites, setFavorites]= useState(null)


const loadData =async ()=>{
  try{
const resp = await getPopularAdvertList(6);
setPopularList(resp);
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
},[])


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

//degioskenleri olusturmak

// ihtiyaciniz olan fonksiyonlari olusturaksiniz




  return (
    <div className="featured-properties">
      <h2>Discover Popular Properties </h2>
      <span className="text-success mb-5 ">Featured Properties</span>
      {/* <Spacer/> */}
      <Row>
        {popularList?.map((property) => (
          <Col lg={4} md={6} key={property.id}>
            <FeaturedPropertyCard {...property} favory={isFavory(property.id)}/>
          </Col>
        ))}
      </Row>
    </div>
  );
};

export default FeaturedProperties;
