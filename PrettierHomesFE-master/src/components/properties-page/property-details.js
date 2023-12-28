import React, { useEffect, useState } from "react";
import { Button, Card, Col, Container, Form, Row } from "react-bootstrap";
import { useLocation, useParams } from "react-router-dom";
import "./property-details.scss";
import { getAdvertDetailBySlug } from "../../api/adverts-service";
import { createOrDelete, isFavory } from "../common/favorites";
import { FaHeart,FaRegHeart  } from "react-icons/fa";

const PropertyDetails = () => {
  const { slug } = useParams();
  const [data, setdata]= useState(null);
  const [images, setImages]=useState([])
  const [img, setImg]=useState("https://images.pexels.com/photos/19453408/pexels-photo-19453408/free-photo-of-kent-peyzaj-su-yaz.jpeg?auto=compress&cs=tinysrgb&w=600&lazy=load")
  const [properties, setProperties]=useState([])
  const [favory, setIsFavory]=useState(false)


  
  const loadData =async()=>{   
    try{
  const resp = await getAdvertDetailBySlug(slug);
  setdata(resp.advert);
  setImages(resp.imges)
  setProperties(resp.values)
  setImg(resp.imges[0].url)
  getisFavory(resp.advert.id);  
    }catch(err){
    console.log(err)
     }
  }

  const getisFavory =async(id)=>{
    try{
    const res = await isFavory(id);
    setIsFavory(res);
    }catch(err){

    }}
  useEffect(()=>{
    loadData();
  },[slug]);


  const imgs = [
    {
      id: 0,
      value:
        "https://images.pexels.com/photos/19453408/pexels-photo-19453408/free-photo-of-kent-peyzaj-su-yaz.jpeg?auto=compress&cs=tinysrgb&w=600&lazy=load",
    },
    { id: 1, value: "https://source.unsplash.com/user/c_v_r/1900x800" },
    { id: 2, value: "https://source.unsplash.com/user/c_v_r/100x100" },
  ];
  const [wordData, setWordData] = useState(imgs[0]);
  const [val, setVal] = useState(0);
  const handleClick = (index) => {
    console.log(index);
    setVal(index);
    const wordSlider = imgs[index];
    setWordData(wordSlider);
  };

  const handleFavory = async() => {
    try{  
      const res = await createOrDelete(data.id);    
    }catch(err){
      console.log(err)
    }
    setIsFavory(!favory); 
    };

  return (
    <Container>
      <Row className="justify-content-center mt-5">
        <Col xs={12} lg={8} >
          <Card>
            <Card.Body>
              <img src={img} height="300" width="100%" />

              <div className="d-flex gap-5 mt-5">
                {images?.map((data, i) => (
                  <div className="thumbnail" key={i}>
                    <img
                      className={wordData.id == i ? "clicked" : ""}
                      src={data.url}
                      onClick={() =>setImg(data.url)}
                      height="70"
                      width="100"
                    />
                     
                  </div>
                ))}
                {favory ? <span className="heart-icon-full btn" onClick={()=>{
             
             handleFavory()}}>
        <FaHeart />            
         </span> : <span className="heart-icon btn" onClick={()=>{
          
          handleFavory()}}>
         <FaRegHeart />           
         </span> }
              </div>
            </Card.Body>
          </Card>
          <p> {data !==null ? data.title :"...."}</p>          
          <Card>
            <Card.Body>
              <Card.Title>Description </Card.Title>
              <Card.Text>
                {data !==null ? data.description :"...."}
              </Card.Text>
            </Card.Body>
          </Card>
          <Card className="details">
            <Card.Body>
              <h3>Details </h3>
              <Card.Text className="d-flex ">
                <Col className="info d-flex flex-column gap-2">
                  {properties?.map((value) =>(
                     <div key={value.id}>
                     <h4>{value.keyName}</h4>
                     <h4>{value.value}</h4>
                   </div>
                  ))}
                  
                </Col>
                <Col className="info d-flex flex-column  gap-2">
                  <div>
                    <h4>Year od Built</h4>
                    <h4>120</h4>
                  </div>
                  <div>
                    <h4>Furtiture</h4>
                    <h4>2</h4>
                  </div>
                  <div>
                    <h4>Maintenance</h4>
                    <h4>4</h4>
                  </div>
                  <div>
                    <h4>Terrace</h4>
                    <h4>1</h4>
                  </div>
                </Col>
              </Card.Text>
            </Card.Body>
          </Card>
          <Card>
            <Card.Body>
              <Card.Title>Location </Card.Title>
              <Card.Text className="d-flex gap-5">
                <div>{data !==null ? data.country.name : "xxx"}:</div>
                <div>{data !==null ? data.city.name : "xxx"}:</div>
                <div>{data !==null ? data.district.name: "xxx"}:</div>
              </Card.Text>
              <iframe
                src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d1571543.3278485276!2d31.026053301554516!3d39.70773029069568!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x14d34ef01d406c31%3A0x5669332ebe02ad84!2sAnkara!5e0!3m2!1sfr!2str!4v1701539114535!5m2!1sfr!2str"
                frameborder="0"
              ></iframe>
            </Card.Body>
          </Card>
        </Col>
        <Col lg={4}>
          <Card>
            <Form className="px-5">
              <h2 className="pt-3 mb-0 ">Schedule a tour</h2>
              <span>Choose your preferred day</span>
              <div className="mt-5">
                <Col className="mb-3">
                  <Form.Control type="text" placeholder="Tour date" />
                </Col>
                <Col>
                  <Form.Control type="text" placeholder=" Tour time" />
                </Col>
              </div>
              <Col>
                <Button type="submit" variant="success" className="text-light mt-3 mb-5 text-center w-100">
                  Submit a tour request
                </Button>
              </Col>
            </Form>
          </Card>
        </Col>
      </Row>
    </Container>
  );
};

export default PropertyDetails;
