/*
import React from 'react';
import Info from './Info';


const Home = () => (
  <div>
    <h1>Welcome to the  Project</h1>
    <Info />  {/* Include the Info component here }*/
    /*<img src={`${process.env.PUBLIC_URL}/LogImage.webp`} alt="Sorry" className="img-fluid mb-4"style={{ width: `${1000}px`, height: `${500}px`}}/>
  </div>
);

export default Home;
*/

/*
import React from 'react';
import Info from './Info'; // Import the Info component
import './Home.css'; // Import the CSS file for styling

const Home = () => (
  <>
    <div className="video-background">
      <video autoPlay loop muted>
        <source src="./bg.mp4" type="video/mp4" />
        Your browser does not support the video tag.
      </video>
    </div>
    <div className="home-content">
      <h1 className="welcome-title">Welcome To Our Project</h1>
      <h1 className="info-title">Log Data Visualization</h1>
      <div className="home-buttons">
        <button className="home-button" onClick={() => document.getElementById('introduction').scrollIntoView({ behavior: 'smooth' })}>Introduction</button>
        <button className="home-button" onClick={() => document.getElementById('challenges').scrollIntoView({ behavior: 'smooth' })}>Challenges</button>
        <button className="home-button" onClick={() => document.getElementById('solutions').scrollIntoView({ behavior: 'smooth' })}>Solutions</button>
      </div>
      <Info /> {/* Include the Info component here }/*
    </div>
  </>
);

export default Home;
*/
// src/component/Home.js
import React, { useState } from 'react';
//import Info from './Info'; // Import the Info component
import './Home.css'; // Import the CSS file for styling

const Home = () => {
  const [visibleSection, setVisibleSection] = useState(null);

  const handleButtonClick = (section) => {
    setVisibleSection(section);
  };

  return (
    <>
      <div className="video-background">
        <video autoPlay loop muted>
          <source src="./bg.mp4" type="video/mp4" />
          Your browser does not support the video tag.
        </video>
      </div>
      <div className="home-content">
        <h1 className="welcome-title">Welcome To Our Project</h1>
        <h1 className="info-title log-data">Log Data Visualization</h1>
        <div className="home-buttons">
          <button className="home-button" onClick={() => handleButtonClick('introduction')}>Introduction</button>
          <button className="home-button" onClick={() => handleButtonClick('challenges')}>Challenges</button>
          <button className="home-button" onClick={() => handleButtonClick('solutions')}>Solutions</button>
        </div>
        <div className="info-container">
          {visibleSection === 'introduction' && (
            <>
              <h2 id="introduction" className="info-title intro">Introduction</h2>
              <p className="info-description">
                Data visualization is a powerful technique that transforms complex data sets into graphical representations, making it easier to understand, analyze, and communicate information. By using various chart types, such as line charts, bar charts, and pie charts, data visualization helps users quickly grasp trends, patterns, and outliers in their data.
              </p>
            </>
          )}
          {visibleSection === 'challenges' && (
            <>
              <h2 id="challenges" className="info-title challenges">The Challenges</h2>
              <p className="info-description">
                Raw data can be unclear to those unfamiliar with it.<br />
                Effective visualization is needed to communicate data significance.<br />
              </p>
            </>
          )}
          {visibleSection === 'solutions' && (
            <>
              <h2 id="solutions" className="info-title solutions">The Solutions</h2>
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
            </>
          )}
        </div>
      </div>
    </>
  );
};

export default Home;
