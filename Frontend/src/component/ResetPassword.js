// ResetPassword.js
import React, { Component } from 'react';
import './Auth.css'; // Use the same CSS file

export default class ResetPassword extends Component {
  handleSubmit = (e) => {
    e.preventDefault();
    // Implement the logic to reset the password using the token
    // Example: fetch(`/api/reset-password?token=${this.props.token}`, { method: 'POST', body: JSON.stringify({ password: this.password.value }) });
  };

  render() {
    return (
      <div className="auth-wrapper">
        <div className="auth-inner">
          <form onSubmit={this.handleSubmit}>
            <h3>Reset Password</h3>
            <div className="mb-3">
              <label>New Password</label>
              <input
                type="password"
                className="form-control"
                placeholder="Enter new password"
                ref={(input) => (this.password = input)}
                required
              />
            </div>
            <div className="d-grid">
              <button type="submit" className="btn btn-primary">
                Reset Password
              </button>
            </div>
          </form>
        </div>
      </div>
    );
  }
}
