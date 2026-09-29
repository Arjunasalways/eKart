import React, { useState,useEffect } from 'react'
import './Categories.css'
import { getCategories } from '../services/categoryService';
import { Link, useNavigate } from 'react-router-dom';

const Categories = ({limit}) => {

  const [categories,setCategories] = useState([]);
  const [loading,setLoading] = useState(true);
  const [error,setError]     = useState(null);
  const navigate = useNavigate();

  useEffect(()=>{
    const loadCategories = async () => {
    
                try {
    
                    const data = await getCategories(0, limit)
    
                    console.log("CATEGORY API RESPONSE:", data)
    
                    setCategories(data.content)
    
                } catch (error) {
    
                    console.error("FAILED TO LOAD CATEGORIES:", error)
    
                    setError("Unable to load categories.")
    
                } finally {
    
                    setLoading(false)
                }
            }
    
            loadCategories()
  },[])

  if (loading) {
        return (
            <section className="categories">
                <p>Loading categories...</p>
            </section>
        )
    }

    if (error) {
        return (
            <section className="categories">
                <p>{error}</p>
            </section>
        )
    }
  return (
    <section className="categories">

        <div className="categories-header">
            <p className="section-tag">
                SHOP BY CATEGORY
            </p>

            <h2>
                Explore Our Categories
            </h2>

            <p className="section-description">
                Find everything you need, all in one place.
            </p>

        <Link to="/categories"className='view-all'>
        View All →</Link>
        
        </div>


        <div className="categories-grid">
            

        {categories.map((category) => (
          <span onClick={() => navigate(`/products/category/${category.id}`)}
            className="category-card"
            key={category.id}
          >
            <h3>{category.name}</h3>

            <span >
                Explore →
            </span>
          </span>
        ))}

      </div>

    </section>
  )
}

export default Categories