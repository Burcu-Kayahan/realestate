import React from "react";
import { Button, Col, Container, Image, Row } from "react-bootstrap";
import "./error.scss";
import PageHeader from "../../components/common/page-header";
import { Link } from "react-router-dom";
import Spacer from "../../components/common/spacer";

const Error404Page = () => {
  return (
    <Container>
      <Spacer height={20}/>
      <PageHeader title="NOT FOUND" />
      <div className="error-page d-flex justify-content-center align-items-center">
        <Row>
          <Col>
            <Image src="/images/errors/404.png" alt="Not found" />
          </Col>
          <Col className="d-flex justify-content-center flex-column align-items-start">
            <h2 className="text-success">Oops! It looks like you're lost</h2>
            <p>
              The page you're looking for isn't available. Try to search again
              or use the go to
            </p>
            <Button as={Link} to="/" variant="dark" className="w-50">
              Go to Home Page
            </Button>
          </Col>
        </Row>
      </div>
    </Container>
  );
};

export default Error404Page;
