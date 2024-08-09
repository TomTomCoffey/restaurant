import React from "react";
import { useContext } from "react";
import { UserContext } from "../context/UserContext";
import { useState } from "react";
import Menu from "../pages/Menu";
import MenuItemModal from "./MenuItemModal";

function MenuItem({ item }) {

    const [isModalOpen, setIsModalOpen] = useState(false);

  

    const closeModal = () => {
        setIsModalOpen(false);
    };

    const openModal = () => {
        setIsModalOpen(true);
    };

    const truncated = item.description.length > 50 ? item.description.substring(0, 50) + '...' : item.description;
    return (
        <>
        <MenuItemModal item={item} isOpen={isModalOpen} onClose={closeModal} />
        <div className="menu-item">
            {/* <img src={item.image} alt={item.title} className="menu-item-image" />  i dont need to worry about this just yet*/}
            <div className="menu-item-info">
                <div className="menu-words">
                <h2 className="menu-item-title">{item.title}</h2>
                <p className="menu-item-description">{truncated}</p>
                </div>
                <div className="menu-words">
                <p className="menu-item-price">${item.price.toFixed(2)}</p>
                <button className="menu-item-button"onClick={openModal}><strong>Add to Cart</strong></button>
                </div>
            </div>
        </div>
        </>
    );
}

export default MenuItem;
