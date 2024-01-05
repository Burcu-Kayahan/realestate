import React from "react";
import Spacer from "../components/common/spacer";
import PageHeader from "../components/common/page-header";
import Contact from "../components/contact-page/contact";
import { Container } from "react-bootstrap";
import VisitOurOffice from "../components/contact-page/visit-our-office";

const ContactPage = () => {
  return (
    <Container>
      <Spacer />
      <PageHeader title="CONTACT US" />
      <Spacer />
      <Contact />
      <Spacer />
      <VisitOurOffice />
      <Spacer height={300}/>

    </Container>
  );
};

export default ContactPage;
