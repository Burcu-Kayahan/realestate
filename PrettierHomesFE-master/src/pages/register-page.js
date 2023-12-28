import React from 'react'
import { Container } from 'react-bootstrap'
import Spacer from '../components/common/spacer'
import RegisterForm from '../components/register-page/register-form'
import PageHeader from '../components/common/page-header'

const RegisterPage = () => {
  return (
    <Container>
        <Spacer height={20}/>
        <PageHeader title="REGISTER"/>
        <Spacer/>
        <RegisterForm/>
        <Spacer/>
      
    </Container>
  )
}

export default RegisterPage
