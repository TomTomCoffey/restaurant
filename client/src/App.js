import './App.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { UserProvider } from './context/UserContext';
import { CartProvider } from './context/CartContext';
import Home from './pages/Home';
import Login from './pages/Login';
import Signup from './pages/Signup';
import Menu from './pages/Menu';
import NavBar from './components/NavBar';
import Order from './pages/Order';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import Admin from './pages/Admin';
import Cart from './pages/Cart';


function App() {
  return (
   <>
   <ToastContainer position="top-right" autoClose={3000} />
    <UserProvider>
      <CartProvider>
    <Router>
      <NavBar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/menu" element={<Menu/>}/>
        <Route path="/order" element={<Order/>}/>
        <Route path="/admin" element={<Admin/>}></Route>
        <Route path="/cart" element={<Cart/>}></Route>
      </Routes>
    </Router>
    </CartProvider>
    </UserProvider>
   </>
  );
}

export default App;
