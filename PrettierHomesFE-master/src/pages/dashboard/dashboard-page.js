import React from "react";
import Spacer from "../../components/common/spacer";
import DashboardNavigation from "../../components/dashboard/home/dashboard-navigation";
import { Container } from "react-bootstrap";

const DashboardPage = () => {
  return (
    <Container>
      <Spacer />
      <DashboardNavigation />
      <Spacer />
    </Container>
  );
};

export default DashboardPage;
