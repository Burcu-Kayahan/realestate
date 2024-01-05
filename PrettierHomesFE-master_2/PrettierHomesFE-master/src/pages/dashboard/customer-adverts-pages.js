import React, {  useState } from 'react'
import CustomerAdverts from '../../components/dashboard/customer/customer-adverts';
import CustomerAdvertEdit from '../../components/dashboard/customer/customer-advert-edit';
import { Container } from 'react-bootstrap';
import Spacer from '../../components/common/spacer';

const CustomerAdvertsPages = () => {

    const [isEdit, setIsEdit]= useState(false); 
    const [data, setData]= useState();
  
  
    const handleEdit =(data)=>{
      setData(data);
      setIsEdit(true);
      
    }
  
    const close =()=>{
      setIsEdit(false);
      setData(null);
    }
  return (
   
    <Container>
         <Spacer />
       {isEdit && <CustomerAdvertEdit data={data} close={close}/>}
        <Spacer />
       {!isEdit && <CustomerAdverts edit={handleEdit}/>}
      <Spacer />
    </Container>
  )
}

export default CustomerAdvertsPages;