
/*
import React from 'react';
import Selection from './Selection';
import Output from './Output';

/**
 * The Body component serves as a container for the Selection and Output components.
 * It applies styling from Body.scss to arrange the layout.
 *
 * @returns {JSX.Element} The rendered Body component.
 */
/*
function Body() {
  return (
    <section className="bod">
      {/* The Selection component handles user input for graph selection }
    /*  <Selection />
      
      {/* The Output component displays the generated graph based on user selection }
      /*<Output />
    </section>
  );
}

export default Body;
*/

import React from 'react';
import Selection from './Selection';
import Output from './Output';
import './Body.css'; // Ensure you import the CSS file

/**
 * The Body component serves as a container for the Selection and Output components.
 * It applies styling from Body.css to arrange the layout.
 *
 * @returns {JSX.Element} The rendered Body component.
 */
function Body() {
  return (
    <section className="bod">
      {/* The Selection component handles user input for graph selection */}
      <Selection />
      
      {/* The Output component displays the generated graph based on user selection */}
      <Output />
    </section>
  );
}

export default Body;
