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
export const getListForUserForCart = async()=>{ 
    const res = await axios.get(
        `${BASE_URL}/favorites/forCart/auth`,
        { headers: getAuthHeader() }
      );
      const data = res.data;     
      return data;
}

export const addOrRemoveFavorite = async(id)=>{ 
  const dto ={
    advertId:id
  }
    const res = await axios.post(
        `${BASE_URL}/favorites`,dto,
        { headers: getAuthHeader() }
      );
      const data = res.data;      
      return data;
}
export const isFavoryService = async(id)=>{ 
    const res = await axios.get(
        `${BASE_URL}/favorites/isFavory/${id}`,
        { headers: getAuthHeader() }
      );
      const data = res.data;          
      return data;
}






//addOrRemoveFavorite