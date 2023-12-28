import React from 'react'
import PageHeader from '../components/common/page-header';
import Spacer from '../components/common/spacer';
import ChangePasswordForm from './ChangePasswordForm';
import { Container } from 'react-bootstrap';


const ChangePassword = () => {
    return (
        <Container>
            <Spacer height={20} />
            <PageHeader title="CHANGE PASSWORD" />
            <Spacer />
            <ChangePasswordForm />
            <Spacer />
       
        </Container>
    );
};

export default ChangePassword;