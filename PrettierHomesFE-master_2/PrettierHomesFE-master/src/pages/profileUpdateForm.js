
import { useFormik } from 'formik';
import React, { useEffect, useState } from "react";
import { Button, Container,Form} from 'react-bootstrap';
import * as Yup from "yup";
import { isInValid, isValid } from "../helpers/functions/forms";
import { swalAlert } from "../helpers/functions/swal";
import { setToLocalStorage } from "../helpers/functions/encrypted-storage";
import { useDispatch } from "react-redux";
import { login as updateSuccess } from "../store/slices/auth-slice";
import { Link, useNavigate } from "react-router-dom";

import { getMyProfile, updateProfile } from '../api/user-service';


const ProfileUpdateForm = () => {
  const [loading, setLoading] = useState(false);
  const dispatch = useDispatch();
  const navigate = useNavigate();
 const [user,setUser]=useState();


const LoadData=async()=>{
  try{
    const rest=await getMyProfile();
    setUser(rest);
console.log(rest);
  }catch(err){
    console.log(err);

  }
}
useEffect(()=>{LoadData()},[])
useEffect(() => {
  // user değiştiğinde formik'in initialValues'ını güncelle
  if (user) {
    formik.setValues({
      firstName: user.firstName || '',
      lastName: user.lastName || '',
      phone: user.phone || '',
      id:user.id || 0 ,
      email:user.email || '',
      role:user.role || '',
    });
  }
}, [user]);








  const initialValues={
    firstName:"",
    lastName:"",
    phone:"",
    id:0,
    email:"",
    role:"",


  }

  const validationSchema=Yup.object({
    firstName:Yup.string().required("enter first name"),
    lastName:Yup.string().required("enter last name"),
    phone:Yup.number().required("enter phone number")

  });

  const onSubmit = async (values) => {
    setLoading(true);
    

    try {
      const resp = await updateProfile(values);
      //const { user, token } = resp;
      // console.log(resp);
     //setToLocalStorage("token", token);
      // console.log(token);

      dispatch(updateSuccess(user));
      swalAlert("your profile updated" , "success");
      //navigate("/dashboard"); // burasi daha sonra aktif ewdilecek.
    } catch (err) {
      console.log(err);
      const errMsg = err.response.data.message;
      swalAlert(errMsg, "error");
    } finally {
      setLoading(false);
    }
  };

  const formik=useFormik({
    initialValues,
    validationSchema,
    onSubmit
  });
  return (
    <Container className='myProfilPage'>
    <Form noValidate onSubmit={formik.handleSubmit}>
    <Form.Group className="mb-3" >
      <Form.Label className="mb-0 label">First name</Form.Label>
      <Form.Control className="control" type="text" 
      {...formik.getFieldProps("firstName")}
      isInvalid={isInValid(formik, "firstName")}
      isValid={isValid(formik, "firstName")}
       />
         <Form.Control.Feedback type="invalid">
                    {formik.errors.firstName}
                  </Form.Control.Feedback>
    </Form.Group>

    <Form.Group className="mb-3" >
      <Form.Label className="mb-0 label">Last name </Form.Label>
      <Form.Control className="control" type="text"
       {...formik.getFieldProps("lastName")}
       isInvalid={isInValid(formik, "lastName")}
       isValid={isValid(formik, "lastName")} />
  <Form.Control.Feedback type="invalid">
                    {formik.errors.lastName}
                  </Form.Control.Feedback>
    </Form.Group>


  

    <Form.Group className="mb-3" >
      <Form.Label className="mb-0 label">Phone</Form.Label>
      <Form.Control className="control" type="number"{...formik.getFieldProps("phone")}
        isInvalid={isInValid(formik, "phone")}
        isValid={isValid(formik, "phone")}   />
          <Form.Control.Feedback type="invalid">
                    {formik.errors.phone}
                  </Form.Control.Feedback>
    </Form.Group>

   
    <Form.Group className="mb-3" >
      <Form.Label className="mb-0 label">Email</Form.Label>
      <Form.Control className="control" type="text" disabled 
      {...formik.getFieldProps("email")}
    
       />
         
    </Form.Group>
   
    <Button variant="primary" type="submit"
      className="btn-login w-50 mx-auto m-4 d-flex justify-content-center"
    
    disabled={!formik.isValid || loading}>
       {/* <Link
                    className="text-dark "
                    onClick={() => {
                      navigate("/login");
                    }}
                  ></Link> */}
      UpDate
    </Button>
  </Form>
  </Container>
);
}


export default ProfileUpdateForm
