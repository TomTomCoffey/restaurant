import React from "react";
import { useContext } from "react";
import { UserContext } from "../context/UserContext";
import { useState } from "react";
import Menu from "../pages/Menu";
import MenuItemModal from "./MenuItemModal";
import { Button , Card, CardContent, Box, Typography} from "@mui/material";

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
            <Card sx={{ maxWidth: 345, m: 2, p: 2, display: "flex", flexDirection: "column" }}>
                <CardContent>
                    <Typography variant="h6" component="h2" gutterBottom>
                        {item.title}
                    </Typography>
                    <Typography variant="body2" color="text.secondary" noWrap>
                        {item.description}
                    </Typography>
                    <Box display="flex" justifyContent="space-between" alignItems="center" mt={2}>
                        <Typography variant="h6">${item.price.toFixed(2)}</Typography>
                        <Button variant="contained" onClick={openModal}>
                            <strong>Add to Cart</strong>
                        </Button>
                    </Box>
                </CardContent>
            </Card>
        </>
    );
}

export default MenuItem;
