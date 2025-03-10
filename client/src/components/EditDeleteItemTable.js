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


    const deleteItem = (item) => {

        alert("Are you sure you want to delete this item?");

        if(checker){
            try{
                const response = axios.delete(`http://localhost:8080/api/item/${item.itemmId}`);
                if(response.status === 204){
                    toast.success(`${item.title} was successfully deleted`);
                    const newItemList = items.filter(i => i.itemId !== item.itemId);
                    setItems(newItemList);
                    setChecker(false);
                }
            }catch(error){
                toast.error(error);
                setChecker(false);
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