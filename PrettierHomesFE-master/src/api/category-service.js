import axios from "axios";
import { getAuthHeader } from "./auth-header";
import { config } from "../helpers/config";

const BASE_URL = config.api.baseUrl;

export const getAllCategoryForAdmin = async(page,size,sort,type,q)=>{
 const res = await axios.get(
   `${BASE_URL}/categories/admin?page=${page}&size=${size}&sort=${sort}&type=${type}&q=${q}`,
   { headers: getAuthHeader() }
 );
 const data = await res.data;
 return data;
}

export const createCategory = async (payload)=>{
const res = await axios.post(
  `${BASE_URL}/categories`,payload,
  { headers: getAuthHeader() }
);
const data = await res.data;
return data;
}


export const updateAdminCategory = async (id, payload) => {
  const res = await axios.put(`${BASE_URL}/admin-category/${id}`, payload, {
    headers: getAuthHeader(),
  });
  const data = res.data;
  return data;
};

export const getCategoryById = async (id) => { 
  const resp = await axios.get(`${BASE_URL}/categories/getSavedCategoryById/${id}`, {
    headers: getAuthHeader(),
  });
  const data = await resp.data;
  return data;
 }

export const deleteAdminCategory = async (id) => {
  const res = await axios.delete(`${BASE_URL}/admin-category/${id}`, {
    headers: getAuthHeader(),
  });
  const data = res.data;
  return data;
};

export const getAllCategory = async () => {
  const res = await axios.get(
    `${BASE_URL}/categories?page=0&size=100&sort=title&type=DESC&q=`,
    {
      headers: getAuthHeader(),
    }
  );
  const data = await res.data;
  return data;
};
export const getAllCatgKeys = async (props) => {
  const res = await axios.get(
    `${BASE_URL}/propertieskey/getAllByCatgory/${props}`,
    {
      headers: getAuthHeader(),
    }
  );
  const data = await res.data;
  return data;
};

// TODO UPDATE VE DELETE FONKSIYONLARI YAZI;CAK VE GETbYiDcATEGORY FONKSIYONU YAZILACAK

