import { Button, Col, Container, Row } from 'react-bootstrap'
import { AiOutlineDelete } from 'react-icons/ai'
import React, { useState, useEffect } from 'react';
import "./styles/admin-customer-favorites-cart.scss"
import CustomPaginaton from '../../common/custom-pagination'
import { getAdvertsByUser } from '../../../api/adverts-service'
import { deleteFovoritesByUserForAdmin, getFovoritesByUserForAdmin } from '../../../api/favorites-service'



const AdminCustomerFavorites = (props) => {
  const [favorites, setFavorites]= useState([]);
  const [loading, setLoading] = useState(true);
  const [totalRows, setTotalRows] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [flag, setFlag] = useState(false);
  const [search, setSearch] = useState("");
  const [page, setPage] = useState(0);
  const [size, setSize] = useState(3);







 





  

  const loadData = async () => {   
    try {
      const resp = await getFovoritesByUserForAdmin(page, size, props.id);     
      setTotalRows(resp.totalElements);
      setTotalPages(resp.totalPages);
      setFavorites(resp.content);
      setPage(resp.number)
      console.log(resp.content)
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

  const deleteHandle =async (id)=>{
    try{
      const res = await deleteFovoritesByUserForAdmin(id);
      setFlag(!flag)
    }catch(err){
      console.log(err)
    }

  }
//1 buttonun icersinde onClick yazilacak, parametre gonderilecek
//2 onClikck  bizi yukarida yazdigimiz methoda gonderecek
//3  yukarida yazdigimiz delete methodu bizi service de ki methoda gonderecek
  return (

    <Container>
      <Button  className='logs_title'> <span>Favorites</span> </Button>
      {favorites?.map((favory)=>{
        return (  <div className="custemFavoriteCart">

        <Row id={favory.id}>
          <Col sm={12}
            md={4}
            lg={2}
            className="d-flex justify-content-center align-items-center">

            <img
              alt="ddf"

              src={favory.url}

            />
          
          </Col>
          <Col sm={12} md={4} lg={2} className=" text-wrap">

            <p className="fw-bold ">Title : {favory.title}</p>
            <p>{favory.districtName} / {favory.cityName}</p>
            <p>${favory.price}.00</p>

          </Col>
          <Col
            sm={12}
            md={4}
            lg={2}
            className="d-flex justify-content-center align-items-center"
          >
            <p style={{ fontWeight: 'bold' }}>{favory.categoryName}</p>
          </Col>
          <Col
            sm={12}
            md={4}
            lg={2}
            className="d-flex justify-content-center align-items-center"
          >
            <p style={{ fontWeight: 'bold' }}>{favory.advertTypeName}</p>
          </Col>
          <Col>
          
          
          </Col>
          <Col sm={12}
            md={4}
            lg={2}
            className="d-flex justify-content-center align-items-center"
            >
          <button
            sm={12}
            md={4}
            lg={2}
           className="icons  border-0 " 
           onClick={()=>deleteHandle(favory.favoriId
            )}>

          <AiOutlineDelete />
          </button>
          
          </Col>
        </Row>

      </div>)
      })}
    

      <CustomPaginaton 
        customPage={customPage}
        totalPages={totalPages}
        page={page}/>

    </Container>
  )
}

export default AdminCustomerFavorites
