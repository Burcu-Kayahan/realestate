import React from 'react'
import Adverts from '../../components/dashboard/admin-advert/admin-adverts'
import { Col, Container, Form, Row } from 'react-bootstrap'
import AdminAdvertsSearch from '../../components/dashboard/admin-advert/admin-search'

const AdminAdvertsPage = () => {
  
 
  return (
    <div>
      <AdminAdvertsSearch  />
      <Adverts/>      
    </div>
  )
}

export default AdminAdvertsPage
