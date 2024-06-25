import React, { useState } from 'react';
import './Login.css';
import Cookies from 'js-cookie';


const Login = ({ onLogin }) => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleLogin = async (e) => {
        e.preventDefault();

        try {
            const response = await fetch('http://localhost:8080/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ email, password }),
                credentials: 'include'
            });

            if (!response.ok) {
                throw new Error('Email ou senha inválidos');
            }

            const { message } = await response.json();

            const token = Cookies.get('auth_token');
            const userResponse = await fetch('http://localhost:8080/userinfo', {
                method: 'GET',
                headers: {
                    'Authorization': `Bearer ${token}`,
                },
                credentials: 'include'
            });

            if (!userResponse.ok) {
                throw new Error('Falha ao obter dados do usuário');
            }

            const userData = await userResponse.json();

            onLogin(userData);
        } catch (error) {
            console.error('Erro no login:', error);
            alert('Usuário ou senha inválidos, tente novamente.');
        }
    };

    return (
        <div className="login-container">
            <form className="login-form" onSubmit={handleLogin}>
                <h2>Login</h2>
                <label>
                    Email:
                    <input
                        type="text"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
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
        </div>
    );
};

export default Login;