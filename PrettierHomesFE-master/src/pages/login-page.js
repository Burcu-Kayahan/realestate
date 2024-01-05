import React from "react";
import PageHeader from "../components/common/page-header";
import Spacer from "../components/common/spacer";
import LoginForm from "../components/login-page/login-form";
import { Container } from "react-bootstrap";


const LoginPage = () => {
  return (
    <Container>
      <Spacer height={20} />
      <PageHeader title="LOGIN" />
      <Spacer />
       <LoginForm />
      <Spacer />      
      <Spacer />      
      <Spacer />      

    </Container>
  );
};

export default LoginPage;
