import React, { useState } from 'react'
import { userService } from '../services/userService'
import { useNavigate } from 'react-router-dom'
import './UserRegistration.css'

export const UserRegistration = () => {

    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [mobile, setMobile] = useState('')
    const [email, setEmail] = useState('')

    const [showPassword, setShowPassword] = useState(false)
    const [isLoading, setIsLoading] = useState(false)

    const navigate = useNavigate()


    const handleRegistration = async () => {

        if (!username || !password || !mobile || !email) {
            return
        }

        // Only allow 10-digit Indian mobile number
        if (!/^[6-9]\d{9}$/.test(mobile)) {
            alert('Please enter a valid 10-digit Indian mobile number')
            return
        }

        // Backend receives +91 XXXXXXXXXX
        const indianMobile = `+91 ${mobile}`

        try {

            setIsLoading(true)

            const result = await userService(
                username,
                email,
                indianMobile,
                password
            )

            console.log(result)

        } finally {

            setIsLoading(false)

        }
    }


    return (

        <div className="registration-page">

            {/* =========================
                BACKGROUND DECORATION
            ========================= */}

            <div className="registration-circle registration-circle-one"></div>

            <div className="registration-circle registration-circle-two"></div>

            <div className="registration-circle registration-circle-three"></div>


            <div className="registration-container">


                {/* =========================
                    LEFT SIDE
                ========================= */}

                <div className="registration-intro">

                    <div className="registration-brand">

                        <span className="registration-brand-icon">
                            🛍
                        </span>

                        <span>
                            eKart
                        </span>

                    </div>


                    <div className="registration-intro-content">

                        <span className="registration-welcome-pill">
                            ✨ JOIN THE COMMUNITY
                        </span>


                        <h1>
                            Your next
                            <span> favorite</span>
                            <br />
                            thing is waiting.
                        </h1>


                        <p>
                            Create your eKart account and unlock a
                            smoother, smarter and more enjoyable
                            shopping experience.
                        </p>


                        <div className="registration-benefits">


                            <div className="registration-benefit">

                                <div className="registration-benefit-icon">
                                    🛒
                                </div>

                                <div>
                                    <h3>
                                        Shop Your Way
                                    </h3>

                                    <p>
                                        Discover products made for you
                                    </p>
                                </div>

                            </div>


                            <div className="registration-benefit">

                                <div className="registration-benefit-icon">
                                    ❤️
                                </div>

                                <div>
                                    <h3>
                                        Save Your Favorites
                                    </h3>

                                    <p>
                                        Keep everything you love together
                                    </p>
                                </div>

                            </div>


                            <div className="registration-benefit">

                                <div className="registration-benefit-icon">
                                    ⚡
                                </div>

                                <div>
                                    <h3>
                                        Checkout Faster
                                    </h3>

                                    <p>
                                        Your details ready when you need them
                                    </p>
                                </div>

                            </div>

                        </div>

                    </div>


                    {/* =========================
                        SHOPPING DECORATION
                    ========================= */}

                    <div className="registration-shopping-decoration">

                        <div className="registration-bag registration-bag-black">
                            🛍
                        </div>

                        <div className="registration-bag registration-bag-green">
                            eKart
                        </div>

                        <div className="registration-box">
                            📦
                        </div>

                    </div>

                </div>



                {/* =========================
                    RIGHT SIDE
                ========================= */}

                <div className="registration-card">


                    {/* =========================
                        HEADER
                    ========================= */}

                    <div className="registration-form-header">

                        <div className="registration-hello-icon">
                            👋
                        </div>

                        <span className="registration-form-tag">
                            GET STARTED
                        </span>

                        <h2>
                            Create <span>Account</span>
                        </h2>

                        <p>
                            Join eKart and start shopping smarter.
                        </p>

                        <div className="registration-header-line"></div>

                    </div>



                    <div className="registration-form">


                        {/* =========================
                            USERNAME
                        ========================= */}

                        <div className="registration-input-group">

                            <label>
                                Username
                            </label>

                            <div className="registration-input-wrapper">

                                <span className="registration-input-icon">
                                    👤
                                </span>

                                <input
                                    type="text"
                                    placeholder="Choose a username"
                                    value={username}
                                    onChange={(e) =>
                                        setUsername(e.target.value)
                                    }
                                />

                            </div>

                        </div>



                        {/* =========================
                            EMAIL
                        ========================= */}

                        <div className="registration-input-group">

                            <label>
                                Email
                            </label>

                            <div className="registration-input-wrapper">

                                <span className="registration-input-icon">
                                    ✉
                                </span>

                                <input
                                    type="email"
                                    placeholder="Enter your email"
                                    value={email}
                                    onChange={(e) =>
                                        setEmail(e.target.value)
                                    }
                                />

                            </div>

                        </div>



                        {/* =========================
                            MOBILE
                        ========================= */}

                        <div className="registration-input-group">

                            <label>
                                Mobile Number
                            </label>

                            <div className="registration-mobile-wrapper">


                                {/* COUNTRY CODE */}

                                <div className="registration-country-code">

                                    <span>
                                        🇮🇳
                                    </span>

                                    <select
                                        value="+91"
                                        disabled
                                    >
                                        <option value="+91">
                                            +91
                                        </option>
                                    </select>

                                </div>


                                {/* MOBILE INPUT */}

                                <div className="registration-mobile-input">

                                    <span className="registration-input-icon">
                                        ☎
                                    </span>

                                    <input
                                        type="tel"
                                        placeholder="Enter 10-digit mobile number"
                                        value={mobile}
                                        maxLength={10}
                                        onChange={(e) => {

                                            const value =
                                                e.target.value.replace(
                                                    /\D/g,
                                                    ' '
                                                )

                                            setMobile(value)

                                        }}
                                    />

                                </div>

                            </div>

                            <small className="registration-mobile-hint">
                                🇮🇳 Indian mobile number only
                            </small>

                        </div>



                        {/* =========================
                            PASSWORD
                        ========================= */}

                        <div className="registration-input-group">

                            <label>
                                Password
                            </label>

                            <div className="registration-input-wrapper">

                                <span className="registration-input-icon">
                                    🔒
                                </span>

                                <input
                                    type={
                                        showPassword
                                            ? 'text'
                                            : 'password'
                                    }
                                    placeholder="Create a strong password"
                                    value={password}
                                    onChange={(e) =>
                                        setPassword(e.target.value)
                                    }
                                />

                                <button
                                    type="button"
                                    className="registration-password-toggle"
                                    onClick={() =>
                                        setShowPassword(!showPassword)
                                    }
                                >
                                    {showPassword ? '🙈' : '👁'}
                                </button>

                            </div>

                        </div>



                        {/* =========================
                            REGISTER BUTTON
                        ========================= */}

                        <button
                            className="registration-main-btn"
                            onClick={handleRegistration}
                            disabled={isLoading}
                        >

                            {isLoading ? (

                                <>
                                    <span className="registration-spinner"></span>
                                    Creating Account...
                                </>

                            ) : (

                                <>
                                    Create Account

                                    <span className="registration-arrow">
                                        →
                                    </span>
                                </>

                            )}

                        </button>



                        {/* =========================
                            LOGIN
                        ========================= */}

                        <div className="registration-login">

                            <span>
                                Already have an account?
                            </span>

                            <button
                                onClick={() =>
                                    navigate('/login')
                                }
                            >
                                Sign in
                            </button>

                        </div>

                    </div>

                </div>

            </div>

        </div>
    )
}