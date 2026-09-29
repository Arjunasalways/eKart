import React from 'react'
import { Link } from 'react-router-dom'
import './ProductCard.css'

const ProductCard = ({ product }) => {

    return (
        <Link to={`/products/${product.id}`} className="product-card">

            <div className="product-image">

                <img
                    src={`http://localhost:8080${product.defaultVariant?.thumbnail}`}
                    alt={product.name}
                />

            </div>

            <div className="product-info">

                <p className="product-brand">
                    {product.brand}
                </p>

                <h3>
                    {product.name}
                </h3>

                <p className="product-description">
                    {product.description}
                </p>

                <div className="product-bottom">

                    <span className="product-category">
                        {product.category?.name}
                    </span>

                </div>

            </div>

        </Link>
    )
}

export default ProductCard