import {useState, useEffect} from "react";


const item = {


}

function NewItemForm() {

    const [categorys, setCategories] = useState([]);
    const [modifiers, setModifiers] = useState([]);

    useEffect(() => {
        const fetchCategories = async () => {
            try{
                const response = await fetch('http://localhost:8080/api/category');
                if (response.ok) {
                    const data = await response.json();
                    setCategories(data);
                } else {
                    console.error('Failed to fetch categories');
                }

            }
            catch (error) {
                console.error('Error fetching categories:', error);
            }
        }
    });

    useEffect(() => {
        const fetchModifiers = async () => {
                try{
                    const response = await fetch('http://localhost:8080/api/modifiers');
                    if (response.ok) {
                        const data = await response.json();
                        setModifiers(data);
                    } else {
                        console.error('Failed to fetch modifiers');
                    }
    
                }
                catch (error) {
                    console.error('Error fetching modifiers:', error);
                }
            }
        });


        

   
        

        







    return(
        <>
        <h1>New item form</h1>
        </>
    );
}
export default NewItemForm;