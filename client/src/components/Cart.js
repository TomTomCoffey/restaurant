import React, { useState, useContext } from "react";
import { useNavigate } from "react-router-dom";
import { Button, IconButton, Menu, MenuItem } from "@mui/material";
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import { CartContext } from "../context/CartContext";

function Cart() {
    const {total } = useContext(CartContext);
    const navigate = useNavigate();

    const [anchorEl, setAnchorEl] = useState(null);
    const open = Boolean(anchorEl);

    const handleClick = (event) => {
        event.preventDefault();
        setAnchorEl(event.currentTarget);
    };

    const handleClose = () => {
        setAnchorEl(null);
    };

    const placeOrder = () => {
        handleClose();
        navigate("/order");
    };

    const editCart = () => {
        handleClose();
        navigate("/cart");
    };

    return (
        <>
            <IconButton
                aria-controls={open ? 'cart-menu' : undefined}
                aria-haspopup="true"
                aria-expanded={open ? 'true' : undefined}
                onClick={handleClick}
            >
                <ShoppingCartIcon />
            </IconButton>

            <Menu
                id="cart-menu"
                anchorEl={anchorEl}
                open={open}
                onClose={handleClose}
                anchorOrigin={{
                    vertical: 'bottom',
                    horizontal: 'right',
                }}
                transformOrigin={{
                    vertical: 'top',
                    horizontal: 'right',
                }}
            >
                <MenuItem onClick={placeOrder}>
                    Place Order ${total.toFixed(2)}
                </MenuItem>
                <MenuItem onClick={editCart}>
                    Edit Cart
                </MenuItem>
            </Menu>
        </>
    );
}

export default Cart;
