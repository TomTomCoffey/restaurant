import React, { useState, useEffect, createContext } from "react";
import { toast } from "react-toastify";

const CartContext = createContext({
    cart: [],
    total: 0,
    addToCart: () => {},
    removeFromCart: () => {},
    clearCart: () => {},
    removeSingleItemFromCart: () => {}

});

const CartProvider = ({ children }) => {
    const [cart, setCart] = useState(() => {
        const savedCart = localStorage.getItem('cart');
        return savedCart ? JSON.parse(savedCart) : [];
    });
    const [total, setTotal] = useState(() => {
        const savedTotal = localStorage.getItem('total');
        return savedTotal ? parseFloat(savedTotal) : 0;
    });

    useEffect(() => {
        localStorage.setItem('cart', JSON.stringify(cart));
        localStorage.setItem('total', total.toFixed(2));  
    }, [cart, total]);

    const addToCart = (item) => {
        console.log('From CartContext.js', item);
        const existingItemIndex = cart.findIndex(c => c.title === item.item);
        let updatedCart;
        if (existingItemIndex >= 0) {
            updatedCart = cart.map((cartItem, index) => 
                index === existingItemIndex
                ? { ...cartItem, quantity: cartItem.quantity + item.quantity }
                : cartItem
            );
        } else {
            updatedCart = [...cart, item];
        }
        setCart(updatedCart);        
        setTotal(total + (item.total));

    };

    const removeFromCart = (item) => {
        const updatedCart = cart.filter(c => c.item !== item.item);
        setCart(updatedCart);
        setTotal(total - item.total);
  
    };
    ////need to test this function out
    const removeSingleItemFromCart = (item) => {

        if(item.quantity === 1){
            removeFromCart(item);

        }
        else{
        // const pricePer = item.total / item.quantity;
        const existingItemIndex = cart.findIndex(c => c.item === item.item);
        const pricePerItem = item.total / item.quantity;
        let updatedCart;
        if(existingItemIndex >= 0){
            updatedCart = cart.map((cartItem, index) => 
                index === existingItemIndex ? {...cartItem, quantity: cartItem.quantity - 1, total: (total - pricePerItem)} : cartItem
            );
        }
        setCart(updatedCart);
        setTotal(total - (item.total / item.quantity));

        }

    };

    const clearCart = () => {
        setCart([]);
        setTotal(0);
    };

    return (
        <CartContext.Provider value={{ cart, total, addToCart, removeFromCart, clearCart, removeSingleItemFromCart }}>
            {children}
        </CartContext.Provider>    
    );
};

export { CartContext, CartProvider };
