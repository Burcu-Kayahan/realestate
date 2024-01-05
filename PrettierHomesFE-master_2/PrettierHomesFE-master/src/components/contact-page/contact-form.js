import React, { useState } from "react";
import { Button, Col, Form, InputGroup, Row } from "react-bootstrap";
import { FiMail, FiMessageSquare, FiSend, FiTag, FiUser } from "react-icons/fi";
import "./contact-form.scss";
import * as Yup from "yup";
import { useFormik } from "formik";
import { isInValid, isValid } from "../../helpers/functions/forms";
import { swalAlert } from "../../helpers/functions/swal";
import ButtonLoader from "../common/button-loader";

const ContactForm = () => {
  const [loading, setLoading] = useState(false);

  const initialValues = {
    email: "",
    message: "",
    firstname: "",
    lastname: "",
  };

  const validationSchema = Yup.object({
    email: Yup.string()
      .email("Invalid email")
      .max(50, "Max 50 characters")
      .required("Required"),
    message: Yup.string().required("Required").max(200, "Max 200 characters"),
    name: Yup.string()
      .required("Required")
      .min(4, "At least 4 characters")
      .max(30, "Max 30 characters"),
    subject: Yup.string()
      .required("Required")
      .min(4, "At least 4 characters")
      .max(30, "Max 30 characters"),
  });

  const onSubmit = async (values) => {
      setLoading(true);

      try {
          // await createMessage(values);
          formik.resetForm();

          swalAlert("Your message was sent", "success");

      } catch (err) {
          const errMsg = Object.values(err.response.data.validations)[0];
          swalAlert(errMsg, "error");
      }
      finally{
          setLoading(false);
      }
  }
 

  const formik = useFormik({
    initialValues,
    validationSchema,
    onSubmit,
  });

  return (
    <Form
      className="contact-form shadow p-5 rounded-4"
      noValidate
      onSubmit={formik.handleSubmit}
    >
      <h5 className="py-3">Have a question? Get in touch!</h5>
      <Row className="g-3">
        <Col xs={12}>
          <Form.Label>First Name</Form.Label>
          <InputGroup className="mb-3">
            <Form.Control
              aria-label="Your Firstname"
              aria-describedby="basic-addon1"
              {...formik.getFieldProps("firstname")}
              isValid={isValid(formik, "firstname")}
              isInvalid={isInValid(formik, "firstname")}
            />
            <Form.Control.Feedback type="invalid">
              {formik.errors.name}
            </Form.Control.Feedback>
          </InputGroup>
        </Col>
        <Col xs={12}>
          <Form.Label>Last Name</Form.Label>
          <InputGroup className="mb-3">
            <Form.Control
              aria-label="Your lastname"
              aria-describedby="basic-addon1"
              {...formik.getFieldProps("lastname")}
              isValid={isValid(formik, "lastname")}
              isInvalid={isInValid(formik, "lastname")}
            />
            <Form.Control.Feedback type="invalid">
              {formik.errors.name}
            </Form.Control.Feedback>
          </InputGroup>
        </Col>
        <Col xs={12}>
          <Form.Label>Email</Form.Label>
          <InputGroup className="mb-3">
            <Form.Control
              type="email"
              aria-label="Your email"
              aria-describedby="basic-addon1"
              {...formik.getFieldProps("email")}
              isValid={isValid(formik, "email")}
              isInvalid={isInValid(formik, "email")}
            />
            <Form.Control.Feedback type="invalid">
              {formik.errors.email}
            </Form.Control.Feedback>
          </InputGroup>
        </Col>

        <Col xs={12}>
          <Form.Label>Message</Form.Label>
          <InputGroup className="mb-3">
            <Form.Control
              as="textarea"
              aria-label="With textarea"
              {...formik.getFieldProps("message")}
              isValid={isValid(formik, "message")}
              isInvalid={isInValid(formik, "message")}
            />
            <Form.Control.Feedback type="invalid">
              {formik.errors.message}
            </Form.Control.Feedback>
          </InputGroup>
        </Col>
      </Row>
      <Row>
        <Button type="submit" variant="success">
          {loading ? <ButtonLoader /> : ""} Send
        </Button>
      </Row>
    </Form>
  );
};

export default ContactForm;
