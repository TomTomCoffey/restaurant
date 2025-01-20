import React, { useState, useEffect } from 'react';
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
    });
    return groupedItems;
  };

  const groupedItems = groupByCategory(items);

  // Toggle visibility for a specific category
  const toggleCategory = (categoryName) => {
    setOpenCategories((prev) => ({
      ...prev,
      [categoryName]: !prev[categoryName],
    }));
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
              <TableCell align="center"></TableCell>
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
                    
                    </TableCell>

                </TableRow>

                {/* Collapsible Rows for Items */}
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
                                {item.disabled? "Active" : "Disabled"}
                              </TableCell>
                              <TableCell align="center">
                                <Button
                                  variant="contained"
                                  color={item.disabled? "secondary" : "primary"}
                                  onClick={() =>
                                    console.log(
                                      `Toggle active state for ${item.title}`
                                    )
                                  }
                                >
                                  {item.disabled ? "Disable" : "Enable"}
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
