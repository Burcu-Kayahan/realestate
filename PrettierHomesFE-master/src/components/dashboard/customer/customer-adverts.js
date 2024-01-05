import React, { useEffect, useState } from "react";
import { Button, Col, Container, Row } from "react-bootstrap";
import { AiOutlineDelete } from "react-icons/ai";
import { FiEdit2 } from "react-icons/fi";
import { IoEyeOutline } from "react-icons/io5";
import { MdFavoriteBorder } from "react-icons/md";
import { CiLocationOn } from "react-icons/ci";
import "./styles/customer-adverts.scss";
import { getMyAdverts } from "../../../api/adverts-service";
import CustomPagination from "../../common/custom-pagination";
import PageHeader from "../../common/page-header";
import Spacer from "../../common/spacer";

const CustomerAdverts = (props) => {
  const [loading, setLoading] = useState(true);
  const [data, setData] = useState(null);
  const [totalRows, setTotalRows] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [flag, setFlag] = useState(false);
  const [search, setSearch] = useState("");
  const [page, setPage] = useState(0);

  const [lazyState, setlazyState] = useState({
    first: 0,
    rows: 3,
    page: 0,
    sortField: "",
    sortOrder: "DESC",
  });

  const loadData = async (page, size, sort, type) => {
    try {
      const resp = await getMyAdverts(page, size, sort, search);
      console.log(resp);
      setTotalRows(resp.totalElements);
      setTotalPages(resp.totalPages);
      setData(resp.content);
      setPage(resp.number);
    } catch (err) {
      console.log(err);
    } finally {
      setLoading(false);
    }
  };

  const onPage = (event) => {
    setlazyState((prevState) => {
      return {
        ...prevState,
        first: event.first,
        page: event.first / event.rows,
        rows: event.rows,
      };
    });
  };

  useEffect(() => {
    // zaman ayari yarim saniyr bekle
    loadData(page, lazyState.rows, lazyState.sortField, lazyState.sortOrder);
  }, [lazyState, search, flag, page]);

  const customPage = (page) => {
    setPage(page);
  };

  const handleEdit = (x) => {
          props.edit(); //
  };
  return (
    <Container>
      <div className="pagetitle">
        <PageHeader style={{ padding: "20px" }} title="MY ADVERTS" />
      </div>
      <div className="customtitle">
        <Col sm={12} md={4} lg={2} className="">
          <div
            className="d-grid justify-content-between align-items-center fw-bold"
            style={{
              gridTemplateColumns: "27rem 14rem 6rem 17rem auto",
              gap: "1.5rem",
              margin: "1rem",
              marginTop: "3rem",
            }}
          >
            <div>Property</div>
            <div>Date Published</div>
            <div>Status</div>
            <div>View/Like/Tour</div>
            <div>Action </div>
          </div>
        </Col>
      </div>
      {data &&
        data.map((x) => (
          <div
            key={x.id}
            className="custemAdvertCart"
            style={{
              margin: "1rem",
            }}
          >
            <Row>
              <Col
                sm={12}
                md={4}
                lg={2}
                className="d-flex justify-content-center align-items-center"
              >
                <img
                  alt="ddf"
                  src="https://images.pexels.com/photos/106399/pexels-photo-106399.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
                />
              </Col>
              <Col
                sm={12}
                md={4}
                lg={2}
                className="text-wrap"
                style={{ padding: "0 1px" }}
              >
                <p className="fw-bold ">Equestrian Family Home </p>
                <p>California City, CA, USA </p>
                <p>$1400.00 </p>
              </Col>
              <Col
                sm={12}
                md={4}
                lg={2}
                className="d-flex justify-content-center align-items-center"
              >
                <p>03/04/2023 </p>
              </Col>
              <Col
                sm={12}
                md={4}
                lg={2}
                className="d-flex justify-content-center align-items-center"
              >
                <p className="statusP text-center">Pending </p>
              </Col>
              <Col
                sm={12}
                md={4}
                lg={2}
                className="justify-content-center align-items-center"
              >
                <p>
                  <IoEyeOutline /> 125
                </p>
                <p>
                  <MdFavoriteBorder />
                  122
                </p>
                <p>
                  <CiLocationOn />
                  452
                </p>
              </Col>
              <Col
                sm={12}
                md={4}
                lg={2}
                className="d-flex justify-content-center align-items-center"
              >
                <button
                  className="icons border-0 bg-white"
                 
                >
                  {" "}
                  <AiOutlineDelete />
                </button>
                <button
                  className="icons border-0 bg-white"
                  onClick={handleEdit}
                >
                  <FiEdit2 />
                </button>
              </Col>
            </Row>
          </div>
        ))}
      {/* Toplam eleman sayısı */}
      <div className="ms-2 pt-3">Toplam Element: {totalRows}</div>
      {/* Sayfalama bileşeni */}
      <CustomPagination
        customPage={customPage}
        totalPages={totalPages}
        page={page}
      />

      <Spacer height={200} />
    </Container>
  );
};

export default CustomerAdverts;
