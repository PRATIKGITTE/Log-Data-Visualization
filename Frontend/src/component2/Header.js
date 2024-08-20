
/*
import React from 'react';
// import '../style/Header.scss';

/**
 * Header component to display the navigation bar and project title.
 * 
 * @returns {JSX.Element} The Header component containing a navigation bar with a project title.
 */
/*
function Header() {
  return (
    <nav>
      <div className="heading">
        <h1>LOG DATA VISUALIZATION PROJECT</h1>
      </div>
    </nav>
  );
}

export default Header;
*/

import React from 'react';
import './Header.css';

function Header() {
  return (
    <div className="header-container">
      <div className="video-background">
        <video autoPlay loop muted>
          <source src="./bg.mp4" type="video/mp4" />
          Your browser does not support the video tag.
        </video>
      </div>
      <nav className="header-nav">
        {/* Your header content if any */}
      </nav>
      <div className="heading-container">
        <h1>LOG DATA VISUALIZATION PROJECT</h1>
      </div>
    </div>
  );
}

export default Header;

