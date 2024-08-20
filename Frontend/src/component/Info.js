/*
// Info.js
import React from 'react';
import './Info.css'; // Create a CSS file for Info-specific styles


const Info = () => (
  <div className="info-container">
    <h2 className="info-title">Log Data Visualization</h2>
    <h4 className="info-subtitle">  Introduction
    </h4>
    <p className="info-description">
      Data visualization is a powerful technique that transforms complex data sets into graphical representations, making it easier to understand, analyze, and communicate information. By using various chart types, such as line charts, bar charts, and pie charts, data visualization helps users quickly grasp trends, patterns, and outliers in their data.
    </p>
    <p className="info-description">

      <b>
        The Challenge
        <br />

      </b>

      Raw data can be unclear to those unfamiliar with it.<br />
      Effective visualization is needed to communicate data significance.<br />
    </p>
    <p className="info-description">

      <b>
        The Solutions
        <br />

      </b>
    </p>
    <ul className="info-list">
      <li>
        <i className="fas fa-check-circle text-success"></i> A web application for log data visualization simplifies the process.
      </li>
      <li>
        <i className="fas fa-check-circle text-success"></i> Users upload CSV files, choose a chart type, and generate graphs with a single click.
      </li>
      <li>
        <i className="fas fa-check-circle text-success"></i> This method saves time and reduces the need for technical skills, making visualization more accessible.
      </li>
    </ul>
  </div>
);


export default Info;

*/
import React from 'react';
import './Info.css'; // Create a CSS file for Info-specific styles

const Info = () => (
  <div className="info-container">
    <h2 id="introduction" className="info-title">Introduction</h2>
    <p className="info-description">
      Data visualization is a powerful technique that transforms complex data sets into graphical representations, making it easier to understand, analyze, and communicate information. By using various chart types, such as line charts, bar charts, and pie charts, data visualization helps users quickly grasp trends, patterns, and outliers in their data.
    </p>
    <h2 id="challenges" className="info-title">The Challenge</h2>
    <p className="info-description">
      Raw data can be unclear to those unfamiliar with it.<br />
      Effective visualization is needed to communicate data significance.<br />
    </p>
    <h2 id="solutions" className="info-title">The Solutions</h2>
    <ul className="info-list">
      <li>
        <i className="fas fa-check-circle text-success"></i> A web application for log data visualization simplifies the process.
      </li>
      <li>
        <i className="fas fa-check-circle text-success"></i> Users upload CSV files, choose a chart type, and generate graphs with a single click.
      </li>
      <li>
        <i className="fas fa-check-circle text-success"></i> This method saves time and reduces the need for technical skills, making visualization more accessible.
      </li>
    </ul>
  </div>
);

export default Info;
