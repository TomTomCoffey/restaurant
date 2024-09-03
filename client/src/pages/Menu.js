import React from "react";
import { useState, useEffect } from "react";
import MenuItem from "../components/MenuItem";


function Menu() {
    const [items, setItems] = useState([]);
    

    const groupByCategory = (items) => {
        const groupedItems = {};
        items.forEach(item => {
            if (!groupedItems[item.category.name]) {
                groupedItems[item.category.name] = [];
            }
            groupedItems[item.category.name].push(item);
        });
        return groupedItems;
    }

    const groupItems = groupByCategory(items);


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
                console.log(data);
            })
            .catch(error => console.error(error));
    }, []);

    return (
        <>
        <h1 className="menu-title">Menu</h1>
        <div className="menu">
            {Object.keys(groupItems).map(category => (
                <div key={category} className="menu-category">                  
                    <h2 className="category-title">{category}</h2>
                    <div className="menu-items">
                        {groupItems[category].map(item => (
                            <MenuItem key={item.id} item={item} />
                        ))}
                    </div>
                </div>
            ))}
        </div>
    </>
    );
}

export default Menu;
