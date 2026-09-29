import axios from "axios";

import {
    getAccessToken,
    setAccessToken,
    clearAccessToken
} from "./tokenService";


const api = axios.create({
    baseURL: "http://localhost:8080",
    withCredentials: true
});


/*
================================
REQUEST INTERCEPTOR
================================
*/

api.interceptors.request.use(
    (config) => {

        const token = getAccessToken();

        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        }

        return config;
    },

    (error) => {
        return Promise.reject(error);
    }
);


/*
================================
RESPONSE INTERCEPTOR
================================
*/

api.interceptors.response.use(

    (response) => {
        return response;
    },

    async (error) => {

        const originalRequest = error.config;

        if (
            error.response?.status === 401 &&
            !originalRequest._retry
        ) {

            originalRequest._retry = true;

            try {

                const response =
                    await api.post("/auth/refresh");

                const newAccessToken =
                    response.data.accessToken;

                setAccessToken(newAccessToken);

                originalRequest.headers.Authorization =
                    `Bearer ${newAccessToken}`;

                return api(originalRequest);

            } catch (refreshError) {

                clearAccessToken();

                return Promise.reject(refreshError);
            }
        }

        return Promise.reject(error);
    }
);


export default api;