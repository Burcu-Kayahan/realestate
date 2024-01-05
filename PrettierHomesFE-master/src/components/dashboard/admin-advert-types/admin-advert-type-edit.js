import React, { useEffect, useState } from "react";
import { swalAlert, swalConfirm } from "../../../helpers/functions/swal";
import {
  deleteAdvertType,
  getByIdAdvertType,
  updateAdvertType,
} from "../../../api/advert-type-service";
import { useDispatch, useSelector } from "react-redux";
import * as Yup from "yup";

import { Formik, useFormik } from "formik";
import {
  setListRefreshToken,
  setOperation,
} from "../../../store/slices/misc-slice";

import "./admin-advert-type-edit.scss";
import { Button, Col, Container, Row } from "react-bootstrap";
import { isInValid, isValid } from "../../../helpers/functions/forms";
import ButtonLoader from "../../common/button-loader";

const AdminAdvertTypeEdit = (props ) => {
  const dispatch = useDispatch();
  const [loading, setLoading] = useState(false);
  const [adType, setAdType] = useState({});
  const { currentOperation } = useSelector((state) => state.misc);

  console.log(currentOperation);

  const initialValues = {
  //  title: props.data.title,
    id: props.data.id,
  };
  console.log(props.data)
  const validationSchema = Yup.object({
    title: Yup.string().required("Required"),
  });
  useEffect(()=>{
    formik.setValues(props.data)
  },[props.data])

  const handleDelete = async () => {
    console.log();

    try {
      setLoading(true);
      const resp = await deleteAdvertType(props.data.id);
      console.log(resp);
      swalAlert("Data silindi", "success");
      props.close();
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
      await updateAdvertType();
     // props.close();
    //  resetForm(); // Formu sıfırla
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

  return (
    <div className="adTypeEdit">
      {loading && <p>Yükleniyor...</p>}
      {!loading && (
        <form onSubmit={formik.handleSubmit}>
          <input
            placeholder="Title"
            className="m-3 g-3 d-flex justify-content-center rounded"
            style={{ backgroundColor: "#F4F4F4" }}
            type="text"
            name="title"
            value={formik.values.title}
            onChange={formik.handleChange}
            error={
              formik.touched.title && formik.errors.title
                ? formik.errors.title
                : ""
            }
          />
          <div className="button">
            <Button onClick={()=>{ dispatch(setOperation(""));}} className="close m-4">
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
              onClick={onSubmit}
              className="save btn-login m-4"
            >
              {loading ? <ButtonLoader /> : ""}SAVE
            </Button>
          </div>
        </form>
      )}
    </div>
  );
};

export default AdminAdvertTypeEdit;
