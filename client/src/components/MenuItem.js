import React from "react";

function MenuItem({ item }) {
    return (
        <div className="menu-item">
            {/* <img src={item.imageUrl} alt={item.title} className="menu-item-image" /> */}
            <div className="menu-item-info">
                <div classname="menu-words">
                <h2 className="menu-item-title">{item.title}</h2>
                <p className="menu-item-description">{item.description}</p>
                </div>
                <p className="menu-item-price">${item.price.toFixed(2)}</p>
            </div>
        </div>
    );
}

export default MenuItem;
