import { useFormik } from 'formik';
import React, { useState } from 'react'
import { Button, Card, Col, Container, Dropdown, Form, Row } from 'react-bootstrap';
import { useDispatch } from 'react-redux';
import * as Yup from "yup";
import { deleteUser, getUserInfos, updateUser} from '../../../api/user-service';
import { swalAlert } from '../../../helpers/functions/swal';
import { isInValid, isValid } from "../../../helpers/functions/forms";
import ButtonLoader from '../../common/button-loader';
import "./styles/admin-custemer-edit.scss"


const AdminCustomerEditForm = (props) => {

 const [loading, setLoading] = useState(false);
 const [user, setUser] = useState(props.data);
 const dispatch = useDispatch();
 
 const initialValues = {
  ...user
  //  id:user.id,
  //  firstName: user.firstName,
  //  lastName: user.lastName,
  //  phone: user.phone,
  //  email: user.email,
  //  role: user.role,
 };

 const validationSchema = Yup.object({
   firstName: Yup.string().required("Required"),
   lastName: Yup.string().required("Required"),
   phone: Yup.string().required("Required"),
   email: Yup.string().required("Required"),
   role: Yup.string()
     .required("Required")
     .oneOf(["ADMIN", "CUSTOMER", "MANAGER"], "Invalid role"),
 });


  const onSubmit = async (values) => {
    setLoading(true);
    try {
      const resp = await updateUser(values);
     formik.setValues(resp)
     swalAlert("User Updated successful...", "success");
    } catch (err) {
      
      const errMsg = err.response.data.message;
      swalAlert(errMsg, "error");
    } finally {
      setLoading(false);
    }
  };

  const handleDelete = async()=>{
    setLoading(true);
    const isOk = window.confirm("Are you sure you want to delete this user?");
    if(isOk){
    try {
      const resp = await deleteUser(formik.values.id);
      formik.setValues("")
     swalAlert("User Delete successful...", "success");
     props.close();
    } catch (err) {      
      const errMsg = err.response.data.message;
      swalAlert(errMsg, "error");      
    } finally {
      setLoading(false);
    }
  }
  setLoading(false);
  }
  const formik = useFormik({
    initialValues,
    validationSchema,
    onSubmit,
  });

  const close =()=>{
    props.close();
  }

  return (
    <div>
      <Container className="usersEdit">
        <Form noValidate onSubmit={formik.handleSubmit}>
          <Row className="justify-content-center">
            <Col md={6}>
              <Form.Group className=" mb-4" controlId="firstName">
                <Form.Label className="mb-0 label">firstName</Form.Label>
                <Form.Control
                  className="control"
                  type="text"
                  placeholder="firstName"
                  {...formik.getFieldProps("firstName")}
                  isInvalid={isInValid(formik, "firstName")}
                  isValid={isValid(formik, "firstName")}
                />
                <Form.Control.Feedback type="invalid">
                  {formik.errors.firstName}
                </Form.Control.Feedback>
              </Form.Group>
            </Col>
            <Col md={6}>
              <Form.Group className="mb-3" controlId="lastName">
                <Form.Label className="mb-0 label">lastName</Form.Label>
                <Form.Control
                  className="control"
                  type="text"
                  placeholder="lastName"
                  {...formik.getFieldProps("lastName")}
                  isInvalid={isInValid(formik, "lastName")}
                  isValid={isValid(formik, "lastName")}
                />
              </Form.Group>
              <Form.Control.Feedback type="invalid">
                {formik.errors.lastName}
              </Form.Control.Feedback>
            </Col>
          </Row>
          <Row className="justify-content-center">
            <Col md={4}>
              <Form.Group className="mb-3" controlId="phone">
                <Form.Label className="mb-0 label">Phone</Form.Label>
                <Form.Control
                  className="control"
                  type="text"
                  placeholder="phone"
                  {...formik.getFieldProps("phone")}
                  isInvalid={isInValid(formik, "phone")}
                  isValid={isValid(formik, "phone")}
                />
              </Form.Group>
              <Form.Control.Feedback type="invalid">
                {formik.errors.phone}
              </Form.Control.Feedback>
            </Col>
            <Col md={4}>
              <Form.Group className="mb-3" controlId="email">
                <Form.Label className="mb-0 label">Email</Form.Label>
                <Form.Control
                  disabled
                  className="control"
                  type="text"
                  placeholder="email"
                  {...formik.getFieldProps("email")}
                  isInvalid={isInValid(formik, "email")}
                  isValid={isValid(formik, "email")}
                />
              </Form.Group>
              <Form.Control.Feedback type="invalid">
                {formik.errors.email}
              </Form.Control.Feedback>
            </Col>
            <Col md={4}>
              <Form.Label className="mb-0 label">Role</Form.Label>
              <select
                class="form-select"
                onChange={(e) => {
                  formik.setFieldValue("role", e.target.value);                
                }}
              >
                
                <option disabled={formik.values.role === "CUSTOMER"} value="CUSTOMER">
                  CUSTOMER
                </option>
                <option disabled={formik.values.role === "MANAGER"} value="MANAGER">
                  MANAGER
                </option>
                <option disabled={formik.values.role === "ADMIN"} value="ADMIN">
                  ADMIN
                </option>
              </select>
              <Form.Control.Feedback type="invalid">
                {formik.errors.role}
              </Form.Control.Feedback>
            </Col>
          </Row>
          <div className="d-flex justify-content-end">
            <Button
              onClick={()=>{
                close()
              }}
              className="close   mx-auto m-4  "             
            >
             CLOSE
            </Button>
            <Button
              onClick={()=>{
                handleDelete();
              }}
              className="delete  mx-auto m-4  "
              disabled={!formik.isValid || loading}
            >
              {loading ? <ButtonLoader /> : ""}DELETE
            </Button>
            <Button
              type="submit"
              className="save btn-login  mx-auto m-4 "
              disabled={!formik.isValid || loading}
            >
              {loading ? <ButtonLoader /> : ""}SAVE
            </Button>
          </div>
        </Form>
      </Container>

     
    </div>
  );
}

export default AdminCustomerEditForm