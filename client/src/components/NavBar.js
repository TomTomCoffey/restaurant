
import React from "react";
import Cart from "./Cart";


function NavBar() {
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