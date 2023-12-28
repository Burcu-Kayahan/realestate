import React from 'react'
import { Col, Container, Row } from 'react-bootstrap';
import "./styles/admin-custemer-adverts-cart.scss"
import { AiOutlineDelete } from "react-icons/ai";
import { FiEdit2 } from "react-icons/fi";
import { IoEyeOutline } from "react-icons/io5";
import { MdFavoriteBorder } from "react-icons/md";
import { CiLocationOn } from "react-icons/ci";
const AdvertPropertyCart = (props) => {
const {adverts} = props;
console.log(adverts)
  return (
    <Container>
    {adverts?.map((advert) =>{
      return ( <div key={advert.id} className="custemAdvertCart">       
      <Row>
        <Col
          sm={12}
          md={4}
          lg={2}
          className="d-flex justify-content-center align-items-center"
        >
          <img
            alt="ddf"
            src={advert.url}
          />
          {/* <div
            style={{
              border: "1px",
              width: "150px",
              height: "150px",
              backgroundColor: "blue",
            }}
          ></div> */}
        </Col>
        <Col sm={12} md={4} lg={2} className=" text-wrap">
          <p className="fw-bold ">Title : {advert.title}</p>
          <p>{advert.districtName} / {advert.cityName} / {advert.countryName}</p>
          <p>${advert.price}.00</p>
        </Col>
        <Col
          sm={12}
          md={4}
          lg={2}
          className="d-flex justify-content-center align-items-center"
        >
          <p>{advert.createAt}</p>
        </Col>
        <Col
          sm={12}
          md={4}
          lg={2}
          className="d-flex justify-content-center align-items-center"
        >
          <p className="statusP text-center">{advert.status}</p>
        </Col>
        <Col sm={12} md={4} lg={2} className="">
          <p>
            <IoEyeOutline /> {advert.viewCount}
          </p>
          <p>
            <MdFavoriteBorder /> 
            122-Eklenecek
          </p>
          <p>
            <CiLocationOn /> 
            452-Eklenecek
          </p>
        </Col>
        <Col
          sm={12}
          md={4}
          lg={2}
          className="d-flex justify-content-center align-items-center"
        >
          <button className="icons border-0 bg-white">
            {" "}
            <AiOutlineDelete />
          </button>
          <button className="icons  border-0 bg-white">
            <FiEdit2 />
          </button>
        </Col>
      </Row>
    </div>)
    })}
       
         
    </Container>
  );
}

export default AdvertPropertyCart;

//src="https://images.pexels.com/photos/106399/pexels-photo-106399.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
//