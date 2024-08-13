import React from "react";
import { useState } from "react";

function MenuItemModal({ item, isOpen, onClose }) {

    const [price, setPrice] = useState(item.price);
    const [quantity, setQuantity] = useState(1);
    const [total, setTotal] = useState(price);
    const [modifiers, setModifiers] = useState([]);


    const updateQuantity = (e) => {

  
            setQuantity(e.target.value);
            setTotal(price * e.target.value);
        
  
    }

    const handleModifierChange = (e, modifier) => {

        if(Array.isArray(modifiers)){

        if(e.target.checked) {
            let temp = [...modifiers, modifier]
            setModifiers(temp);
            setPrice(price + modifier.price);
            setTotal(price + modifier.price);
        } 
        else {

          setModifiers(...modifiers.filter(m => m.modifier_id !== modifier.modifier_id))
            setPrice(price - modifier.price);
            setTotal(total - modifier.price);    
        }
    }
    else{
        setModifiers([]);
        if(e.target.checked) {
            setModifiers([modifier]);
            setPrice(price + modifier.price);
            setTotal(price + modifier.price);
        } 
        else {
            setPrice(price - modifier.price);
            setTotal(total - modifier.price);
        }
    }
};
    

    const clickInput = (e) => {
        if(e.target.tagName === 'INPUT') return;
        e.target.querySelector('input').click();
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
                    <h5 className="modal-title">Total: ${total.toFixed(2)}</h5>
                    {/* <button type="button" className="close" aria-label="Close" onClick={onClose}>
                        <span aria-hidden="true">&times;</span>
                    </button> */}
                </div>
                <div className="modal-body">
                   <p>{item.description}</p>
                  {item.modifiers.length > 0 && <h3>Modifiers</h3>}
                   {item.modifiers.map(modifier => (
                       <div key={modifier.modifier_id} className="menu-modifiers" onClick={clickInput}>
                           <input type="checkbox" id={modifier.modifier_id} onChange={(e) => handleModifierChange(e, modifier)} />
                           <label htmlFor={modifier.modifier_id}>{modifier.name} (+${modifier.price.toFixed(2)})</label>
                       </div>
                   ))}
            
                </div>
                <div className="modal-footer">
                    <input type="number" className="modal-quantity" value={quantity} onChange={updateQuantity} />
                   <h3>Total: ${total.toFixed(2)}</h3>
                    <button type="button" className="btn btn-secondary" onClick={onClose}>Cancel</button>
                    <button type="button" className="btn btn-primary">Add to Cart</button>
                </div>
                <div>
            
                </div>
            </div>
        </div>
    </div>
    );
}

export default MenuItemModal;