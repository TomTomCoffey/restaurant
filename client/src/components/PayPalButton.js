import { useEffect, useContext, useState } from 'react';
import { CartContext } from '../context/CartContext';

const PayPalButton = () => {
  const { total } = useContext(CartContext);
  const { cart } = useContext(CartContext);
  const { user } = useContext(CartContext);


  const [order, setOrder] = useState({
    cart: cart,
    total: total,
    user: user
});

  useEffect(() => {
    console.log('paypal useeffect firing');

   
    window.paypal.Buttons(  
        {
            createOrder: function (data, actions) {
            return actions.order.create({

                purchase_units: [
                {
                    amount: {
                    currency_code: 'USD',
                    value: total.toFixed(2),
                    },
                },
                ],
            });
            },
            onApprove: function (data, actions) {
            return actions.order.capture().then(function (details) {
                console.log(details);
                console.log(details.payer.name.given_name);
                fetch('http://localhost:8080/api/printer', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + localStorage.getItem('jwtToken'),
                },
                body: JSON.stringify({
                    user: {
                        userId: null,
                        username: null,
                        firstName: details.payer.name.given_name,
                        lastName: details.payer.name.surname,
                        email: details.payer.email,
                        hashedPassword: null,
                        roles: null,
                        isBanned: null,
                    },
                    items: cart,
                    time: null,
                    cost: total
                 }),
                })
                .then((response) => response.json())
                .then((data) => {
                    console.log(data);
                })
                .catch((error) => {
                    console.error(error);
                });
            });
            },
        },
        '#paypal-button-container'
        ).render('#paypal-button-container');
    }, [total, cart, user, order]);

  return <div id="paypal-button-container"></div>;
};

export default PayPalButton;


