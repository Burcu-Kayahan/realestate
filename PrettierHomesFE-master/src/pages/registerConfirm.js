import React, { useEffect, useState } from 'react'
import { Container } from 'react-bootstrap';
import Spacer from '../components/common/spacer';
import { useParams } from 'react-router-dom';
import { registerConfirm } from '../api/user-service';

const RegisterConfirm = (props) => {
 const { code } = useParams();
 const [message, setMessage]= useState(null);
 

 const confirm = async ()=>{
  try{
 const res = await registerConfirm(code);
setMessage(res.message)
  }catch(error){
alert(error)
  }

 }

 useEffect(()=>{
confirm();
 },[])
  return (
    <Container>
      <Spacer />
      <h1> {message && message}</h1>

      <Spacer />
    </Container>
  );
}

export default RegisterConfirm;