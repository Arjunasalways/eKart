import React from 'react'
import Navbar from '../components/Navbar'
import './Home.css'
import { Link } from 'react-router-dom'
import Categories from '../components/Categories'
import FeaturedProducts from '../components/FeaturedProducts'
import { useNavigate } from 'react-router-dom'

export const Home = () => {

  const navigate=useNavigate()

  return (
    <div>
      <section className="hero">

        <div className="hero-content">

          <p className="hero-tag">
             EVERYDAY SHOPPING DESTINATION
          </p>

          <h1>
            Shop Smarter.
            <br />
            Live Better.
          </h1>

          <p className="hero-description">
            Discover quality products at great prices,
            all in one place.
          </p>

          <button className="hero-button" onClick={()=>navigate('/products')}>
            Shop
          </button>

        </div>

      </section>
      <Categories limit = {6}/>

      <FeaturedProducts/>

    </div>
  )
}// test
