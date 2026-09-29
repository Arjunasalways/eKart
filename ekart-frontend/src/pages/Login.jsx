import React, { useState } from 'react'
import { loginService } from '../services/loginService'
import { useNavigate } from 'react-router-dom'
import './Login.css'

export const Login = () => {

    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [showPassword, setShowPassword] = useState(false)
    const [isLoading, setIsLoading] = useState(false)

    const navigate = useNavigate()

    const handleLogin = async () => {

        if (!username || !password) {
            return
        }

        try {

            setIsLoading(true)

            const result = await loginService(
                username,
                password
            )

            console.log(result)

        } finally {

            setIsLoading(false)

        }
    }

    return (

        <div className="login-page">

            {/* =========================
                FLOATING BACKGROUND
            ========================= */}

            <div className="login-circle login-circle-one"></div>
            <div className="login-circle login-circle-two"></div>
            <div className="login-circle login-circle-three"></div>


            <div className="login-container">


                {/* =========================
                    LEFT SIDE
                ========================= */}

                <div className="login-intro">

                    <div className="login-brand">

                        <span className="login-brand-icon">
                            🛍
                        </span>

                        <span>
                            eKart
                        </span>

                    </div>


                    <div className="login-intro-content">

                        <span className="login-welcome-pill">
                            👋 WELCOME BACK
                        </span>


                        <h1>
                            Your shopping
                            <span> adventure</span>
                            <br />
                            starts here.
                        </h1>


                        <p>
                            Sign in to continue exploring amazing
                            products, exclusive deals and everything
                            you love.
                        </p>


                        <div className="login-benefits">


                            <div className="login-benefit">

                                <div className="login-benefit-icon">
                                    🛒
                                </div>

                                <div>
                                    <h3>
                                        Your Cart Awaits
                                    </h3>

                                    <p>
                                        Pick up right where you left off
                                    </p>
                                </div>

                            </div>


                            <div className="login-benefit">

                                <div className="login-benefit-icon">
                                    ❤️
                                </div>

                                <div>
                                    <h3>
                                        Your Favorites
                                    </h3>

                                    <p>
                                        Everything you love in one place
                                    </p>
                                </div>

                            </div>


                            <div className="login-benefit">

                                <div className="login-benefit-icon">
                                    ⚡
                                </div>

                                <div>
                                    <h3>
                                        Faster Checkout
                                    </h3>

                                    <p>
                                        Shop faster with your account
                                    </p>
                                </div>

                            </div>

                        </div>

                    </div>

                </div>



                {/* =========================
                    RIGHT SIDE
                ========================= */}

                <div className="login-card">


                    {/* =========================
                        HEADER
                    ========================= */}

                    <div className="login-form-header">

                        <div className="login-hello-icon">
                            👋
                        </div>

                        <span className="login-form-tag">
                            WELCOME BACK
                        </span>

                        <h2>
                            Let's <span>Sign In</span>
                        </h2>

                        <p>
                            Enter your details to continue shopping.
                        </p>

                        <div className="login-header-line"></div>

                    </div>



                    <div className="login-form">


                        {/* =========================
                            USERNAME
                        ========================= */}

                        <div className="login-input-group">

                            <label>
                                Username
                            </label>

                            <div className="login-input-wrapper">

                                <span className="login-input-icon">
                                    👤
                                </span>

                                <input
                                    type="text"
                                    placeholder="Enter your username"
                                    value={username}
                                    onChange={(e) =>
                                        setUsername(e.target.value)
                                    }
                                />

                            </div>

                        </div>



                        {/* =========================
                            PASSWORD
                        ========================= */}

                        <div className="login-input-group">

                            <div className="login-password-label">

                                <label>
                                    Password
                                </label>

                                <button
                                    type="button"
                                    className="forgot-password"
                                    onClick={() =>
                                        console.log('Forgot password')
                                    }
                                >
                                    Forgot password?
                                </button>

                            </div>


                            <div className="login-input-wrapper">

                                <span className="login-input-icon">
                                    🔒
                                </span>

                                <input
                                    type={
                                        showPassword
                                            ? 'text'
                                            : 'password'
                                    }
                                    placeholder="Enter your password"
                                    value={password}
                                    onChange={(e) =>
                                        setPassword(e.target.value)
                                    }
                                />

                                <button
                                    type="button"
                                    className="login-password-toggle"
                                    onClick={() =>
                                        setShowPassword(!showPassword)
                                    }
                                >
                                    {showPassword ? '🙈' : '👁'}
                                </button>

                            </div>

                        </div>



                        {/* =========================
                            LOGIN BUTTON
                        ========================= */}

                        <button
                            className="login-main-btn"
                            onClick={handleLogin}
                            disabled={isLoading}
                        >

                            {isLoading ? (

                                <>
                                    <span className="login-spinner"></span>
                                    Signing In...
                                </>

                            ) : (

                                <>
                                    Sign In

                                    <span className="login-arrow">
                                        →
                                    </span>
                                </>

                            )}

                        </button>



                        {/* =========================
                            DIVIDER
                        ========================= */}

                        <div className="login-divider">

                            <span></span>

                            <p>
                                OR
                            </p>

                            <span></span>

                        </div>



                        {/* =========================
                            REGISTER
                        ========================= */}

                        <div className="create-account">

                            <span>
                                New to eKart?
                            </span>

                            <button
                                onClick={() =>
                                    navigate('/users/register')
                                }
                            >
                                Create an account
                            </button>

                        </div>

                    </div>

                </div>

            </div>

        </div>
    )
}