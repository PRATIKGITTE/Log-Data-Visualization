/*import React, { Component } from 'react';
import { Link, useNavigate } from 'react-router-dom'; // Import useNavigate from react-router-dom
import './Auth.css'; // Use a common CSS file for authentication components

class Login extends Component {
  handleLogin = (event) => {
    event.preventDefault();
    // Perform your login logic here
    const isAuthenticated = true; // Assume login is successful for now

    if (isAuthenticated) {
      this.props.navigate('/app'); // Redirect to the main app
    }
  };

  render() {
    return (
      <div className="auth-wrapper">
        <div className="auth-inner">
          <form onSubmit={this.handleLogin}>
            <h3>Sign In</h3>
            <div className="mb-3">
              <label>Email address</label>
              <input
                type="email"
                className="form-control"
                placeholder="Enter email"
                required
              />
            </div>
            <div className="mb-3">
              <label>Password</label>
              <input
                type="password"
                className="form-control"
                placeholder="Enter password"
                required
              />
            </div>
            <div className="mb-3">
              <div className="custom-control custom-checkbox">
                <input
                  type="checkbox"
                  className="custom-control-input"
                  id="customCheck1"
                />
                <label className="custom-control-label" htmlFor="customCheck1">
                  Remember me
                </label>
              </div>
            </div>
            <div className="d-grid">
              <button type="submit" className="btn btn-primary">
                Submit
              </button>
            </div>
            <p className="forgot-password text-right">
              Forgot <Link to="/forgot-password">password?</Link>
            </p>
          </form>
        </div>
      </div>
    );
  }
}

// Named functional wrapper to use the useNavigate hook
function LoginWrapper(props) {
  const navigate = useNavigate();
  return <Login {...props} navigate={navigate} />;
}

export default LoginWrapper;
*/


/*2
// src/component/Login.js

import React, { Component } from 'react';
import { Link, useNavigate } from 'react-router-dom'; // Import useNavigate from react-router-dom
import './Auth.css'; // Use a common CSS file for authentication components

class Login extends Component {
  handleLogin = (event) => {
    event.preventDefault();
    // Perform your login logic here
    const isAuthenticated = true; // Assume login is successful for now

    if (isAuthenticated) {
      this.props.navigate('/app'); // Redirect to the main app
    }
  };

  render() {
    return (
      <>
        <div className="video-background">
          <video autoPlay loop muted>
            <source src="./bg.mp4" type="video/mp4" />
            Your browser does not support the video tag.
          </video>
        </div>
        <div className="auth-wrapper">
          <div className="auth-inner">
            <form onSubmit={this.handleLogin}>
              <h3>Sign In</h3>
              <div className="mb-3">
                <label>Email address</label>
                <input
                  type="email"
                  className="form-control"
                  placeholder="Enter email"
                  required
                />
              </div>
              <div className="mb-3">
                <label>Password</label>
                <input
                  type="password"
                  className="form-control"
                  placeholder="Enter password"
                  required
                />
              </div>
              <div className="mb-3">
                <div className="custom-control custom-checkbox">
                  <input
                    type="checkbox"
                    className="custom-control-input"
                    id="customCheck1"
                  />
                  <label className="custom-control-label" htmlFor="customCheck1">
                    Remember me
                  </label>
                </div>
              </div>
              <div className="d-grid">
                <button type="submit" className="btn btn-primary">
                  Submit
                </button>
              </div>
              <p className="forgot-password text-right">
                Forgot <Link to="/forgot-password">password?</Link>
              </p>
            </form>
          </div>
        </div>
      </>
    );
  }
}

// Named functional wrapper to use the useNavigate hook
function LoginWrapper(props) {
  const navigate = useNavigate();
  return <Login {...props} navigate={navigate} />;
}

export default LoginWrapper;
*/



import React, { Component } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './Auth.css';

class Login extends Component {
  constructor(props) {
    super(props);
    this.state = {
      email: '',
      password: '',
      showPassword: false, // State to track password visibility
    };
  }

  handleChange = (event) => {
    this.setState({ [event.target.name]: event.target.value });
  };

  toggleShowPassword = () => {
    this.setState((prevState) => ({ showPassword: !prevState.showPassword }));
  };

  handleLogin = (event) => {
    event.preventDefault();
    const { email, password } = this.state;
    const userData = { email, password };

    fetch('http://localhost:8081/database/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(userData),
    })
      .then((response) => response.json())
      .then((data) => {
        if (data) {
          this.props.navigate('/app');
        } else {
          this.setState({ error: 'Invalid email or password' });
        }
      })
      .catch((error) => {
        console.error(error);
        this.setState({ error: 'Login failed. Please try again.' });
      });
  };

  render() {
    return (
      <>
        <div className="video-background">
          <video autoPlay loop muted>
            <source src="./bg.mp4" type="video/mp4" />
            Your browser does not support the video tag.
          </video>
        </div>
        <div className="auth-wrapper">
          <div className="auth-inner">
            <form onSubmit={this.handleLogin}>
              <h3>Sign In</h3>
              <div className="mb-3">
                <label>Email address</label>
                <input
                  type="email"
                  className="form-control"
                  placeholder="Enter email"
                  name="email"
                  value={this.state.email}
                  onChange={this.handleChange}
                  required
                />
              </div>
              <div className="mb-3">
                <label>Password</label>
                <div className="input-group">
                  <input
                    type={this.state.showPassword ? 'text' : 'password'}
                    className="form-control"
                    placeholder="Enter password"
                    name="password"
                    value={this.state.password}
                    onChange={this.handleChange}
                    required
                  />
                  <div className="input-group-append">
                    <span
                      className="input-group-text"
                      onClick={this.toggleShowPassword}
                      style={{ cursor: 'pointer' }}
                    >
                      {this.state.showPassword ? '👁️' : '🙈'}
                    </span>
                  </div>
                </div>
              </div>
              <div className="mb-3">
                <div className="custom-control custom-checkbox">
                  <input
                    type="checkbox"
                    className="custom-control-input"
                    id="customCheck1"
                  />
                  <label className="custom-control-label" htmlFor="customCheck1">
                    Remember me
                  </label>
                </div>
              </div>
              <div className="d-grid">
                <button type="submit" className="btn btn-primary">
                  Submit
                </button>
              </div>
              <p className="forgot-password text-right">
                Forgot <Link to="/forgot-password">password?</Link>
              </p>
              {this.state.error && (
                <div className="alert alert-danger">{this.state.error}</div>
              )}
            </form>
          </div>
        </div>
      </>
    );
  }
}

function LoginWrapper(props) {
  const navigate = useNavigate();
  return <Login {...props} navigate={navigate} />;
}

export default LoginWrapper;
