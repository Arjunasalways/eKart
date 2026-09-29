import axios from "axios";

import React from 'react'

const API_URL = 'http://localhost:8080/categories'


export const getCategories = async (page=0, size=20) => {

  const response = await axios.get(API_URL, {
        params: {
            page,
            size
        }
    })


    return response.data
}
