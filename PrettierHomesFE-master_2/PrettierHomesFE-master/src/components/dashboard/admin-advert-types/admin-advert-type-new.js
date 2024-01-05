import React, { useState } from 'react'
import { useDispatch, useSelector } from 'react-redux';
import * as Yup from "yup";
import { createAdvertType, getByIdAdvertType } from '../../../api/advert-type-service';
import { setListRefreshToken, setOperation } from '../../../store/slices/misc-slice';
import { swalAlert } from '../../../helpers/functions/swal';
import { Form, Formik, useFormik } from 'formik';
import { Button, Container, FormControl } from 'react-bootstrap';
import ButtonLoader from '../../common/button-loader';
import "./admin-advert-type-new.scss"
import { isInValid, isValid } from '../../../helpers/functions/forms';
import { createNewAdvert } from '../../../api/adverts-service';

const AdminAdvertTypeNew = (props) => {

  const dispatch = useDispatch();
  const [loading, setLoading] = useState(false);
  const {currentOperation} =useSelector((state)=>state.misc);

  console.log(currentOperation);
  const initialValues={
    title: ""
  }
  const validationSchema = Yup.object({
    title:Yup.string().required("Required")
  })
  const onSubmit =async (values)=>{
    setLoading(true);

    try{
    const resp= await createAdvertType(values);
    formik.resetForm();
    dispatch(setOperation(null));
   
     
    
    }catch(err){
      const errMsg = err.response.data.message;
      swalAlert(errMsg, "error");
    }finally{
     setLoading(false);
    }
  }
  const formik=useFormik({
    initialValues,
    validationSchema,
    onSubmit
  });
  const close =()=>{
    
    props.close();
  }
  return (
    <Container>
     
      
    <div className="adTypeNew">
       {loading && <p>Yükleniyor...</p>}
      {!loading && (
        <form onSubmit={formik.handleSubmit}>
    
     <input
       placeholder="Title"
       className="m-3 g-3  d-flex justify-content-center rounded"
       style={{ backgroundColor: "#F4F4F4" }}
      
       type="text"
       name="title"
     
      {...formik.getFieldProps("title")}
       isValid={isValid(formik, "title")}
       isInvalid={isInValid(formik, "title")}
      //  value={formik.values.title}
      //  onChange={formik.handleChange}
      //  error={
      //    formik.touched.title && formik.errors.title
      //      ? formik.errors.title
      //      : ""
      //  }
     />

    <div className=" button">
    <Button
      onClick={()=>{ dispatch(setOperation(""));}}
        className="close   m-4  "             
      >
       CLOSE
      </Button>
     
      <Button
        type="submit"
        className="create btn-login   m-4"
        disabled={!formik.isValid || loading}
        onClick={onSubmit}
      >
        {loading ? <ButtonLoader /> : ""}CREATE
      </Button>
    </div>
    </form>
      )}
</div></Container>
  )
}

export default AdminAdvertTypeNew  
