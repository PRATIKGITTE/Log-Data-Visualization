// import React from 'react';
// import { useNavigate } from 'react-router-dom';
// import axios from 'axios';
// import './Auth.css';

// export default function ForgotPassword() {
//   const navigate = useNavigate();

//   const handleSubmit = async (e) => {
//     e.preventDefault();
    
//     const email = e.target.email.value;
    
//     try {
//       {/*const response = await axios.get(`http://localhost:8081/database/forgot-password/${encodeURIComponent(email)}*/}
//       const response = await axios.get(`http://localhost:8081/database/forgot-password/${email}}`, 
      
//       {
//         headers: {
//           'Content-Type': 'application/json',
//         },
//       });

//       if (response.data.success) {
//         alert(response.data.message);
//         navigate('/Signup'); 
//       } else {
//         alert(response.data.message);
//       }
//     } catch (error) {
//       if (error.response) {
//         alert(`Error: ${error.response.data.message || 'Something went wrong.'}`);
//       } else {
//         alert('Network error. Please try again later.');
//       }
//     }
//   };

//   return (
//     <div className="auth-wrapper">
//       <div className="auth-inner">
//         <form onSubmit={handleSubmit}>
//           <h3>Forgot Password</h3>
//           <div className="mb-3">
//             <label>Email address</label>
//             <input
//               type="email"
//               name="email"
//               className="form-control"
//               placeholder="Enter your email"
//               required
//             />
//           </div>
//           <div className="d-grid">
//             <button type="submit" className="btn btn-primary">
//               Check Email
//             </button>
//           </div>
//         </form>
//       </div>
//     </div>
//   );
// }


import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import './Auth.css';

export default function ForgotPassword() {
  const navigate = useNavigate();
  const [errorMessage, setErrorMessage] = useState(''); // State to manage the error message

  const handleSubmit = async (e) => {
    e.preventDefault();
    
    const email = e.target.email.value;

    try {
      // Make a GET request to check if the email exists
      const response = await axios.get(`http://localhost:8081/database/forgot-password/${encodeURIComponent(email)}`, {
        headers: {
          'Content-Type': 'application/json',
        },
      });

      // Check the response and navigate or set an error message
      if (response.status === 200) {
        alert('Email found! Redirecting to the Sign Up page...');
        navigate('/Signup'); 
      }
    } catch (error) {
      if (error.response && error.response.status === 404) {
        setErrorMessage('The email address does not exist in our records.');
      } else {
        setErrorMessage('An error occurred. Please try again later.');
      }
    }
  };

  return (
    <div className="auth-wrapper">
      <div className="video-background">
        <video autoPlay loop muted>
          <source src="./bg.mp4" type="video/mp4" />
          Your browser does not support the video tag.
        </video>
      </div>
      <div className="auth-inner">
        <form onSubmit={handleSubmit}>
          <h3>Forgot Password</h3>
          <div className="mb-3">
            <label>Email address</label>
            <input
              type="email"
              name="email"
              className="form-control"
              placeholder="Enter your email"
              required
            />
          </div>
          <div className="d-grid">
            <button type="submit" className="btn btn-primary">
              Check Email
            </button>
          </div>
          {errorMessage && ( 
            <div className="error-message" style={{ color: 'red', marginTop: '10px' }}>
              {errorMessage}
            </div>
          )}
        </form>
      </div>
    </div>
  );
}
