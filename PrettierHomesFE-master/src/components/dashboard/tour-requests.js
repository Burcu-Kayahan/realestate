import React, { useState, useEffect, useCallback } from 'react';
import { Button, Container, Form, FormControl, Table } from 'react-bootstrap';
import { getTourRequests } from '../../api/tour-request-service';
import { FaEdit, FaTrash} from "react-icons/fa";
import { deleteTourRequest } from '../../api/tour-request-service';
import { VscSearch } from "react-icons/vsc";

import "./tour-requests.scss"

const TourRequestList = () => {
  const [searchQuery, setSearchQuery] = useState('');
  const [tourRequests, setTourRequests] = useState([]);
 

  const fetchTourRequests = useCallback(async () => {
    try {
      const response = await getTourRequests(searchQuery);
      setTourRequests(response.data);;
    } catch (error) {
      // Handle error
    }
  }, [searchQuery]);

  useEffect(() => {
    fetchTourRequests();
  }, [fetchTourRequests]);

  const handleSearchChange = (event) => {
    setSearchQuery(event.target.value);
  };

  const handleSearch = () => {
    fetchTourRequests();
  };

  const handleDeleteTourRequest = async (id) => {
    try {
      await deleteTourRequest(id);
      fetchTourRequests();
    } catch (error) {
      // Handle error
    }
  };

  return (
    <Container className='tour-request'>
      <div>
        <h1>Tour Requests</h1>
        <form inline="true" className='d-flex'>
  <FormControl
    type="text"
    placeholder="Search"
    value={searchQuery}
    onChange={handleSearchChange}
    className="mr-sm-2 searchInput"
  />
  <Button variant="primary" onClick={handleSearch}>
    <VscSearch />
  </Button>
</form>
        <Table  className='mt-4'>
          <thead>
            <tr>
              <th>Property</th>
              <th>Owner</th>
              <th>Guest</th>
              <th>Status</th>
              <th>Tour Date</th>
              <th>Tour Time</th>
              <th>Action</th>
            </tr>
          </thead>

         <tbody>
  {tourRequests.length > 0 ? (
    tourRequests.map((tourRequest) => (
      <tr key={tourRequest.id}>
        <td>{tourRequest.advert}</td>
        <td>{tourRequest.ownerUser}</td>
        <td>{tourRequest.guestUser}</td>
        <td>{tourRequest.status}</td>
        <td>{tourRequest.tourDate}</td>
        <td>{tourRequest.tourTime}</td>
        <td>
          <Button variant="danger" onClick={() => handleDeleteTourRequest(tourRequest.id)}>
            <FaTrash />
          </Button>{' '}
          <Button variant="info">
            <FaEdit />
          </Button>
        </td>
      </tr>
    ))
  ) : (
    <tr>
      <td colSpan="7">No tour requests found.</td>
    </tr>
  )}
</tbody>
        </Table>
      </div>
    </Container>
  );
};

export default TourRequestList;