import axios from "axios";

const API_URL1='http://localhost:8080/auth'
export const userService = async(name,email,mobile,password) => {

    const response = await axios.post(`${API_URL1}/register`,{
        email: email,
        password:password,
        name:name,
        mobile:mobile
    })
  
    return response.data
}
