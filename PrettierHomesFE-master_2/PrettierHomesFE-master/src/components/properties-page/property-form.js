import React, { useEffect, useState } from "react";
import { Button, Col, Form, Row } from "react-bootstrap";
import { CiSearch } from "react-icons/ci";
import "./property-form.scss";
import { getAdvertTypeList } from "../../api/advert-type-service";
import { getAllCategory } from "../../api/category-service";
import { getCities, getCountries, getDistricts } from "../../api/adverts-service";

const PropertyForm = (props) => {
  const [aTypes, setATypes]= useState([]);
  const [type, setType]= useState(props.type || "");
  const [categories, setCategories]= useState([]);
  const [category, setCategory]= useState(props.category || "");
  const [search, setSearch]=useState(props.search || "");
 const [properties, setProperties]= useState([]);
 const [countries, setCountries] = useState([]);
 const [cities, setCities] = useState([]);
 const [districts, setDistricts] = useState([]);
 const [country, setCountry] = useState("");
 const [city, setCity] = useState("");
 const [priceMin, setPriceMin] = useState("");
 const [priceMax, setPriceMax] = useState("");
 const [district, setDistrict] = useState("");

 const pushFeatures =()=>{
  console.log("calisti")
  console.log(type, category, search,country, city,district,priceMin,priceMax)
  props.getFeatures(type, category, search,country, city,district,priceMin,priceMax);

}

  const loadATypes = async ()=>{
    try{
    const resp = await getAdvertTypeList();
    setATypes(resp) 
    }catch(err){
      console.log(err)
    }
  }
  const loadCategories = async ()=>{
  try{
    const resp = await getAllCategory();
    setCategories(resp.content);
  }catch(err){
    console.log(err)
  }
  }
  
  useEffect(()=>{
    loadATypes();
    loadCategories(); 
    loadCountries()
  },[]);

  useEffect(()=>{
    if(country !==""){
      loadCities();
    }
  },[country]);

  useEffect(()=>{
    if(city !==""){
      loadDistricts();
    }
  },[city]);
  const loadCountries = async () => {
    try {
      const resp = await getCountries();
      setCountries(resp);
    } catch (err) {
      alert(err);
    }
  };
  const loadCities = async () => {
    try {
      const resp = await getCities(country);
      setCities(resp);
    } catch (err) {
      alert(err);
    }
  };
  const loadDistricts = async () => {
    try {
      const resp = await getDistricts(city);
      setDistricts(resp);
    } catch (err) {
      alert(err);
    }
  };



  return (
    <Form className="properties-page-form">
      <Form.Group className="mb-5">
        <Form.Label>Find your home</Form.Label>
        <Form.Control        
          className="property-search-input"
          placeholder={search !=="" ? search : "What are you looking for?"}
          onChange={(e)=> setSearch(e.target.value)}
        />
      </Form.Group>

      <Form.Group className="mb-5">
        <Form.Label>Property Type</Form.Label>
        <Form.Check label="All"  name="property-by-status" type="radio" value={type} key="all"        
         onChange={()=> setType("")} />
        {aTypes?.map((typeObje)=>{
          return (<Form.Check key={typeObje.id} label={typeObje.title} name="property-by-status" type="radio" id={typeObje.id}
          
           onChange={()=> setType(typeObje.id)}/> )
        })}  
        
      </Form.Group>

      <Form.Group className="mb-5">
        <Form.Label>Categories</Form.Label>
        
        <Form.Check
          checked={category ===""}
          label="All"
          name="property-by-type"
          type="radio"
          id="Alli"
          onChange={()=> setCategory("")}
        />
        {categories?.map((catg)=>{
          return (<Form.Check
            key={catg.title}
            checked={category ===catg.id}
            label={catg.title}
            name="property-by-type"
            type="radio"
            id={catg.title}
            onChange={()=> setCategory(catg.id)}
          />)
        })}      
      </Form.Group>

      <Form.Group className="mb-5">
        <Form.Label>Price Range</Form.Label>
        <Row>
          <Col>
            <Form.Control className="property-min-input" placeholder="Min" onChange={(e)=>setPriceMin(e.target.value)} />
          </Col>
          <Col>
            <Form.Control className="property-max-input" placeholder="Max" onChange={(e)=>setPriceMax(e.target.value)}/>
          </Col>
        </Row>
      </Form.Group>

      <Form.Group className="mb-2">
        <Form.Label>Country</Form.Label>
        <Form.Select className="property-min-input" value={country}  onChange={(e)=> setCountry(e.target.value)}>
          <option key='0' value="" >All</option>
          {countries?.map((cntr)=>{
            return (<option key={cntr.id} value={cntr.id} >{cntr.name}</option>)
          })} 
        </Form.Select>
      </Form.Group>
      <Form.Group className="mb-2">
        <Form.Label>Cities</Form.Label>
        <Form.Select className="property-min-input" value={city}  onChange={(e)=> setCity(e.target.value)}>
          <option key='0' value="" >All</option>
          {cities?.map((cntr)=>{
            return (<option key={cntr.id} value={cntr.id} >{cntr.name}</option>)
          })} 
        </Form.Select>
      </Form.Group>
      <Form.Group className="mb-5">
        <Form.Label>Districts</Form.Label>
        <Form.Select className="property-min-input" value={district}  onChange={(e)=> setDistrict(e.target.value)}>
          <option key='0' value="" >All</option>
          {districts?.map((cntr)=>{
            return (<option key={cntr.id} value={cntr.id} >{cntr.name}</option>)
          })} 
        </Form.Select>
      </Form.Group>

      <Button className="property-search-button col-12" onClick={()=>pushFeatures()}>
        <span className="fs-5">
          <CiSearch />
        </span>{" "}
        Search
      </Button>
    </Form>
  );
};

export default PropertyForm;
