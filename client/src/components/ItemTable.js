import React from 'react';
import { useState } from 'react';
import { Table, TableCell, TableHead, TableRow, TableBody, Button, Collapse } from '@mui/material';
import { useEffect } from 'react';


function ItemTable() {
    const [items, setItems] = useState([]);
    const [active, setActive] = useState(false);
    const [newItem, setNewItem] = useState({});



    const handleActive = () => {
        //this is just a temp function to test the button 
        setActive(!active);
    }
    

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
        }
        , []);
    
        const groupItems = groupByCategory(items);



  return (
    <>
    <h1 className="menu-title">Set Items to Active or Disabled</h1>
    <Table>
        <TableHead>
            <TableRow>
                <TableCell>Name</TableCell>
                <TableCell>Description</TableCell>
                <TableCell>Price</TableCell>
                <TableCell>Active</TableCell>
            </TableRow>
        </TableHead>
        <TableBody>
        {items.map((item) => (
                        <TableRow key={item.itemId}>
                          <TableCell>{item.title}</TableCell>
                          <TableCell>{item.description || "N/A"}</TableCell>
                          <TableCell>${item.price.toFixed(2)}</TableCell>
                        {item.disabled && <TableCell><Button color="warning" variant='contained' sx={{borderRaduis: "8px"}}>Disabled</Button></TableCell>}
                        {!item.disabled && <TableCell><Button color="success" variant="contained" sx={{borderRadius: "8px"}} >Active</Button></TableCell>}
                        </TableRow>
                      ))}
        </TableBody>
    </Table>
    </>

  );
}

export default ItemTable;