import { useFormik } from 'formik';
import React, { useEffect, useState } from 'react'
import { Button, Col, Container, Form, Row } from 'react-bootstrap';
import { useSelector } from 'react-redux';
import * as Yup from "yup";
import { swalAlert } from '../../../helpers/functions/swal';
import { deleteAdminCategory, updateAdminCategory } from '../../../api/category-service';
import ButtonLoader from '../../common/button-loader';
import Spacer from '../../common/spacer';
import { FiEdit, FiPlus, FiTrash } from 'react-icons/fi';

const AdminCategoryEdit = (props) => {

  
  const [loading, setLoading] = useState(false);
  const [categories, setCategories] = useState([])
  const { currentRecord } = useSelector((state) => state.misc);

console.log(currentRecord)


  const initialValues = {
    ...currentRecord,
    title: "",
    slug: "",
    icon: "",
    seq: 0,
    isActive: false,
    properties: [],
    property: "",
  };
  
  const validationSchema = Yup.object({
    title: Yup.string().required("Category name is required"),
    slug: Yup.string().required("Slug is required").min(5, "min5 char"),
    icon: Yup.string().required("Icon is required"),
    seq: Yup.string().required("Sequence is required"),
  });


  const handleDelete = async () => {
    console.log();

    try {
      setLoading(true);
      const resp = await deleteAdminCategory(props.data.id);
      console.log(resp);
    } catch (error) {
      const errMsg = error.response.data.message;
      swalAlert(errMsg, "error");
      setLoading(false);
    }
    setLoading(false);
  };

  const onSubmit = async (values) => {
    try {
      setLoading(true);
      await updateAdminCategory();
    } catch (error) {
      const errMsg = error.response.data.message;
      swalAlert(errMsg, "error");
      setLoading(false);
    }
  };

  const formik = useFormik({
    initialValues,
    validationSchema,
    onSubmit,
  });

  console.log(formik.values)

  useEffect(()=> {
    formik.setValues(currentRecord)
  },[currentRecord]);

  const addProperty = () => {
    const newProperty = formik.values.property.trim();

    // Check if the property already exists
    const isExistingProperty = formik.values.properties.some(
      (prop) => prop.name.toLowerCase() === newProperty.toLowerCase()
    );

    if (newProperty && !isExistingProperty) {
      const dto = {
        name: newProperty,
      };
      formik.setValues({
        ...formik.values,
        properties: [...formik.values.properties, dto],
      });
    }
    formik.setFieldValue("property", "");
  };

  const editProperty = (index) => {
    const editedProperty = prompt("Enter the new property name:");
    if (editedProperty) {
      const updatedProperties = [...formik.values.properties];
      updatedProperties[index].name = editedProperty;
      formik.setValues({
        ...formik.values,
        properties: updatedProperties,
      });
    }
  };

  const deleteProperty = (index) => {
    const confirmation = window.confirm(
      "Are you sure you want to delete this property?"
    );
    if (confirmation) {
      const updatedProperties = [...formik.values.properties];
      updatedProperties.splice(index, 1);
      formik.setValues({
        ...formik.values,
        properties: updatedProperties,
      });
    }
  };

  return (
    <Container className="adminCategoryNew ms-5 mt-5 row">
      <Col md={8} sm={12}>
        <Form onSubmit={formik.handleSubmit}>
          <div className="row">
            <Col>
              <div>
                <Form.Group className="mb-4">
                  <Form.Label className="mb-0">Title</Form.Label>
                  <Form.Control
                    type="text"
                    name="title"
                    className="bg-secondary"
                    value={formik.values.title}
                    onChange={formik.handleChange}
                    onBlur={formik.handleBlur}
                    isInvalid={formik.touched.title && formik.errors.title}
                  />
                  <Form.Control.Feedback type="invalid">
                    {formik.errors.title}
                  </Form.Control.Feedback>
                </Form.Group>
                <Form.Group className="mb-4">
                  <Form.Label className="mb-0">Slug</Form.Label>
                  <Form.Control
                    type="text"
                    name="slug"
                    className="bg-secondary"
                    value={formik.values.slug}
                    onChange={formik.handleChange}
                    onBlur={formik.handleBlur}
                    isInvalid={formik.touched.slug && formik.errors.slug}
                  />
                  <Form.Control.Feedback type="invalid">
                    {formik.errors.slug}
                  </Form.Control.Feedback>
                </Form.Group>
                <div className="row mb-5 ">
                  <Col>
                    <Form.Group>
                      <Form.Label>Icon</Form.Label>
                      <Form.Control
                        type="text"
                        name="icon"
                    className="bg-secondary"
                        value={formik.values.icon}
                        onChange={formik.handleChange}
                        onBlur={formik.handleBlur}
                        isInvalid={formik.touched.icon && formik.errors.icon}
                      />
                      <Form.Control.Feedback type="invalid">
                        {formik.errors.icon}
                      </Form.Control.Feedback>
                    </Form.Group>
                  </Col>
                  <Col>
                    <Form.Group>
                      <Form.Label>Sequence</Form.Label>
                      <Form.Control
                        type="text"
                        name="seq"
                    className="bg-secondary border-0"
                        value={formik.values.seq}
                        onChange={formik.handleChange}
                        onBlur={formik.handleBlur}
                        isInvalid={formik.touched.seq && formik.errors.seq}
                      />
                      <Form.Control.Feedback type="invalid">
                        {formik.errors.seq}
                      </Form.Control.Feedback>
                    </Form.Group>
                  </Col>
                  <Col>
                    <Form.Group className="status">
                      <Form.Label>Status</Form.Label>
                      <Form.Check
                        type="switch"
                        name="isActive"
                        checked={formik.values.isActive}
                        onChange={formik.handleChange}
                        onBlur={formik.handleBlur}
                        isInvalid={
                          formik.touched.isActive && formik.errors.isActive
                        }
                        label=""
                        className="status-switch"
                      />
                      <Form.Control.Feedback type="invalid">
                        {formik.errors.isActive}
                      </Form.Control.Feedback>
                    </Form.Group>
                  </Col>
                </div>
              </div>
            </Col>
          </div>

          <div className="button">
            <Button onClick={props.close} className="close m-4">
              CLOSE
            </Button>
            <Button
              disabled={!formik.isValid || loading}
              onClick={handleDelete}
              className="delete m-4"
            >
              {loading ? <ButtonLoader /> : ""}DELETE
            </Button>
            <Button
              type="submit"
              disabled={!formik.isValid || loading}
              className="save btn-login m-4"
            >
              {loading ? <ButtonLoader /> : ""}SAVE
            </Button>
          </div>
        </Form>
      </Col>

      <Col md={4} sm={12}>
        <div className="properties mx-5 p-3 bg-secondary  h-75">
          <Form.Group>
            <Form.Label className="mb-1">Properties</Form.Label>

            <div className="d-flex justify-content-between  ">
              <Form.Control
                type="text"
                name="property"
                value={formik.values.property}
                onChange={formik.handleChange}
                onBlur={formik.handleBlur}
                isInvalid={formik.touched.property && formik.errors.property}
              />
              <Form.Control.Feedback type="invalid">
                {formik.errors.property}
              </Form.Control.Feedback>

              <button onClick={() => addProperty()}>
                <FiPlus />
              </button>
            </div>
          </Form.Group>

          <ul>
            {formik.values.properties?.map((pro, index) => (
              <li key={index}>
                {pro.name}
                <button className="ms-3" onClick={() => editProperty(index)}>
                  <FiEdit />
                </button>
                <button onClick={() => deleteProperty(index)}>
                  <FiTrash />
                </button>
              </li>
            ))}
          </ul>
        </div>
      </Col>
    </Container>
  );
};
export default AdminCategoryEdit;