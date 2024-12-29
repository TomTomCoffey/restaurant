import React, { useEffect } from "react";
import { useState } from "react";
import {
  Container,
  Grid2,
  Card,
  CardContent,
  Typography,
  CardHeader,
  Table,
  TableCell,
  TableHead,
  TableRow,
  TableBody,
  CircularProgress,
    Box
} from "@mui/material";
import { Line, Bar, Pie, Doughnut } from "react-chartjs-2";
import "chart.js/auto";

// Sample data
const fakeChartData = {
  labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul"],
  datasets: [
    {
      label: "Revenue",
      data: [65, 59, 80, 81, 56, 55, 40],
      borderColor: "#42A5F5",
      backgroundColor: "rgba(66, 165, 245, 0.2)",
    },
  ],
};

function Home() {
  const [items, setItems] = useState([]);
  const [revenue, setRevenue] = useState([]);
  const [chartData, setChartData] = useState(fakeChartData);
  const [value, setValue] = useState("one");
  const [todaysRevenue, setTodaysRevenue] = useState(0);
  const dailyGoal = 1000;

  const onChange = (event) => {
    setValue(event.target.value);
  };

  useEffect(() => {
    fetch("http://localhost:8080/api/revenue")
      .then((response) => {
        if (response.status === 200) {
          return response.json();
        }
        throw new Error("Something went wrong on the server");
      })
      .then((data) => {
        setItems(data.items[0]);
        setRevenue(data.revenue[0]);
      })
      .then(() => {
        const data = {
          labels: revenue.map((r) => r.date),
          datasets: [
            {
              label: "Revenue",
              data: revenue.map((r) => r.revenue),
              borderColor: "#42A5F5",
              backgroundColor: "rgba(66, 165, 245, 0.2)",
            },
          ],
        };
        setChartData(data);
        setTodaysRevenue(revenue[revenue.length - 1].revenue);
      })
      .catch((error) => console.error(error));
  }, [revenue]);

  return (
    <>
      <div style={{ padding: "20px" }}>
        {/* Header */}
        <Typography variant="h4" align="center" gutterBottom="true">
          Business Dashboard
          {/* I wanna add tabs here to go from windows from business metrics to 86'ing food items */}
        </Typography>

        {/* Grid2 Layout */}
        <Grid2 container spacing={5} alignItems="stretch">
          {/* Total Revenue Card This compondent needs like a big number instead of the chart the chart is a placeholder*/}
          <Grid2 item xs={12} sm={6} md={4}>
          <Card sx={{ boxShadow: 3, borderRadius: 2, height: "100%" }}>
          <CardContent>
  <Typography variant="h4" gutterBottom>
    Revenue Today
  </Typography>
  <Box sx={{ display: "flex", alignItems: "center", justifyContent: "center" }}>
    <CircularProgress
      variant="determinate"
      value={(todaysRevenue / dailyGoal) * 100}
      size={80}
      thickness={5}
      color="primary"
    />
    <Typography
      variant="h5"
      sx={{ position: "absolute", fontWeight: "bold" }}
    >
      {(todaysRevenue / dailyGoal) * 100}%
    </Typography>
  </Box>
    <Typography variant="h6" gutterBottom align="center">
        Daily Goal: ${dailyGoal}
    </Typography>
    <Typography variant="h6" gutterBottom align="center">
        Today's Revenue
    </Typography>
  <Typography variant="h2" sx={{ marginTop: 2 }} color="primary">
    ${todaysRevenue.toFixed(2)}
  </Typography>
</CardContent>
</Card>

          </Grid2>

          <Grid2 item xs={12} sm={6} md={4}>
            <Card sx={{ boxShadow: 3, borderRadius: 2 , height: "100%"}}>
              <CardHeader title="Revenue in Past 7 Days" />
              <CardContent>
                <Typography variant="h6">
                  <Bar data={chartData} />
                </Typography>
                <Typography variant="h6">Total Revenue</Typography>
                <Typography variant="h4" color="primary">
                  ${revenue.reduce((sum, r) => sum + r.revenue, 0).toFixed(2)}
                </Typography>
              </CardContent>
            </Card>
          </Grid2>

          <Grid2 item xs={12} sm={6} md={4}>
            <Card sx={{ boxShadow: 3, borderRadius: 2 }}>
              <CardHeader title="Revenue in Past 30 Days" />
              <CardContent>
                <Typography variant="h6">
                  <Line data={chartData} />
                </Typography>
                <Typography variant="h6">Total Revenue</Typography>
                <Typography variant="h4" color="primary">
                  ${revenue.reduce((sum, r) => sum + r.revenue, 0).toFixed(2)}
                </Typography>
              </CardContent>
            </Card>
          </Grid2>

    
          <Grid2 item xs={12} sm={6} md={4}>
            <Card sx={{ boxShadow: 3, borderRadius: 2 }}>
              <CardContent>
                <Typography variant="h6">Top 5 Best Sellers</Typography>
                <Typography variant="h4" color="primary">
                  <Table>
                    <TableHead>
                      <TableRow>
                        <TableCell>Name</TableCell>
                        <TableCell>Description</TableCell>
                        <TableCell>Price</TableCell>
                      </TableRow>
                    </TableHead>
                    <TableBody>
                      {items.map((item) => (
                        <TableRow key={item.itemId}>
                          <TableCell>{item.title}</TableCell>
                          <TableCell>{item.description || "N/A"}</TableCell>
                          <TableCell>${item.price.toFixed(2)}</TableCell>
                        </TableRow>
                      ))}
                    </TableBody>
                  </Table>
                </Typography>
              </CardContent>
            </Card>
          </Grid2>
        </Grid2>
      </div>
    </>
  );
}

export default Home;
