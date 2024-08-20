import React, { useState } from 'react';
import { useFileContext } from './FileContext';
import './Options.css'; // Import the CSS file

/**
 * Options component allows the user to select the type of chart
 * and generate a chart based on the uploaded CSV file.
 * 
 * @returns {JSX.Element} The Options component with chart selection and file upload handling.
 */
function Options() {
  const { setChart, chart, fileUploaded, setImageReceived, setF } = useFileContext();
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  /**
   * Handles the click event to upload the file and generate the chart.
   * Sends a POST request with the file and selected chart type.
   */
  const handleClick = () => {
    if (!fileUploaded) {
      setError('No file uploaded');
      return;
    }

    setLoading(true);
    setError(null);

    let formData = new FormData();
    formData.append("csv", fileUploaded);
    formData.append("graphName", chart);

    fetch(`http://localhost:8081/file/upload`, {
      method: 'POST',
      body: formData
    })
      .then(response => {
        setLoading(false);
        if (response.ok) {
          return response.json();
        } else {
          throw new Error('Failed to upload file');
        }
      })
      .then((data) => {
        if (data.fileName) {
          setImageReceived(`http://localhost:8081/file/images/${data.fileName}`);
          setF(true);
        } else {
          setError('Invalid response format from server');
        }
      })
      .catch(error => {
        setLoading(false);
        setError(`Error uploading file: ${error.message}`);
      });
  }

  return (
    <section className="top-option">
      <h2>Select the type of chart</h2>
      {error && <p className="error-message">{error}</p>}
      <select
        id="charts"
        onChange={(e) => {
          e.preventDefault();
          const index = e.target.selectedIndex;
          const el = e.target.childNodes[index];
          const option = el.getAttribute('id');
          setChart(option);
        }}
      >
        <option id="line" value="Line Chart">Line Chart</option>
        <option id="pie" value="Pie Chart">Pie Chart</option>
        <option id="bar" value="Bar Chart">Bar Chart</option>
        <option id="histogram" value="Histogram">Histogram</option>
        <option id="scatter" value="Scatter Plot">Scatter Plot</option>
        <option id="area" value="Area Chart">Area Chart</option>
        <option id="bubble" value="Bubble Chart">Bubble Chart</option>
      </select>
      <button className="generate-button" onClick={handleClick} disabled={loading}>
        {loading ? 'Generating...' : 'Generate'}
      </button>
    </section>
  );
}

export default Options;
