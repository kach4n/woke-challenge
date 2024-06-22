import React, { useState } from 'react';
import './Login.css';

const Login = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [loggedIn, setLoggedIn] = useState(false);

  const handleLogin = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch('http://localhost:8080/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ username, password }),
      });

      if (!response.ok) {
        throw new Error('Invalid username or password');
      }

      const { token } = await response.json();

      // Store the token in localStorage or sessionStorage for example
      localStorage.setItem('token', token);

      setLoggedIn(true);
      alert('Login successful!');
      // You may want to redirect the user or perform additional actions upon successful login
    } catch (error) {
      console.error('Login error:', error);
      alert('Invalid username or password. Please try again.');
    }
  };

  const handleLogout = () => {
    localStorage.removeItem('token');
    setLoggedIn(false);
    setUsername('');
    setPassword('');
  };

  return (
    <div className="login-container">
      {!loggedIn ? (
        <form className="login-form" onSubmit={handleLogin}>
          <h2>Login</h2>
          <label>
            Username:
            <input
              type="text"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required
            />
          </label>
          <label>
            Password:
            <input
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </label>
          <button type="submit">Login</button>
        </form>
      ) : (
        <div className="logged-in-container">
          <p>Welcome, {username}!</p>
          <button onClick={handleLogout}>Logout</button>
        </div>
      )}
    </div>
  );
};

export default Login;
