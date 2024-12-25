import React, { useEffect } from "react";
import { useState } from "react";
import { Container, Grid2, Card, CardContent, Typography } from '@mui/material';
import { Line } from 'react-chartjs-2';
import 'chart.js/auto'; // Import Chart.js

// Sample data
const chartData = {
  labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul'],
  datasets: [
    {
      label: 'Revenue',
      data: [65, 59, 80, 81, 56, 55, 40],
      borderColor: '#42A5F5',
      backgroundColor: 'rgba(66, 165, 245, 0.2)',
    },
  ],
};



function Home() {

    const [ items, setItems] = useState([]);
    const [revenue, setRevenue] = useState([]);

   


    useEffect(() => {
        fetch("http://localhost:8080/api/revenue")
            .then(response => {
                if (response.status === 200) {
                    return response.json();
                }
                throw new Error('Something went wrong on the server');
            })
            .then(data => {
                console.log(data);
            })
            .catch(error => console.error(error));
    }
    , []);



   


    return (
      <>
       <Container>
      <Grid2 container spacing={4}>
        <Grid2 item md={9}>
          <Card>
            <CardContent>
              <Typography variant="h5" gutterBottom>
                Revenue Over Time
              </Typography>
              <Line data={chartData} />
            </CardContent>
          </Card>
        </Grid2>

        <Grid2 item xs={12} sm={6}>
          <Card>
            <CardContent>
              <Typography variant="h6" gutterBottom>
                Top 5 Most Ordered Foods
              </Typography>
              {/* Here you could list out the top 5 items dynamically */}
            </CardContent>
          </Card>
        </Grid2>

        {/* Add more components as needed */}
      </Grid2>
    </Container>
      </>

    );
    }

export default Home;