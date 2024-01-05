import axios from 'axios';
import { getAuthHeader } from './auth-header';
import { config } from '../helpers/config';

const BASE_URL = config.api.baseUrl;

export const getTourRequests = async (page = "0", size = "20", sort = "id", type = "ASC", q ="true") => {
  const url = `${BASE_URL}/tourRequest?page=${page}&size=${size}&sort=${sort}&type=${type}&q=${q}`;
  const headers = getAuthHeader(); 

  try {
    const res = await axios.get(url, {
      headers: headers
    });
    console.log(res.data);
  } catch (error) {
    console.log(error);
  }
};

export const createTourRequest = async (payload) => {
  const res = await axios.post(`${BASE_URL}/tourRequest`, payload, {
    headers: getAuthHeader(),
  });
  const data = await res.data;
  return data;
};

export const deleteTourRequest = async (id) => {
  const res = await axios.delete(`${BASE_URL}/tourRequest/${id}`, {
    headers: getAuthHeader(),
  });
  const data = await res.data;
  return data;
};

export const updateTourRequest = async (id, payload) => {
  const res = await axios.put(`${BASE_URL}/tourRequest/${id}`, payload, {
    headers: getAuthHeader(),
  });
  const data = await res.data;
  return data;
};