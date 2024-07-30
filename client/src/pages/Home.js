import React from "react";
import { UserContext,  } from "../context/UserContext";
import { useContext } from "react";
import { useState } from "react";


function Home() {

  

    const { user } = useContext(UserContext);
    console.log('From Home.js', user);

  

    const printSomething = () => {
        fetch('http://localhost:8080/api/printer', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + localStorage.getItem('jwtToken')
            },
            body: JSON.stringify({ message: 'Hello from the client i am finally working!' })
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