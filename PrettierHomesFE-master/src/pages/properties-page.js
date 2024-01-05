import React from "react";
import PageHeader from "../components/common/page-header";
import Spacer from "../components/common/spacer";
import { Container } from "react-bootstrap";
import Properties from "../components/properties-page/properties";
import Error404Page from "./errors/error-404";
import Error401Page from "./errors/error-401";

const PropertiesPage = () => {
  return (
    <Container>
      <Spacer />
      <PageHeader title="PROPERTIES" />
      <Spacer height={20}/>
      <Properties />
      <Spacer />
    </Container>
  );
};

export default PropertiesPage;
