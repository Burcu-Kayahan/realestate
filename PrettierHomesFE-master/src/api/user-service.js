import axios from "axios";
import { getAuthHeader } from "./auth-header";
import { config } from "../helpers/config";

const BASE_URL = config.api.baseUrl;
export const login = async (payload) => {
  const res = await axios.post(`${BASE_URL}/auth/login`, payload);
  const data = res.data;
  return data;
};


export const register = async (payload) => {
  const res = await axios.post(`${BASE_URL}/auth/register`, payload);
  const data = res.data;
  return data;
};

export const updateProfile = async (payload) => {
  const res = await axios.put(`${BASE_URL}/user/auth`, payload, {
    headers: getAuthHeader(),
  });
  const data = await res.data;
  return data;
};
export const updateUser = async (payload) => {
  const res = await axios.put(`${BASE_URL}/user/${payload.id}/admin`, payload, {
    headers: getAuthHeader(),
  });
  const data = await res.data;
  return data;
};

export const deleteUser = async (id) => {
  const res = await axios.delete(`${BASE_URL}/user/${id}/admin`,  {
    headers: getAuthHeader(),
  });
  const data = await res.data;
  return data;
};

export const getMyProfile = async () => {
  const res = await axios.get(`${BASE_URL}/user/auth`, {
    headers: getAuthHeader(),
  });
  const data = res.data;
  return data;
};

export const forgetPassword = async (payload) => {
  let email= payload.email;
  const res = await axios.post(`${BASE_URL}/auth/forgot-password`, email);

  return null;
};

export const resetPassword = async (payload) => {
  const res = await axios.post(`${BASE_URL}/auth/reset-password`, payload);
  const data = res.data;
  return data;
};

export const registerConfirm = async (payload) => {
  const res = await axios.post(`${BASE_URL}/auth/registerconfirm/${payload}`);
  const data = res.data;
  return data;
};
export const getUserInfos = async (payload) => {
  const res = await axios.get(`${BASE_URL}/user/${payload}/admin`, {
    headers: getAuthHeader(),
  });
  const data = res.data;
  return data;
};
export const getUsers = async (page, size, sort, type, q) => {
   const res = await axios.get(
    `${BASE_URL}/user/admin?page=${page}&size=${size}&sort=${sort}&type=${type}&q=${q}`,
    { headers: getAuthHeader() }
  );
  const data = res.data;

  return data;
};

export const changePassword = async (payload) => {
    console.log(getAuthHeader())
    const res = await axios.patch(
        `${BASE_URL}/user/auth`, payload,
        {
            headers: getAuthHeader(),
        });
    const data = res.data;
    return data;
};

