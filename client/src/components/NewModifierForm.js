
import React from "react";
import { useState, useEffect } from "react";
import axios from 'axios';
import { toast } from "react-toastify";



function NewModifierForm(){
    const tempModifier = useState({});
    const [catMods, setCatMods] = useState([]);

    useEffect(() => {
        const a = async () => {
            const response =  await axios.get('http://localhost:8080/api/modifiers"/category');
            if(response.status === 200){
                console.log(response.data);
                setCatMods(response.data);
            }
        }
    }, [])
    


    return(
        <>
          <h1>Hello World</h1>
        
        </>
    );
}

export default NewModifierForm;

