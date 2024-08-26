import React from "react";

import { useState, useContext } from "react";

import { UserContext } from "../context/UserContext";
import { CartContext } from "../context/CartContext";

function Cart() {

    const { user } = useContext(UserContext);
    const { cart, removeFromCart } = useContext(CartContext);
    const { total } = useContext(CartContext);

  



    const placeOrder = (event) => {
        event.preventDefault();
     
        fetch('http://localhost:8080/api/printer', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + localStorage.getItem('jwtToken')
            },
            body: JSON.stringify({
                user: user,
                items: cart,
                time: null,
                cost: total
             })
        }).then(response => response.json())
            .then(data => {
                console.log(data);
            })
            .catch(error => {
                console.error(error);
            }
            );  
    }


    return (
        <div>
            <button className="cart-button" onClick={placeOrder}>Place Order ${total.toFixed(2)}</button>
        </div>
    );
}

export default Cart;