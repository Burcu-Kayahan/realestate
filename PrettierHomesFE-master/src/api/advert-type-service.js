import axios from "axios";
import { getAuthHeader } from "./auth-header";
import { config } from "../helpers/config";

const BASE_URL= config.api.baseUrl;

export const getAdvertTypeList = async ()=>{
  const res = await axios.get(`${BASE_URL}/advert-types`,{
    headers: getAuthHeader(),
  });
  const data = res.data;
  return data;
}

export const getByIdAdvertType = async (id) => {
  const res = await axios.get(`${BASE_URL}/advert-types/${id}`,{
    headers: getAuthHeader(),
  });
  const data = res.data;
  return data;
};
export const createAdvertType = async (payload) => {
  const res = await axios.post(`${BASE_URL}/advert-types`, payload, {
    headers: getAuthHeader(),
  });
  const data = res.data;
  return data;
};

export const updateAdvertType = async (id, payload) => {
  const res = await axios.put(`${BASE_URL}/advert-types/${id}`, payload, {
    headers: getAuthHeader(),
  });
  const data = res.data;
  return data;
};
export const deleteAdvertType = async (id) => {
  const res = await axios.delete(`${BASE_URL}/advert-types/${id}`, {
    headers: getAuthHeader(),
  });
  const data = res.data;
  return data;
};