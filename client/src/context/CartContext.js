import React, { useState, useEffect, createContext } from "react";

const CartContext = createContext({
    cart: [],
    total: 0,
    addToCart: () => {},
    removeFromCart: () => {},
    clearCart: () => {}
});

const CartProvider = ({ children }) => {
    const [cart, setCart] = useState([]);
    const [total, setTotal] = useState(0);

    useEffect(() => {
        const savedCart = JSON.parse(localStorage.getItem('cart')) || [];
        const savedTotal = parseFloat(localStorage.getItem('total')) || 0;
        setCart(savedCart);
        setTotal(savedTotal);
    }, []);

    useEffect(() => {
        localStorage.setItem('cart', JSON.stringify(cart));
        localStorage.setItem('total', total.toFixed(2));
    }, [cart, total]);

    const addToCart = (item) => {
        console.log(item);
        const existingItemIndex = cart.findIndex(c => c.title === item);
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
        console.log(cart);
        setTotal(prevTotal => prevTotal + item.price * item.quantity);

    };

    const removeFromCart = (item) => {
        const updatedCart = cart.filter(c => c.title !== item.title);
        setCart(updatedCart);
        setTotal(prevTotal => prevTotal - item.price * item.quantity);
    };

    const clearCart = () => {
        setCart([]);
        setTotal(0);
    };

    return (
        <CartContext.Provider value={{ cart, total, addToCart, removeFromCart, clearCart }}>
            {children}
        </CartContext.Provider>    
    );
};

export { CartContext, CartProvider };
