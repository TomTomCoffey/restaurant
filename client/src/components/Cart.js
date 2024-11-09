import React from "react";

import { useState, useContext } from "react";

import { UserContext } from "../context/UserContext";
import { CartContext } from "../context/CartContext";
import { useNavigate } from "react-router-dom";

function Cart() {

    const { user } = useContext(UserContext);
    const { cart, removeFromCart } = useContext(CartContext);
    const { total } = useContext(CartContext);
    const navigate = useNavigate();

    const placeOrder = (e) => {
        e.preventDefault();
        navigate("/order");
        

    }



    return (
        <div>
            <button className="cart-button" onClick={placeOrder}>Place Order ${total.toFixed(2)}</button>
        </div>
    );
}

export default Cart;