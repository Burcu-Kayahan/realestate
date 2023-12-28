import React from 'react'
import Adverts from '../../components/dashboard/admin-advert/admin-adverts'
import { Col, Container, Form, Row } from 'react-bootstrap'
import AdminAdveertsSearch from '../../components/dashboard/admin-advert/admin-search'

const AdminAdvertsPage = () => {
  return (
    <div>
      <AdminAdveertsSearch />
      <Adverts/>      
    </div>
  )
}

export default AdminAdvertsPage
