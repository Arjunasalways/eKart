import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import { getProductDetails } from '../services/productService'
import './ProductDetailsPage.css'

const ProductDetailsPage = () => {

    const { productId } = useParams()

    const [variants, setVariants] = useState([])
    const [selectedVariant, setSelectedVariant] = useState(null)
    const [selectedImage, setSelectedImage] = useState(null)

    const [loading, setLoading] = useState(true)
    const [error, setError] = useState(null)


    useEffect(() => {

        const loadProductDetails = async () => {

            try {

                const data = await getProductDetails(productId)

                console.log("PRODUCT DETAILS RESPONSE:", data)

                setVariants(data)

                if (data.length > 0) {

                    setSelectedVariant(data[0])

                    const primaryImage = data[0].images.find(
                        image => image.primaryImage
                    )

                    setSelectedImage(
                        primaryImage || data[0].images[0]
                    )
                }

            } catch (error) {

                console.error(
                    "FAILED TO LOAD PRODUCT DETAILS:",
                    error
                )

                setError("Unable to load product details.")

            } finally {

                setLoading(false)
            }
        }

        loadProductDetails()

    }, [productId])


    const handleVariantChange = (variant) => {

        setSelectedVariant(variant)

        const primaryImage = variant.images.find(
            image => image.primaryImage
        )

        setSelectedImage(
            primaryImage || variant.images[0]
        )
    }


    if (loading) {

        return (
            <section className="product-details-page">
                <p>Loading product details...</p>
            </section>
        )
    }


    if (error) {

        return (
            <section className="product-details-page">
                <p>{error}</p>
            </section>
        )
    }


    if (!selectedVariant) {

        return (
            <section className="product-details-page">
                <p>Product not found.</p>
            </section>
        )
    }


    return (

        <section className="product-details-page">

            <div className="product-details-container">

                {/* IMAGE SECTION */}

                <div className="product-gallery">

                    <div className="thumbnail-list">

                        {selectedVariant.images.map((image, index) => (

                            <button
                                key={index}
                                className={`thumbnail ${
                                    selectedImage?.imageUrl === image.imageUrl
                                        ? 'active'
                                        : ''
                                }`}
                                onClick={() => setSelectedImage(image)}
                            >

                                <img
                                    src={`http://localhost:8080${image.imageUrl}`}
                                    alt={`${selectedVariant.sku}-${index}`}
                                />

                            </button>

                        ))}

                    </div>


                    <div className="main-image">

                        <img
                            src={`http://localhost:8080${selectedImage?.imageUrl}`}
                            alt={selectedVariant.sku}
                        />

                    </div>

                </div>


                {/* PRODUCT INFORMATION */}

                <div className="product-info">

                    <p className="section-tag">
                        PRODUCT DETAILS
                    </p>


                    <h1>
                        {selectedVariant.sku}
                    </h1>


                    <div className="price">

                        ₹{selectedVariant.price.toLocaleString('en-IN')}

                    </div>


                    <div className="stock">

                        {selectedVariant.stockQuantity > 0
                            ? `${selectedVariant.stockQuantity} in stock`
                            : 'Out of stock'
                        }

                    </div>


                    {/* ATTRIBUTES */}

                    <div className="attributes">

                        {selectedVariant.attributes.map(
                            (attribute, index) => (

                                <div
                                    className="attribute-row"
                                    key={index}
                                >

                                    <span className="attribute-name">
                                        {attribute.attributeName}
                                    </span>

                                    <span className="attribute-value">
                                        {attribute.attributeValue}
                                    </span>

                                </div>

                            )
                        )}

                    </div>


                    {/* VARIANT SELECTION */}

                    <div className="variant-selection">

                        <h3>
                            Available Variants
                        </h3>


                        <div className="variant-buttons">

                            {variants.map((variant) => (

                                <button
                                    key={variant.id}
                                    className={`variant-button ${
                                        selectedVariant.id === variant.id
                                            ? 'selected'
                                            : ''
                                    }`}
                                    onClick={() =>
                                        handleVariantChange(variant)
                                    }
                                >

                                    {variant.attributes.map(
                                        attribute =>
                                            attribute.attributeValue
                                    ).join(' / ')}

                                </button>

                            ))}

                        </div>

                    </div>

                    {/* ADD TO CART */}
                    <div className='button-container'>
                            <button
                            className="add-cart-btn"
                            disabled={
                                !selectedVariant.active ||
                                selectedVariant.stockQuantity === 0
                            }
                        >

                            Add to Cart

                        </button>

                        <button
                        className="buy-now-btn"
                        disabled={
                            !selectedVariant.active ||
                            selectedVariant.stockQuantity === 0
                        }
                        >
                            BuyNow

                        </button>

                        <button
                            className="wishlist-btn"
                            title="Add to Wishlist"
                        >
                            <svg
                                viewBox="0 0 24 24"
                                className="wishlist-icon"
                            >
                                <path
                                    d="M20.84 4.61a5.5 5.5 0 0 0-7.78 0L12 5.67l-1.06-1.06a5.5 5.5 0 0 0-7.78 7.78L12 21.23l8.84-8.84a5.5 5.5 0 0 0 0-7.78z"
                                />
                            </svg>
                        </button>

                    </div>
                
                </div>
                    

                </div>


        </section>
    )
}

export default ProductDetailsPage