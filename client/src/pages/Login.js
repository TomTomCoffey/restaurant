import React, { useState, useContext } from "react";
import { useNavigate } from "react-router-dom";
import { jwtDecode } from "jwt-decode";
import { UserContext } from "../context/UserContext";

const dummyUser = {
    username: '',
    password: ''
}

function Login() {
    const [user, setUser] = useState(dummyUser);
    const navigate = useNavigate();
    const { setDecodedToken: setToken} = useContext(UserContext);

    const sleep = (milliseconds) => {
        return new Promise(resolve => setTimeout(resolve, milliseconds));
    };

    const onChange = (event) => {
        setUser({
            ...user,
            [event.target.name]: event.target.value
        });
    }

    const login = (event) => {
        event.preventDefault();
        fetch('http://localhost:8080/api/user/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
                
            },
            body: JSON.stringify(user)
        }).then(response => response.json())
          .then(data => {
              if (data.jwt_token) {
                  localStorage.setItem('jwtToken', data.jwt_token);
                  const decodedToken = jwtDecode(data.jwt_token);
                  setToken(decodedToken);
                  sleep(500).then(() => {
                      navigate('/');
                  });

                  console.log('Logged in successfully');
              } else {
               
                  console.log('Failed to login');
              }
          })
          .catch(error => {
          
              console.log(error);
          });
    }

    return (
        <div className="container">
            <h1 className="mb-5 page-desc">Login</h1>
            <form>
                <div className="form-group">
                    <label htmlFor="username">Username</label>
                    <input type="text" className="form-control" id="username" name="username" onChange={onChange} required />
                </div>
                <div className="form-group">
                    <label htmlFor="password">Password</label>
                    <input type="password" className="form-control" id="password" name="password" onChange={onChange} required />
                </div>
                <button type="submit" className="btn btn-primary"id="btn-form" onClick={login}>Login</button>
    
            </form>
        </div>
    )
}
export default Login;
