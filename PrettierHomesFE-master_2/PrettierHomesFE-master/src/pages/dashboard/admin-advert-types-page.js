
import React,{useState} from "react";
import { Container } from "react-bootstrap";
import Spacer from "../../components/common/spacer";
import AdminAdvertTypeNew from "../../components/dashboard/admin-advert-types/admin-advert-type-new";
import AdminAdvertTypeEdit from "../../components/dashboard/admin-advert-types/admin-advert-type-edit";
import AdminAdvertTypes from "../../components/dashboard/admin-advert-types/admin-advert-types";
import { useSelector } from "react-redux";





const AdminAdvertTypesPage = () => {
  const { currentOperation } = useSelector((state) => state.misc);
  const [data,setData]=useState();

  const getData=(data2)=>{
   setData(data2);
  }
    

    
  return (
    <Container>
       <Spacer height={40} />  
       
     {currentOperation === "new" && ( <> <AdminAdvertTypeNew /> <Spacer /> </> )}
      {currentOperation === "edit" && (<> <AdminAdvertTypeEdit data={data}  /><Spacer /> </> )}
      <AdminAdvertTypes getData={getData} />
    </Container>
  );
};

export default AdminAdvertTypesPage;

