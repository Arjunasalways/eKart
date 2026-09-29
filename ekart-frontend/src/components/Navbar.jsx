import { Link } from "react-router-dom";
import "./Navbar.css";


function Navbar() {
    return (
        <nav className="navbar">

            <Link to="/" className="navbar-logo">
                eKart
            </Link>

            <div className="navbar-search">

                <input
                    type="text"
                    placeholder="Search for products, brands and more..."
                />

                <button className="search-button">

                    <svg
                        xmlns="http://www.w3.org/2000/svg"
                        width="22"
                        height="22"
                        viewBox="0 0 24 24"
                        fill="none"
                        stroke="currentColor"
                        strokeWidth="2"
                        strokeLinecap="round"
                        strokeLinejoin="round"
                    >
                        <circle cx="11" cy="11" r="8"></circle>
                        <line x1="21" y1="21" x2="16.65" y2="16.65"></line>
                    </svg>

                </button>

            </div>

            <div className="navbar-links">
                <Link to="/">Home</Link>
                <Link to="/products">Products</Link>
                <Link to="/wishlist">Wishlist</Link>
                <Link to="/users/cart">Cart</Link>
                <Link to="/auth/login" className="login-link">Login</Link>
            </div>

        </nav>
    );
}

export default Navbar;