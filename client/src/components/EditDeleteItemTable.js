import { Container, getBottomNavigationActionUtilityClass, Table, TableBody, TableCell, TableHead, TableRow, Button } from "@mui/material";
import axios from "axios";
import {useState, useEffect} from 'react';
import { toast } from "react-toastify";

function EditDeleteItemTable(){

    const [items, setItems] = useState([]);
    const [checker, setChecker] = useState(false);
    
    useEffect(()=>{
        const getMenu = async () => {
            try{
                const response = await axios.get('http://localhost:8080/api/item');
                    setItems(response.data);      
            }catch(error){
                toast.error(error);
            }
        }
        getMenu();
    },[])


    const deleteItem = async (item) => {

        const isConfirmed = window.confirm("Are you sure you want to delete this item?");

        if(isConfirmed){
            console.log(item.itemId);
            try{
                const response = await axios.delete(`http://localhost:8080/api/item/${item.itemId}`);
                if(response.status === 204){
                    toast.success(`${item.title} was successfully deleted`);
                    setItems(items.filter(i => i.itemId !== item.itemId));
                }
            }catch(error){
                toast.error(error);
            
            } 
        }
    }


    return(
        <>
        <Container>
            <Table>
                <TableHead>
                    <TableRow>
                        <TableCell>Name</TableCell>
                        <TableCell>Description</TableCell>
                        <TableCell>Price</TableCell>
                        <TableCell>Edit</TableCell>
                        <TableCell>Delete</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                  {items.map((item)=> 
                  <TableRow>
                    <TableCell>{item.title}</TableCell>
                    <TableCell>{item.description}</TableCell>
                    <TableCell>{item.price}</TableCell>
                    <TableCell><Button variant="outlined" color="primary">Edit</Button></TableCell>
                    <TableCell><Button varient="outlined" color="error" onClick={() => deleteItem(item)}>Delete</Button></TableCell>
                  </TableRow>
                )}
                </TableBody>
            </Table>
        </Container>

        </>
    )
}

export default EditDeleteItemTable;