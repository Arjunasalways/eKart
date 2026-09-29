import axios from "axios";
import { getAccessToken } from "./tokenService";

const API_URL1='http://localhost:8080/users/cart'

export const cartService = async() => {

  const response = await axios.get(`${API_URL1}`,{
    headers:{
      Authorization: `Bearer ${getAccessToken()}`
    }
  })
  return response.data;
}
