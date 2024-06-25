import React, { useState } from 'react';
import './MainPage.css';

const MainPage = ({ userData, onLogout }) => {
    const [sentToCompany, setSentToCompany] = useState(null);

    const handleSendCurriculum = (companyName) => {
        setSentToCompany(companyName);
    };

    return (
        <div className="main-container">
            <div className="user-info">
                <h2>Área Restrita do usuário {userData.name}</h2>
                <p><strong>Nome completo:</strong> {userData.name}</p>
                <p><strong>Email:</strong> {userData.email}</p>
                <p><strong>Telefone:</strong> {userData.phoneNumber}</p>
                <p><strong>Data de nascimento:</strong> {userData.birthDate}</p>
            </div>
            <div className="company-options">
                <h3>Enviar currículo para:</h3>
                <button onClick={() => handleSendCurriculum('Amazon')}>
                    Amazon
                </button>
                <button onClick={() => handleSendCurriculum('Microsoft')}>
                    Microsoft
                </button>
                <button onClick={() => handleSendCurriculum('Google')}>
                    Google
                </button>
            </div>
            <div className="sent-status">
                {sentToCompany && (
                    <p>Currículo enviado para: {sentToCompany}</p>
                )}
            </div>
            <button className="logout-button" onClick={onLogout}>Logout</button>
        </div>
    );
};

export default MainPage;
