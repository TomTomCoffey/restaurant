import React, { useState, useEffect, useMemo } from 'react';
import {
  Table,
  TableCell,
  TableHead,
  TableRow,
  TableBody,
  Button,
  Collapse,
  Container,
  IconButton,
} from '@mui/material';
import KeyboardArrowUpIcon from '@mui/icons-material/KeyboardArrowUp';
import KeyboardArrowDownIcon from '@mui/icons-material/KeyboardArrowDown';

function ItemTable() {
  const [items, setItems] = useState([]);
  const [openCategories, setOpenCategories] = useState({});
  const [itemState, setItemState] = useState({});
 const [categoryState, setCategoryState] = useState({});

  // Fetch items from API
  useEffect(() => {
    fetch("http://localhost:8080/api/item")
      .then((response) => {
        if (response.status === 200) {
          return response.json();
        }
        throw new Error('Something went wrong on the server');
      })
      .then((data) => {
        setItems(data);
        console.log(data);
        for (let i = 0; i < data.length; i++) {
          setItemState((prev) => ({
            ...prev,
            [data[i].itemId]: data[i].disabled,
          }));
        }
      })
      .catch((error) => console.error(error));   

  }, []);

  // Group items by category
  const groupByCategory = (items) => {
    const groupedItems = {};
    items.forEach((item) => {
      if (!groupedItems[item.category.name]) {
        groupedItems[item.category.name] = [];
      }
      groupedItems[item.category.name].push(item);
      setCategoryState((prev) => ({
        ...prev,
        [item.category.categoryId]: item.category.disabled,
        }));
    });
    return groupedItems;
  };

 
  const groupedItems = useMemo(() => groupByCategory(items), [items]);


  // Toggle visibility for a specific category
  const toggleCategory = (categoryName) => {
    setOpenCategories((prev) => ({
      ...prev,
      [categoryName]: !prev[categoryName],
    }));
  };

  const enableCategory = (categoryId) =>{
    console.log(categoryState);
    console.log(categoryId);
    fetch(`http://localhost:8080/api/item/category/enable/${categoryId}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
    }).then((response) => {
            
        if (response.ok) {
          // Handle success
          console.log('Category enabled successfully');
          setCategoryState((prev) => ({
            ...prev,
            [categoryId]: false,
            }));
        } else {
          // Handle error
          console.error('Failed to enable category');
        }
     })
  }

  const disableCategory = (categoryId) => {
    console.log(categoryId);
    console.log(categoryState);
    fetch(`http://localhost:8080/api/item/category/disable/${categoryId}`, {
            
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
     }).then((response) => {
                
          if (response.ok) {
             // Handle success
             console.log('Category disabled successfully');
                setCategoryState((prev) => ({
                ...prev,
                [categoryId]: true,
                }));

            
          } else {
             // Handle error
             console.error('Failed to disable category');
          }
      })

  }

  const enableItem = (item) => {
    console.log(item);
    fetch(`http://localhost:8080/api/item/id/${item.itemId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            ...item,
            disabled: false,
        }),
        }).then((response) => {
        if (response.ok) {
            // Handle success
            console.log('Item enabled successfully');
            setItemState((prev) => ({
                ...prev,
                [item.itemId]: false,
                }));
        } else {
            // Handle error
            console.error('Failed to enable item');
        }
        }).catch((error) => console.error(error));

  };

  const disableItem = (item) => {
    console.log(item);
    fetch(`http://localhost:8080/api/item/id/${item.itemId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            ...item,
            disabled: true,
        }),
        }).then((response) => {
        if (response.ok) {
            // Handle success
            console.log('Item disabled successfully');
            setItemState((prev) => ({
                ...prev,
                [item.itemId]: true,
                }));
        } else {
            // Handle error
            console.error('Failed to disable item');
        }
        }).catch((error) => console.error(error));
    
};
    
       

  return (
    <>
      <h1 className="menu-title">
        Set Items to Active or Disabled by Category or By Item
      </h1>
      <Container>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell />
              <TableCell>Category</TableCell>
              <TableCell></TableCell>
              <TableCell align="center"></TableCell>
              <TableCell align="center">Status</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {Object.keys(groupedItems).map((categoryName) => (
              <React.Fragment key={categoryName}>
                {/* Category Row */}
                <TableRow>
                  <TableCell>
                    <IconButton
                      aria-label="expand row"
                      size="small"
                      onClick={() => toggleCategory(categoryName)}
                    >
                      {openCategories[categoryName] ? (
                        <KeyboardArrowUpIcon />
                      ) : (
                        <KeyboardArrowDownIcon />
                      )}
                    </IconButton>
                  </TableCell>
                  <TableCell component="th" scope="row" colSpan={4}>
                    <strong>{categoryName}</strong>
                  </TableCell>
                  <TableCell align="center">
                    <strong>{categoryState[groupedItems[categoryName][0].category.categoryId]? "Disabled" : "Active"}</strong>
                    </TableCell>
                    <TableCell align="center">
                    <Button
                                  variant="contained"
                                  color={categoryState[groupedItems[categoryName][0].category.categoryId]? "primary" : "secondary"}
                                  onClick={() => {       
                                    categoryState[groupedItems[categoryName][0].category.categoryId]? enableCategory(groupedItems[categoryName][0].category.categoryId) : disableCategory(groupedItems[categoryName][0].category.categoryId)
                               }
                               }
                                >
                                  {categoryState[groupedItems[categoryName][0].category.categoryId]?"Enable" : "Disable"}
                                
                                </Button>
                        </TableCell>

                </TableRow>
                <TableRow>
                  <TableCell
                    style={{ paddingBottom: 0, paddingTop: 0 }}
                    colSpan={6}
                  >
                    <Collapse
                      in={openCategories[categoryName]}
                      timeout="auto"
                      unmountOnExit
                    >
                      <Table size="small" style={{ marginLeft: "2em" }}>
                        <TableBody>
                          {groupedItems[categoryName].map((item) => (
                            <TableRow key={item.id}>
                              <TableCell />
                              <TableCell>{item.title}</TableCell>
                              <TableCell align="center">
                                {itemState[item.itemId]? "Disabled" : "Active"}
                              </TableCell>
                              <TableCell align="center">
                                <Button
                                  variant="contained"
                                  color={itemState[item.itemId]? "primary": "secondary"}
                                  onClick={() =>
                                    itemState[item.itemId] ? enableItem(item) : disableItem(item)
                                  }
                                >
                                  {itemState[item.itemId] ? "Enable" : "Disable"}
                                </Button>
                              </TableCell>
                            </TableRow>
                          ))}
                        </TableBody>
                      </Table>
                    </Collapse>
                  </TableCell>
                </TableRow>
              </React.Fragment>
            ))}
          </TableBody>
        </Table>
      </Container>
    </>
  );
}

export default ItemTable;
