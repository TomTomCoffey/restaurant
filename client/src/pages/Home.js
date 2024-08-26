import React from "react";
import { UserContext,  } from "../context/UserContext";
import { useContext } from "react";
import { useState } from "react";
import { CartContext } from "../context/CartContext";


function Home() {

    const { user } = useContext(UserContext);
    const {cart} = useContext(CartContext);
    const { total } = useContext(CartContext);

    const [order, setOrder] = useState({
        cart: cart,
        total: total,
        user: user
    });



    const printSomething = () => {
        fetch('http://localhost:8080/api/printer', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + localStorage.getItem('jwtToken')
            },
            body: JSON.stringify(order)
        }).then(response => response.json())
          .then(data => {
              console.log(data);
          })
          .catch(error => {
              console.error(error);
          });
        };


    return (
        <div>
        {user && <h1>Home for {user.email} </h1>}
        <button onClick={printSomething}>Print Something</button>
                </div>
    );
    }

export default Home;