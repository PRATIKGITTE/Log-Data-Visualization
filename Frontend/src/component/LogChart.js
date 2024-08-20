/*import React, { useEffect, useState } from 'react';
import ReactApexChart from 'react-apexcharts';
import './LogChart.css';

const LogChart = () => {
  const [logData, setLogData] = useState([]);
  const [chartType, setChartType] = useState('line');

  useEffect(() => {
    fetch('/log.json')
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then(data => {
        console.log('Fetched log data:', data);
        setLogData(data);
      })
      .catch(error => {
        console.error('Error fetching log data:', error);
      });
  }, []);

  if (logData.length === 0) {
    return <p>Loading data...</p>;
  }

  const chartOptions = {
    chart: {
      type: chartType,
      height: 350,
    },
    xaxis: {
      categories: logData.map(log => new Date(log.timestamp).toLocaleString()),
    },
    yaxis: {
      title: {
        text: 'Status Code',
      },
    },
    tooltip: {
      shared: true,
      intersect: false,
    },
  };

  const series = [{
    name: 'Status Code',
    data: logData.map(log => log.status_code),
  }];

  const renderChart = () => {
    switch (chartType) {
      case 'line':
      case 'bar':
        return <ReactApexChart options={chartOptions} series={series} type={chartType} height={350} />;
      default:
        return <ReactApexChart options={chartOptions} series={series} type="line" height={350} />;
    }
  };

  return (
    <div className="chart-container">
      <h2>Log Data Visualization</h2>
      <div className="chart-controls">
        <label htmlFor="chartType">Select Chart Type: </label>
        <select id="chartType" value={chartType} onChange={(e) => setChartType(e.target.value)}>
          <option value="line">Line Chart</option>
          <option value="bar">Bar Chart</option>

        </select>
      </div>
      <div className="chart-content">
        {renderChart()}
      </div>
    </div>
  );
};
export default LogChart;
*/

import React, { useEffect, useState } from 'react';
import ReactApexChart from 'react-apexcharts';
import './LogChart.css';

const LogChart = () => {
  const [logData, setLogData] = useState([]);
  const [chartType, setChartType] = useState('line');

  useEffect(() => {
    fetch('/log.json')
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then(data => {
        console.log('Fetched log data:', data);
        setLogData(data);
      })
      .catch(error => {
        console.error('Error fetching log data:', error);
      });
  }, []);

  if (logData.length === 0) {
    return <p>Loading data...</p>;
  }

  const chartOptions = {
    chart: {
      type: chartType,
      height: 350,
    },
    xaxis: {
      categories: logData.map(log => new Date(log.timestamp).toLocaleString()),
    },
    yaxis: {
      title: {
        text: 'Status Code',
      },
    },
    tooltip: {
      shared: true,
      intersect: false,
    },
  };

  const series = [{
    name: 'Status Code',
    data: logData.map(log => log.status_code),
  }];

  const renderChart = () => {
    switch (chartType) {
      case 'line':
      case 'bar':
        return <ReactApexChart options={chartOptions} series={series} type={chartType} height={350} />;
      default:
        return <ReactApexChart options={chartOptions} series={series} type="line" height={350} />;
    }
  };

  return (
    <div className="logchart-wrapper">
      <div className="video-background">
        <video autoPlay loop muted>
          <source src="./bg.mp4" type="video/mp4" />
          Your browser does not support the video tag.
        </video>
      </div>
      <div className="chart-container">
        <h2>Log Data Visualization</h2>
        <div className="chart-controls">
          <label htmlFor="chartType">Select Chart Type: </label>
          <select id="chartType" value={chartType} onChange={(e) => setChartType(e.target.value)}>
            <option value="line">Line Chart</option>
            <option value="bar">Bar Chart</option>
          </select>
        </div>
        <div className="chart-content">
          {renderChart()}
        </div>
      </div>
    </div>
  );
};

export default LogChart;
