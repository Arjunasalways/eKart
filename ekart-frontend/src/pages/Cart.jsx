import React, { useEffect, useState } from 'react'
import { cartService } from '../services/cartService'

export const Cart = () => {

    const [cartItem,setCartitem] = useState([])
    const [loading,setLoading]   = useState(false)
    const [error,setError]       = useState([])

    useEffect(()=>{

        const getCartItems =async()=>{

            const data = await cartService();
            setCartitem(data.content)
        } 

    getCartItems()

    },[])


  return (
    <div>
        {
            cartItem.map((item)=>{item})
        }
    </div>
  )
}
