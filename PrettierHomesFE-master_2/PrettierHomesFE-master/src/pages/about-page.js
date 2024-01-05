import React from "react";
import { Container } from "react-bootstrap";
import Spacer from "../components/common/spacer";
import PageHeader from "../components/common/page-header";
import OurMission from "../components/about-page/our-mission";
import Help from "../components/about-page/help";
import HappyCouple from "../components/about-page/happy-couple";

const AboutPage = () => {
  return (
    <Container>
      <Spacer />
      <PageHeader title="ABOUT US" />
      <Spacer />
      <OurMission />
      <Spacer />
      <HappyCouple />
      <Spacer />
      <Help />
      <Spacer />
    </Container>
  );
};

export default AboutPage;
