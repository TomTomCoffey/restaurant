import React from "react";
import { UserContext,  } from "../context/UserContext";
import { useContext } from "react";


function Home() {

    const { user } = useContext(UserContext);
    console.log('From Home.js', user);


    return (
        <div>
        <h1>Home for {user.email} </h1>
        </div>
    );
    }

export default Home;