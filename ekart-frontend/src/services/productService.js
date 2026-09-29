import axios from 'axios'

const API_URL1 = 'http://localhost:8080/products'

export const getProducts = async (page = 0, size = 20) => {

    const response = await axios.get(API_URL1, {
        params: {
            page,
            size
        }
    })


    return response.data
}

const API_URL2 = 'http://localhost:8080/products/categories/'

export const getProductsByCategory = async (categoryId,page = 0, size = 20) => {

    const response = await axios.get(`${API_URL2}${categoryId}`, {
        params: {
            page,
            size
        }
    })


    return response.data
}


const API_URL3 = 'http://localhost:8080/products'

export const getProductDetails = async (productId) => {

    const response = await axios.get(
        `${API_URL3}/${productId}/productvariants`
    )

    return response.data
}



