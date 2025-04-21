import React, { useContext } from 'react';
import { CartContext } from '../context/CartContext';
import { Button, Typography, Card, CardContent, IconButton, Stack, Box, Divider } from '@mui/material';
import { Delete as DeleteIcon } from '@mui/icons-material';
import RemoveCircleOutlineIcon from '@mui/icons-material/RemoveCircleOutline';
import { useNavigate } from 'react-router-dom';
function Cart(){
    const { cart, total, removeFromCart, clearCart, removeSingleItemFromCart } = useContext(CartContext);
    const navigate = useNavigate();

    const handleCheckout = () => {
        navigate('/order');
    };
    

    return (
        <>
                <Box p={2} maxWidth="600px" mx="auto">
            <Typography variant="h5">
                Your Cart
            </Typography>

            {cart.length === 0 ? (
                <Typography variant="body1">Your cart is empty.</Typography>
            ) : (
                <Stack spacing={2}>
                    {cart.map((item, index) => (
                        <Card key={index} variant="outlined">
                            <CardContent sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                                <Box>
                                    <Typography variant="subtitle1">{item.item}</Typography>
                                    <Typography variant="body2">Qty: {item.quantity}</Typography>
                                    <Typography variant="body2">${item.total.toFixed(2)}</Typography>
                                </Box>
                                <IconButton onClick={() => removeSingleItemFromCart(item)}>
                                    <RemoveCircleOutlineIcon/>
                                </IconButton>
                                <IconButton onClick={() => removeFromCart(item)} aria-label="Remove">
                                    <DeleteIcon />
                                </IconButton>
                            </CardContent>
                        </Card>
                    ))}

                    <Divider />

                    <Box display="flex" justifyContent="space-between" alignItems="center">
                        <Typography variant="h6">Total: ${total.toFixed(2)}</Typography>
                        <Button variant="contained" color="primary" onClick={handleCheckout}>
                            Checkout
                        </Button>
                    </Box>

                    <Button onClick={clearCart} color="error">
                        Clear Cart
                    </Button>
                </Stack>
            )}
        </Box>

      
        </>
    );
}
export default Cart;