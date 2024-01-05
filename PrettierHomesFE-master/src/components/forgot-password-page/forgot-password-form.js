
import { useState } from "react";
import "./forgot-password-form.scss"
import { useDispatch } from "react-redux";
import {  useNavigate } from "react-router-dom";
import * as Yup from "yup";
import { register as registerSuccess } from "../../store/slices/auth-slice";
import { setToLocalStorage } from "../../helpers/functions/encrypted-storage";
import { useFormik } from "formik";
import { forgetPassword, register } from "../../api/user-service";
import { swalAlert } from "../../helpers/functions/swal";
import { Card, Col, Container, Row ,Form, Button, } from "react-bootstrap";
import { isInValid, isValid } from "../../helpers/functions/forms";
import ButtonLoader from "../common/button-loader";





const ForgotPasswordForm = () => {

  const [loading, setLoading] = useState(false);
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const initialValues = {
    email: "",
  };

  const validationSchema = Yup.object({
    email: Yup.string().required("Required"),
  });

  const onSubmit = async (values) => {
    setLoading(true);
      console.log(values)
    try {
      const resp = await forgetPassword(values);
      
      navigate("/reset");
      // console.log(resp);
      
    } catch (err) {
      console.log(err);
      const errMsg = err.response.data.message;
      swalAlert(errMsg, "error");
    } finally {
      setLoading(false);
    }
  };

  const formik = useFormik({
    initialValues,
    validationSchema,
    onSubmit,
  });


  


  return(

    <Container className="forgotPage">
      <Row className="justify-content-center">
        <Col md={8} lg={6}>
        <Card  border="0">
          <Card.Body>
            <Form noValidate onSubmit={formik.handleSubmit}>
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


                <Button
                  variant="primary"
                  type="submit"
                  className="btn-forgot w-50 mx-auto m-4 d-flex justify-content-center"
                  disabled={!formik.isValid || loading}
                >
                  {loading ? <ButtonLoader /> : ""}SEND RESET CODE
                </Button>
                


            </Form>
          </Card.Body>
        </Card>
        </Col>
      </Row>
    </Container>



  )
}

  

export default ForgotPasswordForm
