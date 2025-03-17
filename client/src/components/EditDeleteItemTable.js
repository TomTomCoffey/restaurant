import {
  Container,
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
  Button,
  Modal,
  Box,
} from "@mui/material";
import axios from "axios";
import { useState, useEffect } from "react";
import { toast } from "react-toastify";
import EditItemModal from "./EditItemModal";

function EditDeleteItemTable() {
  const [items, setItems] = useState([]);
  const [open, setOpen] = useState(false);
  const [item, setItem] = useState({}); 

  const handleOpen = () => {
    setOpen(true);
  };
  const handleClose = () => {
    setOpen(false);
  };

  useEffect(() => {
    const getMenu = async () => {
      try {
        const response = await axios.get("http://localhost:8080/api/item");
        setItems(response.data);
      } catch (error) {
        toast.error(error);
      }
    };
    getMenu();
  }, []);

  const deleteItem = async (item) => {
    const isConfirmed = window.confirm(
      "Are you sure you want to delete this item?"
    );

    if (isConfirmed) {
      console.log(item.itemId);
      try {
        const response = await axios.delete(
          `http://localhost:8080/api/item/${item.itemId}`
        );
        if (response.status === 204) {
          toast.success(`${item.title} was successfully deleted`);
          setItems(items.filter((i) => i.itemId !== item.itemId));
        }
      } catch (error) {
        toast.error(error);
      }
    }
  };

  const openEditItemModal = (item) => {
    setItem(item);
    handleOpen();  ///now i need to figure out how to maintain state for when the new item is updated , duh I figured it out lol
  };
///also i need the modal to be responsive and not cut out when its too big 

  return (
    <>
      <Container>
        <Modal
          open={open}
          onClose={handleClose}
          aria-labelledby="child-modal-title"
          aria-describedby="child-modal-description"
          scrollBehavior="auto"
        >
          <Box
            sx={{
              position: "absolute",
              scrollBehavior: "auto",
              top: "50%",
              left: "50%",
              transform: "translate(-50%, -50%)",
              width: 600,
              bgcolor: "background.paper",
              border: "2px solid #000",
              boxShadow: 24,
              p: 4,
              borderRadius: 2,
            }}
          >
            <EditItemModal oldItem={item} setItems={setItems} oldItems={items} handleClose={handleClose}></EditItemModal>
          </Box>
        </Modal>
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
            {items.map((item) => (
              <TableRow>
                <TableCell>{item.title}</TableCell>
                <TableCell>{item.description}</TableCell>
                <TableCell>{item.price}</TableCell>
                <TableCell>
                  <Button
                    variant="outlined"
                    color="primary"
                    onClick={() => openEditItemModal(item)}
                  >
                    Edit
                  </Button>
                </TableCell>
                <TableCell>
                  <Button
                    variant="outlined"
                    color="error"
                    onClick={() => deleteItem(item)}
                  >
                    Delete
                  </Button>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </Container>
    </>
  );
}

export default EditDeleteItemTable;
