import React from 'react';
import './MainPage.css';

const MainPage = ({ onLogout }) => {
    const email = 'user@example.com'; // You can set this based on your authentication response

    return (
        <div className="main-container">
            <p>Welcome, {email}!</p>
            <button onClick={onLogout}>Logout</button>
        </div>
    );
};

export default MainPage;