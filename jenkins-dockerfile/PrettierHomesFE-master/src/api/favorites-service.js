import axios from "axios";
import { getAuthHeader } from "./auth-header";
import { config } from "../helpers/config";

const BASE_URL = config.api.baseUrl;


export const getFovoritesByUserForAdmin = async(page, size,id)=>{
    const res = await axios.get(
        `${BASE_URL}/favorites/admin/${id}?page=${page}&size=${size}&sort=&direction=`,
        { headers: getAuthHeader() }
      );
      const data = res.data;
    
      return data;
}

