import { useFormik } from "formik";
import React, { useState } from "react";
import {
  Button,
  Card,
  Col,
  Container,
  Form,
  Row,
  FormGroup,
  FormLabel,
  FormControl,
} from "react-bootstrap";
import * as Yup from "yup";
import PasswordInput from "../common/password-input";
import { isInValid, isValid } from "../../helpers/functions/forms";
import { swalAlert } from "../../helpers/functions/swal";
import { setToLocalStorage } from "../../helpers/functions/encrypted-storage";
import { useDispatch } from "react-redux";
import { register as registerSuccess } from "../../store/slices/auth-slice";
import ButtonLoader from "../common/button-loader";
import { Link, useNavigate } from "react-router-dom";
import { register } from "../../api/user-service";
import "./register-form.scss";

const RegisterForm = () => {
  const [loading, setLoading] = useState(false);
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const initialValues = {
    firstName: "",
    lastName: "",
    phone: "",
    email: "admin@gmail.com",
    password: "123456789",
    confirmPassword: "123456789",
  };

  const validationSchema = Yup.object({
    firstName: Yup.string().required("Required"),
    lastName: Yup.string().required("Required"),
    phone: Yup.string().required("Required"),
    email: Yup.string().email("Invalid email address").required("Required"),
    password: Yup.string().required("Required"),
    confirmPassword: Yup.string()
      .oneOf([Yup.ref("password"), null], "Passwords must match")
      .required("Required"),
  });

  const onSubmit = async (values) => {
    setLoading(true);

    try {
      const resp = await register(values);
      const { user, token } = resp;
      // console.log(resp);
      setToLocalStorage("token", token);
      // console.log(token);

      dispatch(registerSuccess(user));

      navigate("/dashboard"); // burasi daha sonra aktif ewdilecek.
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
    <Container className="registerPage">
      <Row className="justify-content-center">
        <Col md={8} lg={6}>
          <Card border="0">
            <Card.Body>
              <Form noValidate onSubmit={formik.handleSubmit}>
                <FormGroup className=" mb-4" controlId="firstName">
                  <FormLabel className="mb-0 label">First Name</FormLabel>
                  <FormControl
                    className="control"
                    type="text"
                    placeholder="firstName"
                    onChange={formik.handleChange}
                    onBlur={formik.handleBlur}
                    value={formik.values.firstName}
                  />
                  {formik.touched.firstName && formik.errors.firstName ? (
                    <div>{formik.errors.firstName}</div>
                  ) : null}
                </FormGroup>

                <FormGroup className=" mb-4" controlId="lastName">
                  <FormLabel className="mb-0 label">Last Name</FormLabel>
                  <FormControl
                    className="control"
                    type="text"
                    placeholder="lastName"
                    onChange={formik.handleChange}
                    onBlur={formik.handleBlur}
                    value={formik.values.lastName}
                  />
                  {formik.touched.lastName && formik.errors.lastName ? (
                    <div>{formik.errors.lastName}</div>
                  ) : null}
                </FormGroup>
                <FormGroup className=" mb-4" controlId="phone">
                  <FormLabel className="mb-0 label">Phone</FormLabel>
                  <FormControl
                    className="control"
                    type="tel"
                    placeholder="phone"
                    onChange={formik.handleChange}
                    onBlur={formik.handleBlur}
                    value={formik.values.phone}
                  />
                  {formik.touched.phone && formik.errors.phone ? (
                    <div>{formik.errors.phone}</div>
                  ) : null}
                </FormGroup>
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
                <FormGroup className="mb-3" controlId="confirmPassword">
                  <Form.Label className="mb-0 label">
                    Confirm Password
                  </Form.Label>
                  <PasswordInput
                    className="control"
                    {...formik.getFieldProps("confirmPassword")}
                    isInvalid={
                      formik.touched.confirmPassword &&
                      formik.errors.confirmPassword
                    }
                    isValid={
                      formik.touched.confirmPassword &&
                      !formik.errors.confirmPassword
                    }
                    error={formik.errors.confirmPassword}
                  />
                </FormGroup>

                <Button
                  variant="primary"
                  type="submit"
                  className="btn-register w-50 mx-auto m-4 d-flex justify-content-center"
                  disabled={!formik.isValid || loading}
                >
                  {loading ? <ButtonLoader /> : ""}REGISTER
                </Button>
                <p className="fs-4">
                  If you already have an account.{" "}
                  <Link
                    className="text-dark "
                    onClick={() => {
                      navigate("/login");
                    }}
                  >
                    Login now!
                  </Link>
                </p>
              </Form>
            </Card.Body>
          </Card>
        </Col>
      </Row>
    </Container>
  );
};

export default RegisterForm;
