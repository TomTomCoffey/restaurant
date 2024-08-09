import React from "react";
import { useState } from "react";

function MenuItemModal({ item, isOpen, onClose }) {

    const [price, setPrice] = useState(item.price);
    const [quantity, setQuantity] = useState(1);
    const [total, setTotal] = useState(price);


    const updateQuantity = (e) => {
        setQuantity(e.target.value);
        setTotal(price * e.target.value);
    }

    if (!isOpen) {
        return null;
    }

    return (
        <div className="modal show" tabIndex="-1" role="dialog" style={{ display: 'block' }}>
        <div className="modal-dialog" role="document">
            <div className="modal-content">
                <div className="modal-header">
                    <h5 className="modal-title">{item.title}</h5>
                    <button type="button" className="close" aria-label="Close" onClick={onClose}>
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div className="modal-body">
                   <p>{item.description}</p>
                   {item.modifiers.map(modifier => (
                       <div key={modifier.modifier_id}>
                           <h5>{modifier.name}   ${modifier.price}</h5>
                       </div>
                   ))}
            
                </div>
                <div className="modal-footer">
            
                    <button type="button" className="btn btn-secondary" onClick={onClose}>Cancel</button>
                </div>
                <div>
            
                </div>
            </div>
        </div>
    </div>
    );
}

export default MenuItemModal;