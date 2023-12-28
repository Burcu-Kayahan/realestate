import React, { useEffect, useState } from "react";
import { Button, ButtonGroup, Form, InputGroup, ToggleButton } from "react-bootstrap";
import "./property-search.scss";
import { getAdvertTypeList } from "../../../api/advert-type-service";
import { getAllCategory } from "../../../api/category-service";
import { getAllAdvertsForUsers } from "../../../api/adverts-service";
import { useNavigate } from "react-router-dom";
const PropertySearch = () => {
  const [aTypes, setATypes]= useState([]);
  const [atype, setAType]= useState("");
  const [categories, setCategories]= useState([]);
  const [category, setCategory]= useState("");
  const [search, setSearch]=useState("");
 const [properties, setProperties]= useState([]);
 const navigate = useNavigate();

 const handleSearch =()=>{
  let categoryId = category;
  navigate(`/properties/${atype}/${categoryId}/${search}`, { state: { atype, categoryId, search } });

};

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
},[]);

useEffect(()=>{
  let searchTrim =search.trim();
  if((searchTrim !=="")){
    fetchProperties();
  }else {
    setProperties([])
  }
},[atype, category, search]);




const fetchProperties = async ()=>{ 
  try{
const resp = await getAllAdvertsForUsers(0, 20,"title","DESC",search,category,atype,"","","","","","");
setProperties(resp.content)
  }catch(err){
console.log(err)
  }
}

  return (
    <div className="property-search mt-2">
      <Form className="my">
        <h1 className="pb-4">
          Easy Way To Find a <br /> Perfect Property
        </h1>
        <ButtonGroup toggle>
          {aTypes?.map((type) =>{
           return (  <ToggleButton
             key={type.id}
             type="radio"
             variant="light"
             name="radio"
             value={type.id}
             checked={String(type.id) ===String(atype)}
             onClick={() => {
               setAType(type.id);
             }}
           >
            {type.title}
           </ToggleButton>)
          })}          
        </ButtonGroup>

        <InputGroup className="mb-3">
          <Form.Control aria-label="property" aria-describedby="property" placeholder="Find a Perfect Property . . ." onChange={(e)=>setSearch(e.target.value)}/>
          <Button type="submit" id="search" className="mx-2 bg-success" onClick={()=>handleSearch()}>
            Search
          </Button>         
        </InputGroup>       
        <div className="mb-3">
          {categories?.map((catg)=>{
            return (
            <Form.Check
            key={catg.id}
            inline
            label={catg.title}
            name="group1"
            type="radio"
            id={catg.id}
            onClick={()=>setCategory(catg.id)}
          />
          )})}
          
        </div>
        {properties?.map((property)=>(
          <div className="searchPopupDiv">
        <li className="searchPopupLi" key={property.id} onClick={() =>  navigate(`/property/${property.slug}`)}>
        {property.title}
      </li></div>
       )) }
      </Form>
    </div>
  );
};

export default PropertySearch;
