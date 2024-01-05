import { resetPassword } from '../../api/user-service'; 
import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { Form, Button, Row, Col, Card, Container } from 'react-bootstrap';
import { useFormik } from 'formik';
import * as Yup from 'yup';
import "./reset-form.scss"
import ButtonLoader from "../../components/common/button-loader";
import PasswordInput from '../../components/common/password-input';
import { isInValid, isValid } from "../../helpers/functions/forms";
import { swalAlert } from '../../helpers/functions/swal';


const ResetForm= () => {
  const [loading, setLoading] = useState(false);

  const initialValues = {
      resetCode: "",
      newPassword: "",
      retryNewPassword:"",
      email:""
  };


  const validationSchema = Yup.object({
    resetCode: Yup.string().required("Required"),
    newPassword: Yup.string().required("Required")
    .min(8, "At least 8 characters")
    .matches(/[a-z]+/g, "One lowercase char")
    .matches(/[A-Z]+/g, "One uppercase char")
    .matches(/[\d+]+/g, "One number"),
    email: Yup.string().email("Invalid email address").required("Required"),
    retryNewPassword: Yup.string()
      .required("Required")
      .oneOf([Yup.ref("newPassword")], "Passwords must match"),
  });
  

const onSubmit = async (values) => {


  
  if(!formik.isValid) {
    return;
  }
   setLoading(true);
   
  
   const dto = {
     password: values.newPassword,
     email:values.email,
     code:values.resetCode,
   };
   console.log (dto)
  try {
      await resetPassword(dto);
      swalAlert("Change Password was created successfully", "success")
      formik.setValues({
        resetCode: "",
        email:"",
        newPassword: "",
        retryNewPassword:""
      });
      setLoading(false);
 
  } catch (err) {
    console.log(err);
    const errMsg = err.response.data.message;
    setLoading(false);
    swalAlert(errMsg, "error");
  } 
};


const formik = useFormik({
  initialValues,
  validationSchema,
  onSubmit,
});
formik.setErrors({});

return (
  <Container className="resetForm">
    <Row className="justify-content-center">
      <Col md={8} lg={6}>
        <Card border="0">
          <Card.Body>
             <Form noValidate onSubmit={formik.handleSubmit}>
               <Form.Group className="mb-3" controlId="resetCode">
                <Form.Label className="mb-0 label">Reset Code</Form.Label>
                <Form.Control
                    className="control"
                    type="text"
                    placeholder="Enter Reset Code"
                    {...formik.getFieldProps("resetCode")}
                    isInvalid={isInValid(formik, "resetCode")}
                    isValid={isValid(formik, "resetCode")}
                  />
              <Form.Control.Feedback type="invalid">
                  {formik.errors.resetCode}
                </Form.Control.Feedback>
              </Form.Group>

              <Form.Group className=" mb-4" controlId="email">
                  <Form.Label className="mb-0 label">Email</Form.Label>
                  <Form.Control
                    className="control"
                    type="text"
                    placeholder="Email"
                    {...formik.getFieldProps("email")}
                    isInvalid={isInValid(formik, "email")}
                    isValid={isValid(formik, "email")}
                  />
                  <Form.Control.Feedback type="invalid">
                    {formik.errors.email}
                  </Form.Control.Feedback>
                </Form.Group>
                            
              <Form.Group className="mb-3" controlId="newPassword">
                <Form.Label className="mb-0 label">New Password</Form.Label>
                <PasswordInput
                  className="control"
                  {...formik.getFieldProps("newPassword")}
                  isInvalid={isInValid(formik, "newPassword")}
                  isValid={isValid(formik, "newPassword")}
                  error={formik.errors.newPassword}
                
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
                 error={formik.errors.retryNewPassword}
                                />
                  <Form.Control.Feedback type="invalid">
                  {formik.errors.retryNewPassword}
                </Form.Control.Feedback>
              </Form.Group>
                            
              <Button
               
                type="submit"
                className="btn-login w-50 mx-auto m-4 d-flex justify-content-center"
               disabled={!formik.isValid || loading}
              >
                 {loading ? <ButtonLoader /> : ""} RESET PASSWORD
              </Button>
              
            
              
            </Form>
          </Card.Body>
        </Card>
      </Col>
    </Row>
  </Container>
)
}
export default ResetForm;