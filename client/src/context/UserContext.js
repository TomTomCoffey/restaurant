// UserContext.js
import React, { createContext, useState, useEffect, useContext } from 'react';
import { jwtDecode } from "jwt-decode";
import { CartContext } from './CartContext';

const UserContext = createContext({});

const UserProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [decodedTokenIn, setDecodedToken] = useState({});
  const { cart } = useContext(CartContext);

  

  const fetchUserDetails = async (username) => {
    if(username !== undefined){
      const response = await fetch(`http://localhost:8080/api/user/username/${username}`);
      const userDetails = await response.json();
      return userDetails;
    }
    else {
      return Promise.reject('Null username');
    }
  };

  useEffect(() => {
    console.log('UserContext.js useEffect() firing...');
    const token = localStorage.getItem('jwtToken');
    if (token) {
      const decodedToken = jwtDecode(token);
      fetchUserDetails(decodedToken.sub).then(userDetails => {
        setUser({
          username: decodedToken.sub,
          authorities: decodedToken.authorities,
          exp: decodedToken.exp,
          ...userDetails,
          cart: []
        });
      }, (data) => {


        
      });
    }
  }, [decodedTokenIn]);

  return (
    <UserContext.Provider value={{ user, setUser, setDecodedToken }}>
      {children}
    </UserContext.Provider>
  );
};

export { UserContext, UserProvider };
