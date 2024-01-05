import React from 'react'
import { Container } from 'react-bootstrap';
import Spacer from '../components/common/spacer';
import PageHeader from '../components/common/page-header';
import ProfileUpdateForm from './profileUpdateForm';

const MyProfilePage = () => {
  return (
    <Container>
      <Spacer height={20} />
      <PageHeader title="MY PROFILE" />
      <Spacer />
      <ProfileUpdateForm/>
      <Spacer />
    </Container>
  );
}

export default MyProfilePage