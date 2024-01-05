import React from 'react'
import { Container } from 'react-bootstrap'
import PageHeader from '../components/common/page-header'
import Spacer from '../components/common/spacer'
import ForgotPasswordForm from '../components/forgot-password-page/forgot-password-form'

const ForgotPasswordPage = () => {
  return (
    <Container>
        <Spacer height={20}/>
        <PageHeader title="FORGOT PASSWORD"/>
        <ForgotPasswordForm/>
        <Spacer/>
      
    </Container>
  )
}

export default ForgotPasswordPage
