import React, { useState } from 'react'
import { Button, Card, Col, Container, Form, Row } from 'react-bootstrap';
import PasswordInput from '../components/common/password-input';
import { isInValid, isValid } from "../helpers/functions/forms";
import * as Yup from "yup";
import { useFormik } from 'formik';
import { changePassword } from '../api/user-service';
import { swalAlert } from "../helpers/functions/swal";
import "./ChangePasswordForm.scss"
import ButtonLoader from "../../src/components/common/button-loader";

const ChangePasswordForm = () => {
    const [loading, setLoading] = useState(false);

    const initialValues = {
        password: "",
        newPassword: "",
        retryNewPassword:"",
        email:""
    };

    const validationSchema = Yup.object({
      password: Yup.string().required("Required"),
      // .min(8, "At least 8 characters")
      //  .matches(/[a-z]+/g, "One lowercase char")
      // .matches(/[A-Z]+/g, "One uppercase char")
      // .matches(/[\d+]+/g, "One number"),
      // email: Yup.string().email("Invalid email address").required("Required"),
      newPassword: Yup.string().required("Required"),
      retryNewPassword: Yup.string()
        .required("Required")
        .oneOf([Yup.ref("newPassword")], "Passwords must match"),
    });

 const onSubmit = async (values) => {
     setLoading(true);
    
     const dto = {
       password: values.password,
       newPassword: values.newPassword,
       email: "",
       code: "",
     };
    
    try {
        await changePassword(dto);
        swalAlert("Change Password was created successfully", "success")
        formik.setValues({
          password: "",
          newPassword: "",
          retryNewPassword:""
        });
   
    } catch (err) {
      console.log(err);
      const errMsg = err.response.data.message;
      //swalAlert(errMsg, "error");
    } finally {
      setLoading(false);
    }
  };

  const formik = useFormik({
    initialValues,
    validationSchema,
    onSubmit,
  });


  return (
    <Container className="changePasswordForm">
      <Row className="justify-content-center">
        <Col md={8} lg={6}>
          <Card border="0">
            <Card.Body>
               <Form noValidate onSubmit={formik.handleSubmit}>
                 <Form.Group className="mb-3" controlId="password">
                  <Form.Label className="mb-0 label">Current Password</Form.Label>
                  <PasswordInput
                    className="control"
                    {...formik.getFieldProps("password")}
                    isInvalid={isInValid(formik, "password")}
                    isValid={isValid(formik, "password")}
                    error={formik.errors.password}
            
                                  />
                <Form.Control.Feedback type="invalid">
                    {formik.errors.password}
                  </Form.Control.Feedback>
                </Form.Group>
                              
                <Form.Group className="mb-3" controlId="newPassword">
                  <Form.Label className="mb-0 label">New Password</Form.Label>
                  <PasswordInput
                    className="control"
                    {...formik.getFieldProps("newPassword")}
                    isInvalid={isInValid(formik, "newPassword")}
                    isValid={isValid(formik, "newPassword")}
                    error={formik.errors.password}
                  
                                  />
                <Form.Control.Feedback type="invalid">
                    {formik.errors.newPassword}
                  </Form.Control.Feedback>
                </Form.Group>
            
                <Form.Group className="mb-3" controlId="retryNewPassword">
                  <Form.Label className="mb-0 label">Retry New Password</Form.Label>
                 <PasswordInput
                   className="control"
                   {...formik.getFieldProps("retryNewPassword")}
                   isInvalid={isInValid(formik, "retryNewPassword")}
                   isValid={isValid(formik, "retryNewPassword")}
                   error={formik.errors.password}
                                  />
                    <Form.Control.Feedback type="invalid">
                    {formik.errors.retryNewPassword}
                  </Form.Control.Feedback>
                </Form.Group>
                              
                <Button
                  variant="primary"
                  type="submit"
                  className="btn-login w-50 mx-auto m-4 d-flex justify-content-center"
                 disabled={!formik.isValid || loading}
                >
                   {loading ? <ButtonLoader /> : ""} UPDATE
                </Button>
              
                
              </Form>
            </Card.Body>
          </Card>
        </Col>
      </Row>
    </Container>
  )
}

export default ChangePasswordForm