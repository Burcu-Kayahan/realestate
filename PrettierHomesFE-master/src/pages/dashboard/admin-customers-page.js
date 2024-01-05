import React, { useState } from 'react'
import Customers from '../../components/dashboard/admin-customers/admin-customers'
import AdminCustomerEditLayout from '../../components/dashboard/admin-customers/admin-customer-edit-layout'

const AdminCustomersPage = () => {
  const [isEdit, setIsEdit]= useState(false);
  const [id, setSlug]=useState("")
  const [data, setData]= useState();


  const handleEdit =(data)=>{
    setData(data);
    setIsEdit(true);
    
  }

  const close =()=>{
    setIsEdit(false);
    setData(null);
  }

  // burada veya customer componentinde tum userlar cekilecek ve render edilcek. buttonla edit sayfasi acilacak ve ilgili user in id ile tum
  // veriler db den ayri ayri cekilecek ve render edilecek.
  return (
    <div>
    {isEdit &&   <AdminCustomerEditLayout id={id} data={data} close={close} />}
    {!isEdit && <Customers edit={handleEdit}/>}
    </div>
  )
}

export default AdminCustomersPage
