import React from 'react';
// import '../style/Output.scss';
import { useFileContext } from './FileContext';

/**
 * Output component displays the generated chart image and provides a download link.
 * 
 * @returns {JSX.Element} The Output component with image display and download link.
 */
function Output() {
  const { imageReceived } = useFileContext();  // Retrieve the image URL from context
  console.log(imageReceived);  // Log the image URL for debugging

  return (
    <section className="top-output">
      <div>
        {/* Display the chart image if the URL is available */}
        {imageReceived && <img src={imageReceived} alt="Graph" />}
      </div>
      <div>
        {/* Provide a download link for the chart image */}
        {imageReceived && (
          <a href={imageReceived} download="image-download.jpeg">
            Download
          </a>
        )}
      </div>
    </section>
  );
}

export default Output;
