import { BrowserRouter,Route,Routes } from "react-router-dom"
import { Home } from "./pages/Home"
import { Login } from "./pages/Login"
import  Navbar  from "./components/Navbar";
import { CategoriesPage } from "./pages/CategoriesPage";
import Products from "./pages/Products";
import ProductDetailsPage from "./pages/ProductDetailPage";
import { UserRegistration } from "./pages/UserRegistration";
import Footer from "./components/Footer";
import { Cart } from "./pages/Cart";


function App(){ 
  return(
    <BrowserRouter>
      <Navbar></Navbar>
      <Routes>


        <Route path="/" element={<Home />} />
        <Route path="/products" element={<Products />} />
        <Route path="/products/category/:categoryId" element={<Products />}/>
        <Route path="/categories" element={<CategoriesPage />} />
        <Route path="/products/:productId"   element={<ProductDetailsPage />}/>
        <Route path="/users/register"   element={<UserRegistration />}/>
        <Route path="/auth/login" element={<Login />} />
        <Route path="/users/cart" element={<Cart />} />

      </Routes>
      <Footer />
    </BrowserRouter>
  );
}

export default App