import React from 'react';
import './Upload.css';
import { useFileContext } from './FileContext';

/**
 * Upload component provides a file input field for users to upload files.
 * The selected file is stored in the FileContext for further processing.
 * 
 * @returns {JSX.Element} The Upload component with a file input field.
 */
function Upload() {
  // Destructure the setFileUploaded function from FileContext
  const { setFileUploaded } = useFileContext();

  return (
    <section className="top">
        {/* Heading for the upload section */}
        <section className="top-heading">
          <h2>Upload Files</h2>
        </section>

        {/* File upload form */}
        <section className="top-upload">
          <form>
            <input 
              type="file" 
              name="file" 
              onChange={(e) => {
                e.preventDefault();
                // Set the selected file in FileContext
                setFileUploaded(e.target.files[0]);
                console.log(e.target.files); // Log the selected file(s) for debugging
              }}
            />
          </form>
        </section>
    </section>
  )
}

export default Upload;
