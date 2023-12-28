import React, { useState } from 'react'
import { useDispatch } from 'react-redux';
import * as Yup from "yup";
import { createAdvertType, getByIdAdvertType } from '../../../api/advert-type-service';
import { setListRefreshToken, setOperation } from '../../../store/slices/misc-slice';
import { swalAlert } from '../../../helpers/functions/swal';
import { Formik, useFormik } from 'formik';
import { Button } from 'react-bootstrap';
import ButtonLoader from '../../common/button-loader';
import "./admin-advert-type-new.scss"

const AdminAdvertTypeNew = (props) => {

  const dispatch = useDispatch();
  const [loading, setLoading] = useState(false);

  // const initialValues={
  //   title: ""
  // }
  // const validationSchema = Yup.object({
  //   title:Yup.string().required("Required")
  // })
  // const handleNew =async (values)=>{
  //   setLoading(true);

  //   try{
  //    const resp= await getByIdAdvertType(values);
  //     await createAdvertType(resp);
  //     formik.resetForm();
  //     dispatch(setListRefreshToken(Math.random()))
  //     dispatch(setOperation(null));
  //     swalAlert("AdvertType was deleted", "success");
  //   }catch(err){
  //     const errMsg = err.response.data.message;
  //     swalAlert(errMsg, "error");
  //   }finally{
  //    setLoading(false);
  //   }
  // }
  // const formik=useFormik({
  //   initialValues,
  //   validationSchema,
  //   handleNew
  // });
  const close =()=>{
    
    props.close();
  }
  return (
    <div className="adTypeNew"
      
    >
     <input
       placeholder="Title"
       className="m-3 g-3  d-flex justify-content-center rounded"
       style={{ backgroundColor: "#F4F4F4" }}
      
       type="text"
     />

    <div className=" button">
    <Button
        onClick={()=>{
          close()
        }}
        className="close   m-4  "             
      >
       CLOSE
      </Button>
     
      <Button
        type="submit"
        className="create btn-login   m-4"
        disabled={!Formik.isValid || loading}
      >
        {loading ? <ButtonLoader /> : ""}CREATE
      </Button>
    </div>
</div>
  )
}

export default AdminAdvertTypeNew  
