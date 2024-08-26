import React, { useState, useEffect, createContext } from "react";

const CartContext = createContext({
    cart: [],
    total: 0,
    addToCart: () => {},
    removeFromCart: () => {},
    clearCart: () => {}
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
        const updatedCart = cart.filter(c => c.title !== item.title);
        setCart(updatedCart);
        setTotal(total - (item.total));
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
