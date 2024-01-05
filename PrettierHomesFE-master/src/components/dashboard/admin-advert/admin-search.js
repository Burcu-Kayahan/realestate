import { Form, FormControl } from "react-bootstrap";
import React, { useEffect, useState } from "react";
import {
  Button,
  ButtonGroup,
  Col,
  Container,
  FormGroup,
  InputGroup,
  Row,
  ToggleButton,
} from "react-bootstrap";
import { getFilterByAdmin } from "../../../api/adverts-service";
import { swalAlert } from "../../../helpers/functions/swal";
import { getAllCategory } from "../../../api/category-service";
import { getAdvertTypeList } from "../../../api/advert-type-service";
import { CiSearch } from "react-icons/ci";
import ".././admin-advert/styles/admin-search.scss";
const AdminAdvertsSearch = (props) => {
  const [loading, setLoading] = useState(false);
  const [categories, setCategories] = useState([]);
  const [search, setSearch] = useState(props.search || "");
  const [adTypes, setAdTypes] = useState([]);
  const [filteredAdverts, setFilteredAdverts] = useState([]);
  const [status, setStatus] = useState([]);

  // const pushFeatures = () => {
  //   console.log("calisti");
  //   console.log(categories, search, adTypes, status);
  //   props.getFeatures(categories, search, adTypes, status);
  // };

  const handleSearch = async (values) => {
    setLoading(true);
    try {
      const resp = await getFilterByAdmin(values);
      setFilteredAdverts(resp);
    } catch (err) {
      const errMsg = err.response.data.message;
      swalAlert(errMsg, "error");
    } finally {
      setLoading(false);
    }
  };
  const loadAdTypes = async () => {
    try {
      const resp = await getAdvertTypeList();
      setAdTypes(resp);
    } catch (err) {
      console.log(err);
    }
  };
  const loadCategories = async () => {
    try {
      const resp = await getAllCategory();
      setCategories(resp.content);
    } catch (err) {
      console.log(err);
    }
  };
  useEffect(() => {
    loadAdTypes();
    loadCategories();
  }, []);

  useEffect(() => {
    let searchTrim = search.trim();
    if (searchTrim !== "") {
      handleSearch();
    } else {
      setFilteredAdverts([]);
    }
  }, [adTypes, categories, search]);
  return (
    <Form className="adverts-search">
      <FormGroup className="mb-3">
        <FormControl
          className="adverts-search-input"
          placeholder={search !== "" ? search : "Type Something"}
          onChange={(e) => setSearch(e.target.value)}
        />
      </FormGroup>
      <Row>
        <Col className="col-lg-3 col-sm-2 ">
          <FormGroup>
            <Form.Select
              className="adverts-category"
              value={categories}
              onChange={(e) => setCategories(e.target.value)}
            >
              <option key="0" value="">
                Categories
              </option>
              {categories?.map((ctgry) => {
                return (
                  <option key={ctgry.id} value={ctgry.id}>
                    {ctgry.name}
                  </option>
                );
              })}
            </Form.Select>
          </FormGroup>
        </Col>
        <Col className="col-lg-3 col-sm-2 ">
          <FormGroup>
            <Form.Select
              className="adverts-type"
              value={adTypes}
              onChange={(e) => setAdTypes(e.target.value)}
            >
              <option key="0" value="">
                Type
              </option>
              {adTypes?.map((adType) => {
                return (
                  <option key={adType.id} value={adType.id}>
                    {adType.name}
                  </option>
                );
              })}
            </Form.Select>
          </FormGroup>
        </Col>
        <Col className="col-lg-3 col-sm-2 ">
          <FormGroup>
            <Form.Select
              className="adverts-status"
              value={status}
              onChange={(e) => setStatus(e.target.value)}
            >
              <option key="0" value="">
                Status
              </option>
              {status?.map((stat) => {
                return (
                  <option key={stat.id} value={stat.id}>
                    {stat.name}
                  </option>
                );
              })}
            </Form.Select>
          </FormGroup>
        </Col>
        <Col className="col-lg-3 col-sm-2 ">
          <Button
            className="adverts-search-button "
            onClick={handleSearch}
          >
            <span className="fs-5">
              <CiSearch />
            </span>{" "}
            Search
          </Button>
        </Col>
      </Row>
    </Form>
  );
};
export default AdminAdvertsSearch;
