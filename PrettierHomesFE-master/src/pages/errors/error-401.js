import React from "react";
import { Button, Col, Container, Image, Row } from "react-bootstrap";
import "./error.scss";
import PageHeader from "../../components/common/page-header";
import { Link } from "react-router-dom";
import LoginPage from "../login-page";

const Error401Page = () => {
  return (
    <Container>
      <div className="error-page d-flex justify-content-center align-items-center">
      <PageHeader title="UNAUTHORIZED" />
        <Row>
          <Col>
            <Image src="/images/errors/401.png" alt="Unauthorized" />
          </Col>

          <Col className="d-flex justify-content-center flex-column align-items-start">
            <h2 className="text-success">
              Sorry you are not authorized to access.
            </h2>
            <p>
              Please check your login credentials or contact the administrator
            </p>
            <Button as={Link} to="/" variant="dark" className="w-50">
              LOGOUT
            </Button>
          </Col>
        </Row>
      </div>
    </Container>
  );
};

export default Error401Page;
