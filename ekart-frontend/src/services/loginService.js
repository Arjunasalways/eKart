import api from "./api";
import { setAccessToken } from "./tokenService";

export const loginService = async (email, password) => {

    const response = await api.post("/auth/login", {
        email,
        password
    });

    const accessToken = response.data.accessToken;

    setAccessToken(accessToken);

    return response.data;
};