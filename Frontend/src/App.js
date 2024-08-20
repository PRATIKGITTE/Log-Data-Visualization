import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import Home from './component/Home';
import Navbar from './component/Navbar';
import LogChart from './component/LogChart';
import Login from './component/Login';
import SignUp from './component/Signup';
import ForgotPassword from './component/ForgotPassword';
import Header from './component2/Header';
import Body from './component2/Body';
import { FileContextProvider } from './component2/FileContext.js';

function MainApp() {
  return (
    <>
      <Header />
      <FileContextProvider>
        <Body />
      </FileContextProvider>
    </>
  );
}

function App() {
  return (
    <Router>
      <div className="App">
        <Navbar />
        <div className="container mt-3">
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/chart" element={<LogChart />} />
            <Route path="/login" element={<Login />} />
            <Route path="/forgot-password" element={<ForgotPassword />} />
            <Route path="/signup" element={<SignUp />} />
            <Route path="/app" element={<MainApp />} /> {/* Route to the main app */}
          </Routes>
        </div>
      </div>
    </Router>
  );
}

export default App;
