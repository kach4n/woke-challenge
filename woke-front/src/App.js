import React, { useState } from 'react';
import Login from './components/Login';
import MainPage from './components/MainPage';
import Cookies from 'js-cookie';

function App() {
    const [isLoggedIn, setIsLoggedIn] = useState(!!Cookies.get('auth_token'));
    const [userData, setUserData] = useState('');


    const handleLogin = (userData) => {
        setUserData(userData);
        setIsLoggedIn(true);
    };

    const handleLogout = () => {
        Cookies.remove('auth_token');
        setIsLoggedIn(false);
        setUserData(null);
    };

    return (
        <div>
            {isLoggedIn ? (
                <MainPage userData={userData} onLogout={handleLogout} />
            ) : (
                <Login onLogin={handleLogin} />
            )}
        </div>
    );
}

export default App;