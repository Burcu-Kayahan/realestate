import React, { useEffect, useState } from 'react'
import AdvertPropertyCart from './admin-custemer-advert-cart';
import CustomPaginaton from '../../common/custom-pagination';
import { getAdvertsByUser } from '../../../api/adverts-service';
import { Button } from 'react-bootstrap';

const AdminCustomerAdvertsx = (props) => {
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
  

  return <div>
      <Button className='logs_title'> <span>Adverts</span> </Button>
    <AdvertPropertyCart adverts={adverts} />
    <CustomPaginaton 
        customPage={customPage}
        totalPages={totalPages}
        page={page}/>
    </div>;
};

export default AdminCustomerAdvertsx;