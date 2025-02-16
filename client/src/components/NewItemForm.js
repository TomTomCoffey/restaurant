import { Container, FormGroup, TextField, Select, MenuItem, Autocomplete, Chip, Button } from "@mui/material";
import { useState, useEffect } from "react";

const dummyItem = {
    itemId: 0,
    title: "",
    description: "",
    photo: "",
    price: 0.00,
    category: {},
    modifiers: []
};

function NewItemForm() {
    const [categories, setCategories] = useState([]);
    const [modifiers, setModifiers] = useState([]);
    const [selectedModifiers, setSelectedModifiers] = useState([]);
    const [item, setItem] = useState(dummyItem);

    useEffect(() => {
        const fetchCategories = async () => {
            try {
                const response = await fetch('http://localhost:8080/api/category');
                if (response.ok) {
                    const data = await response.json();
                    setCategories(data);
                } else {
                    console.error('Failed to fetch categories');
                }
            } catch (error) {
                console.error('Error fetching categories:', error);
            }
        };
        fetchCategories();
    }, []);

    useEffect(() => {
        const fetchModifiers = async () => {
            try {
                const response = await fetch('http://localhost:8080/api/modifiers');
                if (response.ok) {
                    const data = await response.json();
                    setModifiers(data);
                } else {
                    console.error('Failed to fetch modifiers');
                }
            } catch (error) {
                console.error('Error fetching modifiers:', error);
            }
        };
        fetchModifiers();
    }, []);

    return (
        <>
            <h1>Add a New Item</h1>
            <Container maxWidth="sm">
                <form>
                    <FormGroup sx={{ marginBottom: 2 }}>
                        <TextField label="Item Name" fullWidth />
                    </FormGroup>

                    <FormGroup sx={{ marginBottom: 2 }}>
                        <TextField label="Description" multiline fullWidth />
                    </FormGroup>

                    <FormGroup sx={{ marginBottom: 2 }}>
                        <TextField type="number" label="Price" fullWidth />
                    </FormGroup>

                    <FormGroup sx={{ marginBottom: 2 }}>
                        <Select displayEmpty fullWidth>
                            <MenuItem value="" disabled>Select Category</MenuItem>
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
                        <Button color="primary">Submit</Button>
                    </FormGroup>
                </form>
            </Container>
        </>
    );
}

export default NewItemForm;
