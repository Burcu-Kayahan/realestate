import React from "react";
import { Container } from "react-bootstrap";
import PageHeader from "../components/common/page-header";
import Spacer from "../components/common/spacer";
import ResetForm from "../components/reset-page/reset-form";

const ResetPage = () => {
  return (
    <Container>
      <Spacer height={20} />
      <PageHeader title="RESET" />
      <Spacer />
      <ResetForm />
    </Container>
  );
};

export default ResetPage;
