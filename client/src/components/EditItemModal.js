import React from "react";
import { Container, FormGroup, TextField, Select, MenuItem, Button, Autocomplete, Chip} from "@mui/material";
import { useState, useEffect } from "react";
import { toast } from "react-toastify";
import axios from "axios";


function EditItemModal({oldItem, setItems, oldItems, handleClose}){

        const [categories, setCategories] = useState([]);
        const [modifiers, setModifiers] = useState([]);
        const [selectedModifiers, setSelectedModifiers] = useState(oldItem.modifiers);
        const [item, setItem] = useState(oldItem);



        const handleChange = (event) => {
            const {name, value} = event.target;
    
            setItem(prevItem => ({
                ...prevItem,
                [name]: value
            }))
    
        }
    
        const handleSubmit = async (e) => {
            e.preventDefault();
            try{
             const newItem = {...item, modifiers:selectedModifiers}   
            const response = await axios.put(`http://localhost:8080/api/item/id/${item.itemId}`, newItem);
            if(response.status === 204){
                setItems(oldItems.map(i => i.itemId === item.itemId ? newItem : i));
                toast.success("Changes successfully Made");
                handleClose();
                
            }
          
            }catch(error){
                if(error.response){
                const errors = error.response.data;
                for(let i = 0; i < errors.length; i++){
                    toast.error(errors[i]);
                }
            }
            }    
        }
        useEffect(() => {
            const fetchCategories = async () => {
                try {
                    const response = await axios.get('http://localhost:8080/api/category');
                        setCategories(response.data);
                } catch (error) {
                    toast.error(error);
                }
            };
            fetchCategories();
        }, []);
    
        useEffect(() => {
            const fetchModifiers = async () => {
                try {
                    const response = await axios.get('http://localhost:8080/api/modifiers');   
                        setModifiers(response.data);
                } catch (error) {
                    toast.error(error);
                }
            };
            fetchModifiers();
        }, []);


    return(
        <>
        <h1>Edit {item.title}</h1>
        <Container maxWidth="sm">
                <form>
                    <FormGroup sx={{ marginBottom: 2, marginTop: 6 }}>
                        <TextField label="Item Name" fullWidth name='title' value={item.title} onChange={handleChange}/>
                    </FormGroup>

                    <FormGroup sx={{ marginBottom: 2 }}>
                        <TextField label="Description" multiline fullWidth name='description' value={item.description} onChange={handleChange}/>
                    </FormGroup>

                    <FormGroup sx={{ marginBottom: 2 }}>
                        <TextField type="number" label="Price" fullWidth name='price' value={item.price}onChange={handleChange}/>
                    </FormGroup>

                    <FormGroup sx={{ marginBottom: 2 }}>
                        <Select displayEmpty fullWidth 
                                name="category"
                                value={item.category || "is this working"}
                                 onChange={handleChange}>
                            <MenuItem value={item.category} disabled>{item.category.name}</MenuItem>
                            {categories.map((category, index) => (
                                <MenuItem key={index} value={category}>{category.name}</MenuItem>
                            ))}
                        </Select>
                    </FormGroup>

                    <FormGroup sx={{ marginBottom: 2 }}>
                        <Autocomplete
                            multiple
                            options={modifiers}
                            getOptionLabel={(mod) => `${mod.name} - $${mod.price}`}
                            value={selectedModifiers}
                            onChange={(event, newValue) => setSelectedModifiers(newValue)}
                            renderInput={(params) => <TextField {...params} label="Select Modifiers" fullWidth />}
                            renderTags={(selected, getTagProps) =>
                                selected.map((option, index) => (
                                    <Chip
                                        key={index}
                                        label={`${option.name} - $${option.price}`}
                                        {...getTagProps({ index })}
                                    />
                                ))
                            }
                            sx={{ width: "100%" }}
                        />
                    </FormGroup>
                    <FormGroup sx={{ marginBottom: 2 }}>
                        <Button variant="outlined" color="primary" type='submit' onClick={handleSubmit}>Submit</Button>
                    </FormGroup>
                </form>
            </Container>
        </>
    )
}

export default EditItemModal;