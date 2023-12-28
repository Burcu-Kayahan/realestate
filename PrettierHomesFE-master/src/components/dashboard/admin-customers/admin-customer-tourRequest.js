import React, { useEffect, useState } from 'react'
import { Button, Container } from 'react-bootstrap';
import { getAdvertsByUser } from '../../../api/adverts-service';
import CustomPaginaton from '../../common/custom-pagination';

const AdminCustomerTourRequests = (props) => {

  const [adverts, setAdverts]= useState([]);
  const [loading, setLoading] = useState(true);
  const [totalRows, setTotalRows] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [flag, setFlag] = useState(false);
  const [search, setSearch] = useState("");
  const [page, setPage] = useState(0);
  const [size, setSize] = useState(3);


  const loadData = async () => {
    try {
      const resp = await getAdvertsByUser(page, size, props.id);     
      setTotalRows(resp.totalElements);
      setTotalPages(resp.totalPages);
      setAdverts(resp.content);
      setPage(resp.number)
    } catch (err) {
      console.log(err);
    } finally {
      setLoading(false);
    }
  };

  const onPage = (event) => {
    setPage(event)
  };

  useEffect(() => {    
    loadData();
  }, [size, flag, page]);

  const customPage =(page)=>{
      setPage(page)
  }

  return (
    <Container>
        <Button  className='logs_title'> <span>Tour Requests</span> </Button>
      <p>AdminCustomerTourRequests;</p>
      <CustomPaginaton 
        customPage={customPage}
        totalPages={totalPages}
        page={page}/>
      </Container>
  )
}

export default AdminCustomerTourRequests;