import React, { useState, useContext } from "react";
import { UserContext } from "../context/UserContext";
import { CartContext } from "../context/CartContext";
import "./MenuItemModal.css";
import { Button } from "@mui/material";
import { toast } from "react-toastify";

function MenuItemModal({ item, isOpen, onClose }) {
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
        toast.success(order.item + " added to cart!");
        onClose();
    };

    const updateQuantity = (e) => {
        const newQuantity = parseInt(e.target.value);

        if(newQuantity > 0){
        setQuantity(newQuantity);
        setTotal(price * newQuantity);
    }
        else{
            toast.error("You must have at least one item");
        }


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

    return (
        <div className="modal-backdrop" onClick={closeModel}>
        <div className="modal show" tabIndex="-1" role="dialog" style={{ display: 'block' }}>
            <div className="modal-dialog" role="document">
                <div className="modal-content" onClick={(e) => e.stopPropagation()}>
                    <div className="modal-header">
                        <h5 className="modal-title">{item.title}</h5>
                        <h5 className="modal-title">Total: ${total.toFixed(2)}</h5>
                    </div>
                    <div className="modal-body">
                        <p>{item.description}</p>
                        {Object.keys(groupModifiers).map(categoryName => {
                            const category = groupModifiers[categoryName];
                            return (
                                <div key={categoryName} className="menu-modifiers">
                                    <h4 className={`modifier-category ${category.required ? 'required' : ''}`}>{categoryName}</h4>
                                    {category.modifiers.map(modifier => (
                                        <div key={modifier.modifier_id} className="modifier-option" onClick={clickInput}>
                                            <input
                                                type={category.required ? "radio" : "checkbox"}
                                                name={categoryName}
                                                id={modifier.modifier_id}
                                                onChange={(e) => handleModifierChange(e, modifier, category)}
                                            />
                                            <label htmlFor={modifier.modifier_id}>{modifier.name} (+${modifier.price.toFixed(2)})</label>
                                        </div>
                                    ))}
                                </div>
                            );
                        })}
                    </div>
                    <div className="modal-footer">
                        <div className="quantity-control">
                          <Button size="small" onClick={() => updateQuantity({ target: { value: quantity - 1 } })}>-</Button>
                          <h5> { quantity } </h5>
                          <Button size="small" onClick={() => updateQuantity({ target: { value: quantity + 1 } })}>+</Button>
                         </div>
                 <div className="total-amount">
                        Total: ${total.toFixed(2)}
                 </div>
                 <div className="button-container">
                   <Button variant="contained"  onClick={closeModel}>Cancel</Button>
                   <Button variant="contained"  onClick={add}>Add to Cart</Button>
                </div>
                </div>
                </div>
            </div>
        </div>
        </div>
    );
}

export default MenuItemModal;
