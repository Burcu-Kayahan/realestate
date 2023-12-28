import React from 'react'
import NewAdvertForm from '../../components/dashboard/add-new-advert'
import PageHeader from '../../components/common/page-header'
import { Container } from 'react-bootstrap'

const AddNewAdvertPage = () => {
  return (
    <Container>
      <PageHeader title="NEW ADVERT"/>
      <NewAdvertForm/>
    </Container>
  )
}

export default AddNewAdvertPage
