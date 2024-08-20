/*import React from 'react';
import Upload from './Upload';
import Options from './Options';
import styled from 'styled-components';

// Styled component for the section containing Upload and Options components
const SelectionSection = styled.section`
    display: flex;
    flex-direction: row;           // Arrange children in a horizontal row
    justify-content: space-around; // Distribute space evenly around children
    align-items: center;           // Center align children vertically
    flex: 30%;                     // Flex grow factor to occupy 30% of the available space
`;

/**
 * Selection component renders the Upload and Options components
 * within a styled section.
 * 
 * @returns {JSX.Element} The Selection component with Upload and Options.
 */
/*
function Selection() {
  return (
    <SelectionSection>
        <Upload/>   {/* Component for file upload functionality }
        <Options/>  {/* Component for selecting chart options }
    /* </SelectionSection>
  )
}

export default Selection;
*/
import React from 'react';
import Upload from './Upload';
import Options from './Options';
import './Upload.css';  // Import the existing Upload CSS
import './Options.css'; // Import the existing Options CSS

/**
 * Selection component renders the Upload and Options components
 * within a styled section.
 * 
 * @returns {JSX.Element} The Selection component with Upload and Options.
 */
function Selection() {
  return (
    <div className="section-container">
      <Upload />   {/* Component for file upload functionality */}
      <Options />  {/* Component for selecting chart options */}
    </div>
  );
}

export default Selection;
