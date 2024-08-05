import React from "react";
import { useState, useEffect } from "react";
import MenuItem from "../components/MenuItem";

function Menu() {
    const [items, setItems] = useState([]);

    useEffect(() => {
        fetch("http://localhost:8080/api/item")
            .then(response => {
                if (response.status === 200) {
                    return response.json();
                }
                throw new Error('Something went wrong on the server');
            })
            .then(data => {
                setItems(data);
            })
            .catch(error => console.error(error));
    }, []);

    return (
        <>
            <h1 className="menu-title">Menu</h1>
            <div className="menu">
                {items.map(item => <MenuItem key={item.id} item={item} />)}
            </div>
        </>
    );
}

export default Menu;
