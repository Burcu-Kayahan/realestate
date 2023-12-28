import React from "react";
import { Container } from "react-bootstrap";
import AdminCustomerEditForm from "./admin-customer-edit-form";
import AdminCustomerAdverts from "./admin-customer-adverts";
import AdminCustomerTourRequests from "./admin-customer-tourRequest";
import AdminCustomerFavorites from "./admin-customer-favorites";
import AdminCustomerLogs from "./admin-customer-logs";
import { useParams } from "react-router-dom";
import Spacer from "../../common/spacer";

const AdminCustomerEditLayout = (props) => {
 const slug =useParams();
 const {id, data} = props
  return (
    <Container>
      <Spacer height={20} />
      <AdminCustomerEditForm data={data} close={props.close}/>
      <Spacer height={20} />
      <AdminCustomerAdverts id={data.id} />
      <Spacer height={20} />
      <AdminCustomerTourRequests />
      <Spacer height={20} />
      <AdminCustomerFavorites id={data.id}/>
      <Spacer height={20} />
      <AdminCustomerLogs id={data.id} />
      <Spacer height={40} />
    </Container>
  );
};

export default AdminCustomerEditLayout;
