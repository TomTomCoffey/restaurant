import React, { useEffect } from "react";
import { useState } from "react";
import {
  Typography,
  Tabs,
  Tab,
} from "@mui/material";

import BusinessMetrics from "../components/BusinessMetrics";
import ItemTable from "../components/ItemTable";


function Home() {

  const [value, setValue] = useState("one");
 

  const handleChange = (event, newValue) => {
    console.log(newValue);
    setValue(newValue);
  };

  

  return (
    <>
        <Typography variant="h4" align="center" gutterBottom="true">
          Business Dashboard
          {/* I wanna add tabs here to go from windows from business metrics to 86'ing food items */}
          <Tabs value={value} onChange={handleChange}>
            <Tab value="one" label="Business Metrics" />
            <Tab value="two" label="86'd Items" />
            <Tab value="three" label="Add New Item" />
            <Tab value="four" label="Edit Item" />
            <Tab value="five" label="Delete Item" />
            <Tab value="six" label="Add Modifier" />
            <Tab value="seven" label="Edit Modifier" />
            <Tab value="eight" label="Delete Modifier" />
          </Tabs>
        </Typography>

        {value === "one" && <BusinessMetrics />}
        {value === "two" && <ItemTable />}
        {value === "three" && <h1>Add New Item</h1>}
        {value === "four" && <h1>Edit Item</h1>}
        {value === "five" && <h1>Delete Item</h1>}
        {value === "six" && <h1>Add Modifier</h1>}
        {value === "seven" && <h1>Edit Modifier</h1>}
        {value === "eight" && <h1>Delete Modifier</h1>}
    
 

       
    </>
  );
}

export default Home;
