
import React from "react";
import Cart from "./Cart";
import { useContext } from "react";
import { UserContext } from "../context/UserContext";


function NavBar() {

  const {user} = useContext(UserContext);
  return (
    ///This is all a temporary placeholder for now
    <nav className="navbar navbar-expand-lg navbar-light bg-light">
      <a className="navbar-brand" href="/">
        Navbar
        <Cart />
      </a>
    </nav>
  );
}

export default NavBar;