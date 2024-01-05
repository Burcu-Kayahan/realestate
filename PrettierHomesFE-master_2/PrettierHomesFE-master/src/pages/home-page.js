import React from "react";
import PageHeader from "../components/common/page-header";
import Spacer from "../components/common/spacer";
import { Container } from "react-bootstrap";
import PropertySearch from "../components/home-page/property-search/property-search";
import PropertiesByType from "../components/home-page/properties-by-type/properties-by-type";
import PropertiesByCities from "../components/home-page/properties-by-cities/properties-by-cities";
import DreamHouse from "../components/home-page/dream-house/dream-house";
import HappyCouple from "../components/about-page/happy-couple";
import Help from "../components/about-page/help";
import FeaturedProperties from "../components/home-page/Featured-properties/Featured-properties";

const HomePage = () => {
  return (
    <Container>
 
      <PropertySearch />     
      <FeaturedProperties />
      <Spacer />
      <PropertiesByType />
      <Spacer />
      <PropertiesByCities />
      <Spacer />
      <DreamHouse />
      <Spacer />      
      <HappyCouple />
      <Spacer />
      <Help />
      <Spacer />

    </Container>
  );
};

export default HomePage;
