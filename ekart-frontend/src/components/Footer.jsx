import React from 'react'
import './Footer.css'

const Footer = () => {

    return (

        <footer className="footer">

            <div className="footer-container">

                {/* BRAND */}

                <div className="footer-brand">

                    <div className="footer-logo">
                        <span>🛍</span>
                        eKart
                    </div>

                    <p>
                        Shop smarter. Live better.
                    </p>

                    <p className="footer-description">
                        Your everyday shopping destination for
                        quality products, great deals and a
                        seamless shopping experience.
                    </p>

                </div>


                {/* QUICK LINKS */}

                <div className="footer-section">

                    <h3>
                        Quick Links
                    </h3>

                    <a href="/">
                        Home
                    </a>

                    <a href="/products">
                        Products
                    </a>

                    <a href="/categories">
                        Categories
                    </a>

                    <a href="/cart">
                        Cart
                    </a>

                </div>


                {/* CUSTOMER */}

                <div className="footer-section">

                    <h3>
                        Customer
                    </h3>

                    <a href="/login">
                        Login
                    </a>

                    <a href="/users/register">
                        Register
                    </a>

                    <a href="#">
                        My Account
                    </a>

                    <a href="#">
                        Orders
                    </a>

                </div>


                {/* CONTACT */}

                <div className="footer-section">

                    <h3>
                        Get In Touch
                    </h3>

                    <p>
                        📧 support@ekart.com
                    </p>

                    <p>
                        📞 +91 8919 575841
                    </p>

                    <p>
                        📍 India
                    </p>

                    <div className="footer-socials">

                        <a href="#">
                            Instagram
                        </a>

                        <a href="#">
                            Facebook
                        </a>

                        <a href="#">
                            LinkedIn
                        </a>

                    </div>

                </div>

            </div>


            {/* BOTTOM */}

            <div className="footer-bottom">

                <p>
                    © 2026 eKart. All rights reserved.
                </p>

                <div>

                    <a href="#">
                        Privacy Policy
                    </a>

                    <a href="#">
                        Terms & Conditions
                    </a>

                </div>

            </div>

        </footer>
    )
}

export default Footer