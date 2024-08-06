import React from "react";
import { useContext } from "react";
import { UserContext } from "../context/UserContext";

function MenuItem({ item }) {

    const { user } = useContext(UserContext);

    const addToCart = () => {
        console.log('Adding item to cart:', item);
        
    }

    const truncated = item.description.length > 50 ? item.description.substring(0, 50) + '...' : item.description;
    return (
        <div className="menu-item">
            {/* <img src={item.image} alt={item.title} className="menu-item-image" />  i dont need to worry about this just yet*/}
            <div className="menu-item-info">
                <div className="menu-words">
                <h2 className="menu-item-title">{item.title}</h2>
                <p className="menu-item-description">{truncated}</p>
                </div>
                <div className="menu-words">
                <p className="menu-item-price">${item.price.toFixed(2)}</p>
                <button className="menu-item-button"onClick={addToCart}><strong>Add to Cart</strong></button>
                </div>
            </div>
        </div>
    );
}

export default MenuItem;
