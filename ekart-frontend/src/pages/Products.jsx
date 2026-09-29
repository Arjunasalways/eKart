import { useParams } from "react-router-dom"
import './ProductCard.css'
import ProductCard from "./ProductCard"
import { useEffect, useState } from "react"
import {
    getProductsByCategory,
    getProducts
} from "../services/productService"

const Products = () => {

    const { categoryId } = useParams()

    const [products, setProducts] = useState([])
    const [loading, setLoading] = useState(true)
    const [error, setError] = useState(null)

    const [page, setPage] = useState(1)
    const [totalPages, setTotalPages] = useState(0)

    const pageGroup = Math.floor((page-1)/3)

    const startPage = pageGroup * 3 + 1;

    const endPage = Math.min(startPage + 2 , totalPages)

    useEffect(() => {

        const loadProducts = async () => {

            try {

                setLoading(true)

                let data

                if (categoryId) {
                    data = await getProductsByCategory(categoryId, page - 1, 4)
                } else {
                    data = await getProducts(page - 1, 4)
                }

                setProducts(data.content)
                setTotalPages(data.totalPages)

            } catch (error) {

                console.error("FAILED TO LOAD PRODUCTS:", error)

                setError("Unable to load products.")

            } finally {

                setLoading(false)
            }
        }

        loadProducts()

    }, [categoryId, page])

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
                        {categoryId ? "SHOP BY CATEGORY" : "ALL PRODUCTS"}
                    </p>

                    <h2>
                        {categoryId
                            ? "Explore Products"
                            : "All Products"
                        }
                    </h2>

                    <p className="section-description">
                        {categoryId
                            ? "Discover products from this category."
                            : "Discover all our products."
                        }
                    </p>

                </div>

            </div>

            <div className="products-grid">

                {products.map((product) => (
                    <ProductCard
                        key={product.id}
                        product={product}
                    />
                ))}

            </div>

            {/* Pagination */}

            <div className="pagination">

                <button
                    disabled={page === 1}
                    onClick={() => setPage(page - 1)}
                >
                    Previous
                </button>

                {Array.from({ length: endPage - startPage + 1 }, (_, index) => index + startPage).map(pageNumber => (
                    <button 
                    key={pageNumber} 
                    onClick={() => setPage(pageNumber)}
                    className={page === pageNumber ? "active" : ""}>{pageNumber}</button>
                ))}
                {endPage < totalPages &&
                    (<button
                        onClick={() => {
                            setPage(endPage + 1)
                        }}
                    >...</button>
                )}
                <button
                    disabled={page >= totalPages}
                    onClick={() => setPage(page + 1)}
                >
                    Next
                </button>

            </div>

        </section>
    )
}

export default Products