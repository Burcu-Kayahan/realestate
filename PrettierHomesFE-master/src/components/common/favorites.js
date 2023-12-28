import { addOrRemoveFavorite, getListForUserForCart, isFavoryService } from "../../api/favorites-service";


const isUserLogin = JSON.parse(localStorage.getItem("isUserLogin") || "false");
const favorites = JSON.parse(localStorage.getItem("favorites")) || [];




export const getFavorites =async (userId)=>{ 
    if(isUserLogin){ 
         try{
   const resp = await getListForUserForCart();
     return resp;
    }catch(err){
    return [];
    }
}else {
return [];
//todo user login degilse local de favory olup olmadigi kontrol edilecek
}
}

export const createOrDelete = async (id)=>{ 
  if(isUserLogin ){
    try{
      const resp = await addOrRemoveFavorite(id);
      return resp;
    }catch(err){
      return false;
    }
  }else{
    return false;
    //todo user login degilse local de favory olup olmadigi kontrol edilecek
  }
}

export const isFavory = async (id)=>{
  if(isUserLogin ){
    try{
      const resp = await isFavoryService(id);
      return resp;
    }catch(err){
      return false;
    }
  }else{
    return false;
    //todo user login degilse local de favory olup olmadigi kontrol edilecek
  }
}