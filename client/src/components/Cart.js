import React from "react";

import { useState, useContext } from "react";

import { UserContext } from "../context/UserContext";
import { CartContext } from "../context/CartContext";

function Cart() {

    const { user } = useContext(UserContext);
    const { cart, removeFromCart } = useContext(CartContext);

    console.log('From Cart.js', cart);

    const remove = (order) => {
        removeFromCart(order);
    }

    return (
        <div>
            <button className="cart-button">Cart {cart.length}</button>
        </div>
    );
}

export default Cart;