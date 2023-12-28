import axios from "axios";
import { getAuthHeader } from "./auth-header";
import { config } from "../helpers/config";

const BASE_URL = config.api.baseUrl;

export const createImages = async(payload, id)=>{
    const res = await axios.post(`${BASE_URL}/images/${id}`, payload,{
        headers: getAuthHeader(),
      });
      const data = res.data;
      return data;
}