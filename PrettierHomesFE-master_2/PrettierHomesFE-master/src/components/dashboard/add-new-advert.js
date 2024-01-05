import { useFormik } from "formik";
import React, { useEffect, useRef, useState } from "react";
import {
  Button,
  ButtonGroup,
  Card,
  Col,
  Container,
  FloatingLabel,
  Form,
  Image,
  Row,
} from "react-bootstrap";
import * as Yup from "yup";
import { isInValid, isValid } from "../../helpers/functions/forms";
import { useDispatch } from "react-redux";
import {
  setListRefreshToken,
  setOperation,
} from "../../store/slices/misc-slice";
import { swalAlert } from "../../helpers/functions/swal";
import ButtonLoader from "../common/button-loader";
import { FaPlusCircle, FaPlus, FaAccusoft } from "react-icons/fa";

import "./add-new-advert.scss";
import {
  createNewAdvert,
  getCities,
  getCountries,
  getDistricts,
} from "../../api/adverts-service";
import { useNavigate } from "react-router-dom";
import { getAllCategory, getAllCatgKeys } from "../../api/category-service";
import {
  getAdvertList,
  getAdvertTypeList,
} from "../../api/advert-type-service";
import { createImages } from "../../api/imagesService";

const NewAdvertForm = () => {
  const dispatch = useDispatch();
  const [loading, setLoading] = useState(false);
  const [selectedFiles, setSelectedFiles] = useState([]);
  const [selectedImages, setSelectedImages] = useState([]);
  const navigate = useNavigate();
  const isUserLoginLocal = localStorage.getItem("isUserLogin") === "true";
  const userLocal = JSON.parse(localStorage.getItem("user" || false));
  const [advertType, setAdvertType] = useState([]);
  const [categories, setCategories] = useState([]);
  const [propertyKeys, setPropertyKeys] = useState([]);
  const [countries, setCountries] = useState([]);
  const [cities, setCities] = useState([]);
  const [districts, setDistricts] = useState([]);


  
  useEffect(() => {
    if (!isUserLoginLocal) {
      navigate("/login");
    }
    if (isUserLoginLocal) {
      loadCategories();
      loadAdvertType();
      loadCountries();
    }
  }, [isUserLoginLocal, navigate]);

  const loadCategories = async () => {
    try {
      const resp = await getAllCategory();
      setCategories(resp.content);
    } catch (err) {
      alert(err);
    }
  };
  const loadAdvertType = async () => {
    try {
      const resp = await getAdvertTypeList();
      setAdvertType(resp);
    } catch (err) {
      alert(err);
    }
  };
  const loadCatKeys = async () => {
    try {
      const resp = await getAllCatgKeys(formik.values.category);
      setPropertyKeys(resp);    
    } catch (err) {
      alert(err);
    }
  };
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
      const resp = await getCities(formik.values.country);
      setCities(resp);
    } catch (err) {
      alert(err);
    }
  };
  const loadDistricts = async () => {
    try {
      const resp = await getDistricts(formik.values.city);
      setDistricts(resp);
    } catch (err) {
      alert(err);
    }
  };


  const initialValues = {
    title: "",
    description: "",
    price: 0,
    advertType: 0,
    category: 0,
    country: 0,
    city: 0,
    district: 0,
    isActive:false,
    viewCount:0,
    location: "",
    values:[]
  };

  const validationSchema = Yup.object({
    title: Yup.string().required("Required"),
    description: Yup.string().required("Required"),
    price: Yup.number().required("Required"),
    advertType: Yup.number().required("Required").min(1, "Required"),
    category: Yup.number().required("Required").min(1, "Required"),
    country: Yup.number().required("Required").min(1, "Required"),
    city: Yup.number().required("Required").min(1, "Required"),
    district: Yup.number().required("Required").min(1, "Required"),
    
  });

  const onSubmit = async (values) => {
    setLoading(true);

    try {
  const resp=    await createNewAdvert(values);
      formik.resetForm();
      dispatch(setOperation(null));
      // dispatch(setListRefreshToken(Math.random()));
      console.log(resp)
      // await uploadImages(resp.id);
      const formData = new FormData();

      // En son dosyayı seçili dosyalar arasından alın
      // const lastFile = selectedFiles[selectedFiles.length - 1];
      
      // Dosyayı FormData'ya ekleyin
      // formData.append("lastImage", lastFile);
      
      // Diğer özellikleri bir JSON nesnesine ekleyin
      const imageInfo = [{             
        name: "img",
        featured: false,
        type: "jpg",
        url:"https://images.pexels.com/photos/101808/pexels-photo-101808.jpeg?auto=compress&cs=tinysrgb&w=600"
      },
      {             
        name: "img",
        featured: false,
        type: "jpg",
        url:"https://images.pexels.com/photos/259588/pexels-photo-259588.jpeg?auto=compress&cs=tinysrgb&w=600"
      },{             
        name: "img",
        featured: false,
        type: "jpg",
        url:"https://images.pexels.com/photos/209296/pexels-photo-209296.jpeg?auto=compress&cs=tinysrgb&w=600"
      }];
      
      // JSON nesnesini FormData'ya ekleyin
      // formData.append("imageInfo", JSON.stringify(imageInfo));
        await createImages(imageInfo,resp.id); 
      swalAlert("Your new advert was created successfully", "success");
    } catch (err) {     
      swalAlert(err, "error");
    } finally {
      setLoading(false);
    }
  };

  const uploadImages = async (id) => {
    try {
      const formData = new FormData();
  console.log(id)
      // Seçilen her bir dosyayı FormData'ya ekleyin
      selectedFiles.forEach((file, index) => {
        formData.push(file);
        // formData.append(`image_${index + 1}`, file);
        
      });
  
      const dto =[{
        imgData:formData,
        name:"img",
        featured:false,
        type:"jpg"

      }]
      // FormData içeren POST isteğini gönderin
        const response = await createImages(dto,id); 
    
        swalAlert('Resimler başarıyla yüklendi',"success");
     
    } catch (error) {
      swalAlert(error, "error");
    }
  };
  

  const formik = useFormik({
    initialValues,
    validationSchema,
    onSubmit,
  });

  useEffect(() => {
    if (formik.values.country > 0) {
      loadCities();
    }
  }, [formik.values.country]);
  useEffect(() => {
    if (formik.values.city > 0) {
      loadDistricts();
    }
  }, [formik.values.city]);

  useEffect(() => {
    if (formik.values.category >0){
       loadCatKeys();}
  }, [formik.values.category]);

  const fileInputRef = useRef(null);

  const handleFileChange = (event) => {
    const files = Array.from(event.target.files);

    setSelectedFiles((prevFiles) => [...prevFiles, ...files]);
  };
  const handleImageSelect = (index) => {
    // Check if the image is already selected
    const isSelected = selectedImages.includes(index);

    if (isSelected) {
      // If already selected, remove from the list
      setSelectedImages((prev) => prev.filter((item) => item !== index));
    } else {
      // If not selected, add to the list
      setSelectedImages((prev) => [...prev, index]);
    }
  };
  const handleDeleteImages = () => {
    // Filter out the selected files from the state
    const remainingFiles = selectedFiles.filter((file, index) => {
      return !selectedImages.includes(index);
    });

    // Update the state with the remaining files
    setSelectedFiles(remainingFiles);
  };
  const handleSetAsFeatured = () => {
    if (selectedImages.length > 0) {
      const featuredImageIndex = selectedImages[0];
      const updatedFiles = selectedFiles.map((file, index) => {
        return {
          ...file,
          isFeatured: index === featuredImageIndex,
        };
      });

      // You might want to update your state with the new "isFeatured" property
      setSelectedFiles(updatedFiles);

      // Placeholder logic for making an API call to mark the image as featured
      // replace this with your actual API call if needed
      // Example:
      // api.markImageAsFeatured(selectedFiles[featuredImageIndex].id)
      console.log(`Set image ${featuredImageIndex} as featured`);
    } else {
      // No image selected, show an alert or handle accordingly
      console.log("No image selected to set as featured");
      // Add your logic for handling when no image is selected
    }
  };

  const handleValuesAdd = (event, keys) => {

  const trimmedValue = event.target.value.trim();
  const value = {
    value: event.target.value.trim(),
    advert: null,
    keyId: keys.id,
  };

  const existingValueIndex = formik.values.values.findIndex(
    (item) => item.keyId === keys.id
  );

  if (existingValueIndex !== -1) {
    if (trimmedValue){
       formik.values.values[existingValueIndex] = value;
    }else {
      formik.values.values.splice(existingValueIndex, 1);
    }
  } else {
    if(trimmedValue){
    formik.values.values.push(value);
    }    
  }
  console.log(formik.values.values);
};

    
  

  return (
    <Container>
      <Form
        noValidate
        onSubmit={formik.handleSubmit}
        className="add-new-advert"
      >
        <div>
          <h3>Common Information</h3>
          <Row className="px-5 ">
            <Col>
              <Form.Group
                className="mb-3"
                controlId="exampleForm.ControlInput1"
              >
                <Form.Label>Title</Form.Label>
                <Form.Control
                  type="text"
                  {...formik.getFieldProps("title")}
                  isValid={isValid(formik, "title")}
                  isInvalid={isInValid(formik, "title")}
                />
                <Form.Control.Feedback type="invalid">
                  {formik.errors.title}
                </Form.Control.Feedback>
                <Form.Label>Description</Form.Label>
                <Form.Control
                  as="textarea"
                  {...formik.getFieldProps("description")}
                  isValid={isValid(formik, "description")}
                  isInvalid={isInValid(formik, "description")}
                />
                <Form.Control.Feedback type="invalid">
                  {formik.errors.description}
                </Form.Control.Feedback>
              </Form.Group>
            </Col>
          </Row>
          <Row className="px-5">
            <Col lg={4}>
              {" "}
              <Form.Label>Price</Form.Label>
              <Form.Control
                type="text"
                {...formik.getFieldProps("price")}
                isValid={isValid(formik, "price")}
                isInvalid={isInValid(formik, "price")}
              />
              <Form.Control.Feedback type="invalid">
                {formik.errors.price}
              </Form.Control.Feedback>
            </Col>

            <Col lg={4}>
              <Form.Label>Advert Type</Form.Label>
              <Form.Select
                lg={4}
                aria-label="Default select example"
                {...formik.getFieldProps("advertType")}
                isValid={isValid(formik, "advertType")}
                isInvalid={isInValid(formik, "advertType")}
              >
                <option key={0} value={0}>
                  Choose{" "}
                </option>
                {advertType?.map((type) => {
                  return (
                    <option key={type.id} value={type.id}>
                      {type.title}
                    </option>
                  );
                })}
              </Form.Select>
              <Form.Control.Feedback type="invalid">
                {formik.errors.advertType}
              </Form.Control.Feedback>
            </Col>
            <Col lg={4}>
              <Form.Label>Category</Form.Label>
              <Form.Select
                aria-label="Default select example"
                {...formik.getFieldProps("category")}
                isValid={isValid(formik, "category")}
                isInvalid={isInValid(formik, "category")}
              >
                <option key={0} value={0}>
                  Choose
                </option>
                {categories?.map((ctg) => {
                  return (
                    <option key={ctg.id} value={ctg.id}>
                      {ctg.title}
                    </option>
                  );
                })}
              </Form.Select>
              <Form.Control.Feedback type="invalid">
                {formik.errors.category}
              </Form.Control.Feedback>
            </Col>
          </Row>
        </div>
        <div>
          <h3>Address Information</h3>
          <Row className="px-5">
            <Col lg={4}>
              {" "}
              <Form.Label>Country</Form.Label>
              <Form.Select
                lg={4}
                aria-label="Default select example"
                {...formik.getFieldProps("country")}
                isValid={isValid(formik, "country")}
                isInvalid={isInValid(formik, "country")}
              >
                <option key={0} value={0}>
                  Choose{" "}
                </option>
                {countries?.map((country) => {
                  return (
                    <option key={country.id} value={country.id}>
                      {country.name}
                    </option>
                  );
                })}
              </Form.Select>
              <Form.Control.Feedback type="invalid">
                {formik.errors.country}
              </Form.Control.Feedback>
            </Col>

            <Col lg={4}>
              <Form.Label>City </Form.Label>
              <Form.Select
                lg={4}
                // aria-label="Default select example"
                {...formik.getFieldProps("city")}
                isValid={isValid(formik, "city")}
                isInvalid={isInValid(formik, "city")}
              >
                <option key={0} value={0}>
                  Choose{" "}
                </option>
                {cities?.map((cit) => {
                  return (
                    <option key={cit.id} value={cit.id}>
                      {cit.name}
                    </option>
                  );
                })}
              </Form.Select>
              <Form.Control.Feedback type="invalid">
                {formik.errors.city}
              </Form.Control.Feedback>
            </Col>
            <Col lg={4}>
              <Form.Label>District</Form.Label>
              <Form.Select
                aria-label="Default select example"
                {...formik.getFieldProps("district")}
                isValid={isValid(formik, "district")}
                isInvalid={isInValid(formik, "district")}
              >
                <option value={0} key={0}>
                  Choose
                </option>
                {districts?.map((disctr) => {
                  return (
                    <option key={disctr.id} value={disctr.id}>
                      {disctr.name}
                    </option>
                  );
                })}
              </Form.Select>
              <Form.Control.Feedback type="invalid">
                {formik.errors.district}
              </Form.Control.Feedback>
            </Col>
          </Row>
          <Col className="px-5">
            <Form.Label>Location</Form.Label>
            <Form.Control type="text" {...formik.getFieldProps("location")} />
          </Col>
        </div>
        <div>
          <h3>Property Details</h3>
          <Row className="px-5">
            {propertyKeys?.map((keys) => {
              return (
                <Col key={keys.id} lg={4}>
                  <Form.Label>{keys.name}</Form.Label>
                  <Form.Control type="text"
                  onChange={(e)=>handleValuesAdd(e, keys)}
                  />
                </Col>
              );
            })}
           
          </Row>
        </div>

        <div className="images">
          <h3>Images</h3>
          <Card className="bg-secondary">
            <Card.Body>
              <div>
                <input
                  type="file"
                  ref={fileInputRef}
                  style={{ display: "none" }}
                  onChange={handleFileChange}
                  multiple
                />
                <span type="file" onClick={() => fileInputRef.current.click()}>
                  <FaPlus />
                </span>
              </div>

              <Row className="p-5">
                {selectedFiles.map((file, index) => (
                  <Col key={index} xs={6} md={3}>
                    <div style={{ position: "relative" }}>
                      <Image
                        src={file ? URL.createObjectURL(file) : ""}
                        rounded
                        alt={`Uploaded Image ${index}`}
                        className="img-fluid mb-5"
                      />

                      <Form.Check
                        type="checkbox"
                        className="position-absolute top-0 "
                        checked={selectedImages.includes(index)}
                        onChange={() => handleImageSelect(index)}
                      />
                    </div>
                  </Col>
                ))}
              </Row>
              <Row>
                <Col className="d-flex justify-content-end m-5 gap-3">
                  <Button
                    variant="info"
                    className="px-5"
                    onClick={handleSetAsFeatured}
                  >
                    SET AS FEATURED
                  </Button>
                  <Button
                    variant="dark"
                    className="px-5"
                    onClick={handleDeleteImages}
                  >
                    DELETE
                  </Button>
                </Col>
              </Row>
            </Card.Body>
          </Card>
        </div>
        <div className="d-flex justify-content-end m-5 p-5">
          <Button disabled={loading} variant="success" type="submit" className="px-5 fs-2">
            Create
          </Button>
        </div>
      </Form>
    </Container>
  );
};
export default NewAdvertForm;
