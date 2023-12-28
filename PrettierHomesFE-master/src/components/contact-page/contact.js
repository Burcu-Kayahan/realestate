import React from "react";
import { Card, Col, Container, Row } from "react-bootstrap";
import Map from "./map";
import ContactForm from "./contact-form";
import GetInTouch from "./get-in-touch";
import "./contact.scss";
import Spacer from "../common/spacer";

const Contact = () => {
  return (
    <div className="contact">
      <Container>
        <Map />
        <ContactForm />
        <GetInTouch />
      </Container>
    </div>
  );
};

export default Contact;
