import { useFormik } from "formik";
import React, { useState } from "react";
import { Button, Card, Col, Container, Form, Row } from "react-bootstrap";
import * as Yup from "yup";
import PasswordInput from "../common/password-input";
import { isInValid, isValid } from "../../helpers/functions/forms";
import { login } from "../../api/auth-service";
import { swalAlert } from "../../helpers/functions/swal";
import { setIsLoginLocalStorage, setToLocalStorage, setUserLocalStorage } from "../../helpers/functions/encrypted-storage";
import { useDispatch } from "react-redux";
import { login as loginSuccess } from "../../store/slices/auth-slice";
import ButtonLoader from "../common/button-loader";
import { Link, useNavigate } from "react-router-dom";
import "./login-page.scss";

const LoginForm = () => {
  const [loading, setLoading] = useState(false);
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const initialValues = {
    password: "123456789",
    email: "admin@gmail.com",
  };

  const validationSchema = Yup.object({
    password: Yup.string().required("Required"),
    email: Yup.string().required("Required"),
  });

  const onSubmit = async (values) => {
    setLoading(true);

    try {
      const resp = await login(values);
      const { user, token } = resp;
      // console.log(resp);
      setToLocalStorage("token", token);
      // console.log(token);
      setIsLoginLocalStorage()
      setUserLocalStorage(user)
      dispatch(loginSuccess(user));

      navigate("/"); // burasi daha sonra aktif ewdilecek.
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

  return (
    <Container className="loginPage">
      <Row className="justify-content-center">
        <Col md={8} lg={6}>
          <Card border="0">
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
                <Form.Group className="mb-3" controlId="password">
                  <Form.Label className="mb-0 label">Password</Form.Label>
                  <PasswordInput
                    className="control"
                    {...formik.getFieldProps("password")}
                    isInvalid={isInValid(formik, "password")}
                    isValid={isValid(formik, "password")}
                    error={formik.errors.password}
                  />
                </Form.Group>
                <Link className="forgot-password m-2"
                  onClick={() => {
                    navigate("/forgot-password");
                  }}
                >
                  {" "}
                  Forgot Password?
                </Link>
                <Button
                  variant="primary"
                  type="submit"
                  className="btn-login w-50 mx-auto m-4 d-flex justify-content-center"
                  disabled={!formik.isValid || loading}
                >
                  {loading ? <ButtonLoader /> : ""}LOGIN
                </Button>
                <p className="fs-4">
                If you don't have an account.{" "}
                <Link className="text-dark"
                  onClick={() => {
                    navigate("/register");
                  }}
                >
                  Register now!
                </Link></p>
              </Form>
            </Card.Body>
          </Card>
        </Col>
      </Row>
    </Container>
  );
};

export default LoginForm;
