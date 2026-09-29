import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import { getProducts } from '../services/productService'
import ProductCard from '../pages/ProductCard'

const FeaturedProducts = () => {

    const [products, setProducts] = useState([])
    const [loading, setLoading] = useState(true)
    const [error, setError] = useState(null)

    useEffect(() => {

        const loadProducts = async () => {

            try {

                const data = await getProducts(0, 4)

                setProducts(data.content)

            } catch (error) {

                console.error("FAILED TO LOAD PRODUCTS:", error)

                setError("Unable to load products.")

            } finally {

                setLoading(false)
            }
        }

        loadProducts()

    }, [])

    if (loading) {
        return (
            <section className="featured-products">
                <p>Loading products...</p>
            </section>
        )
    }

    if (error) {
        return (
            <section className="featured-products">
                <p>{error}</p>
            </section>
        )
    }

    return (
        <section className="featured-products">

            <div className="featured-header">

                <div>

                    <p className="section-tag">
                        TOP PICKS
                    </p>

                    <h2>
                        Featured Products
                    </h2>

                    <p className="section-description">
                        Discover some of our most popular products.
                    </p>

                </div>

                <Link
                    to="/products"
                    className="view-all"
                >
                    View All →
                </Link>

            </div>

            <div className="products-grid">

                {products.map((product) => (
                    <ProductCard
                        key={product.id}
                        product={product}
                    />
                ))}

            </div>

        </section>
    )
}

export default FeaturedProducts