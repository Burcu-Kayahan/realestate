import axios from "axios";
import { getAuthHeader } from "./auth-header";
import { config } from "../helpers/config";


const BASE_URL = config.api.baseUrl;


export const createNewAdvert = async (payload) => {
  const res = await axios.post(`${BASE_URL}/adverts`, payload, {
    headers: getAuthHeader(),
  });
  const data = res.data;
  return data;
};

export const getCountries = async () => {
  const res = await axios.get(`${BASE_URL}/countries`, {
    headers: getAuthHeader(),
  });
  const data = res.data;
  return data;
};

export const getCities = async (props) => {
  const res = await axios.get(`${BASE_URL}/cities/${props}`, {
    headers: getAuthHeader(),
  });
  const data = res.data;
  return data;
};
export const getDistricts = async (props) => {
  const res = await axios.get(`${BASE_URL}/districts/${props}`, {
    headers: getAuthHeader(),
  });
  const data = res.data;
  return data;
};

//A01
export const getAllAdvertsForUsers= async(page, size, sort, type, q,category_id,advert_type_id,price_start,price_end,status,country,city,district)=>{
  const res= await axios.get(`${BASE_URL}/adverts?page=${page}&size=${size}&sort=${sort}&type=${type}&q=${q}&categoryid=${category_id}&advert_type_id=${advert_type_id}&price_start=${price_start}&price_end=${price_end}&status=${status}&country=${country}&city=${city}&district=${district}`);
  const data= res.data;
  return data;
}

export const getPopularAdvertList = async (props) => {
  const res = await axios.get(`${BASE_URL}/adverts/popular/${props}`, {
    headers: getAuthHeader(),
  });
  const data = res.data;
  return data;
};


export const getMyAdverts = async (page, size, sort, search) => {
  const res = await axios.get(
    `${BASE_URL}/adverts/auth?page=${page}&size=${size}&sort=${sort}&q=${search}`,
    {
      headers: getAuthHeader(),
    }
  );
  const data = res.data;
  return data;
};

export const getAdvertsByUser = async (page, size, id) => {
  const res = await axios.get(`${BASE_URL}/adverts/userAdverts/${id}?page=${page}&size=${size}`, {
    headers: getAuthHeader(),
  });
  const data = res.data;
  return data;
};

export const getAdvertDetailBySlug= async(slug)=>{
  const res= await axios.get(`${BASE_URL}/adverts/${slug}`);
  const data= res.data;
  return data;
}


export const getFilterByAdmin=async(page,size,sort,q,category_id,advert_type_id,status)=>{
  const res= await axios.get(`${BASE_URL}/adverts?page=${page}&size=${size}&sort=${sort}&q=${q}&categoryid=${category_id}&advert_type_id=${advert_type_id}&status=${status}`);
  const data= res.data;
  return data;
}

