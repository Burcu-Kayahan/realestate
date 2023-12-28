import React from 'react'
import Categories from '../../components/dashboard/admin-categories/admin-categories'
import { Container } from 'react-bootstrap'
import { useSelector } from 'react-redux';
import Spacer from '../../components/common/spacer';
//import AdminCategoryNew from '../../components/dashboard/admin-categories/admin-category-new';
import AdminCategoryEdit from '../../components/dashboard/admin-categories/admin-category-edit';

const AdminCategoriesPage = () => {
    const { currentOperation } = useSelector((state) => state.misc);

    

  return (
    <Container>
      {/* {currentOperation === "new" && (
        <>
        
          <AdminCategoryNew /> <Spacer />
        </>
      )} */}
      {currentOperation === "edit" && (
        <>
      
          <AdminCategoryEdit /> <Spacer />
        </>
      )}
      <Categories />
    </Container>
  );
}

export default AdminCategoriesPage
