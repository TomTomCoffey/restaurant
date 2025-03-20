import React, { useState, useContext } from "react";
import { UserContext } from "../context/UserContext";
import { CartContext } from "../context/CartContext";
import { Modal } from "@mui/material";

function UpdatedMenuItemModal({ item, isOpen, onClose }) {
    const [price, setPrice] = useState(item.price);
    const [quantity, setQuantity] = useState(1);
    const [total, setTotal] = useState(price);
    const [modifiers, setModifiers] = useState([]);
    const { user } = useContext(UserContext);
    const { addToCart } = useContext(CartContext);
    const { cart } = useContext(CartContext);

    const groupByModifierCategory = (item) => {
        const groupedModifiers = {};
        item.modifiers.forEach(modifier => {
            if (!groupedModifiers[modifier.modifiersCategory.name]) {
                groupedModifiers[modifier.modifiersCategory.name] = {
                    required: modifier.modifiersCategory.required,
                    modifiers: []
                };
            }
            groupedModifiers[modifier.modifiersCategory.name].modifiers.push(modifier);
        });
        return groupedModifiers;
    };

    const groupModifiers = groupByModifierCategory(item);
    const handleModifierChange = (e, modifier, category) => {
        const modifierPriceChange = e.target.checked ? modifier.price : -modifier.price;

        if (category.required) {
            if (e.target.checked) {
                
               const temp = modifiers.filter(m => m.modifiersCategory.name === modifier.modifiersCategory.name);
               console.log(temp);
               if( temp.length > 0){
                setModifiers([...modifiers.filter(m => m.modifiersCategory.name !== modifier.modifiersCategory.name), modifier]);
                const priceChange = price + modifier.price;
                setPrice(priceChange - temp[0].price);
                setPrice(price + modifier.price);
                setTotal(total + modifier.price - temp[0].price);
        
               }
               else{
                setModifiers([...modifiers, modifier]);
                setPrice(price + modifierPriceChange);
                setTotal(total + modifierPriceChange);
               }
            }
        } else {
            if (e.target.checked) {
                setModifiers([...modifiers, modifier]);
                setPrice(price + modifierPriceChange);
                setTotal(total + modifierPriceChange);
            } else {
                setModifiers(modifiers.filter(m => m.modifier_id !== modifier.modifier_id));
                setPrice(price + modifierPriceChange);
                setTotal(total + modifierPriceChange);
            }
        }
    };

    const add = () => {
        const order = {
            item: item.title,
            quantity: quantity,
            modifiers: modifiers,
            total: total
        };
        console.log(order);
        addToCart(order);
        console.log(cart);
        onClose();
    };

    const updateQuantity = (e) => {
        const newQuantity = parseInt(e.target.value);
        setQuantity(newQuantity);
        setTotal(price * newQuantity);
    };

    const clickInput = (e) => {
        if (e.target.tagName === 'INPUT') return;
        const input = e.target.querySelector('input');
        if (input) {
            input.click();
        }
    };

    const closeModel = () => {
        setModifiers([]);
        setPrice(item.price);
        setQuantity(1);
        setTotal(item.price);
        onClose();
    };

    if (!isOpen) {
        return null;
    }
return(
    <>
    

    </>
)


}

export default UpdatedMenuItemModal;
