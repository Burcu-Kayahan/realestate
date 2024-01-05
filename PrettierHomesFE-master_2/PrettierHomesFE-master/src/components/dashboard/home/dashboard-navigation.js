import React from "react";
import { Card, Col, Container, Row } from "react-bootstrap";
import { Link } from "react-router-dom";
import adminDashboard from "../../../helpers/data/admin-dashboard.json";
import { FcCloth } from "react-icons/fc";

const DashboardNavigation = () => {
 

  return (
    <Container>
      <Row className="g-4 ">
        {adminDashboard.admin.map((item) => (
                <Col lg={4} md={6}  key={item.id}>
                <Link to={item.link} style={{textDecoration:"none"}}>
                  {" "}
                  <Card className="bg- " style={{backgroundColor:"#d9d9d9"}}>
                    <Card.Body className="text-center d-flex justify-content-center gap-4">
                      <div>
                        <h6><FcCloth/> {item.title}</h6>
                        <h1>{item.number}</h1>
                      </div>
                      <div>
                      <img src={`/images/admin-dashboard/${item.image}`} alt={item.title} width={100}/>
                  
                      </div>
                    </Card.Body>
                  </Card>
                </Link>
              </Col>
          
        ))}
      </Row>
     
    </Container>
  );
};

export default DashboardNavigation;
