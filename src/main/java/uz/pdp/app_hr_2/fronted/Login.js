import React, { useState } from 'react';
import { useHistory } from 'react-router-dom';
import { login } from '../services/authService';

const Login = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const history = useHistory();

    const handleSubmit = async (e) => {
        e.preventDefault();
        const response = await login(email, password);
        if (response.success) {
            const role = response.role;
            switch (role) {
                case 'ROLE_DIRECTOR':
                    history.push('/director/dashboard');
                    break;
                case 'ROLE_HR_MANAGER':
                    history.push('/hr/dashboard');
                    break;
                case 'ROLE_MANAGER':
                    history.push('/manager/dashboard');
                    break;
                case 'ROLE_EMPLOYEE':
                    history.push('/employee/dashboard');
                    break;
                default:
                    history.push('/');
            }
        } else {
            setError(response.message);
        }
    };

    return (
        <div>
            <h2>Login</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Email:</label>
                    <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} />
                </div>
                <div>
                    <label>Password:</label>
                    <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
                </div>
                {error && <p>{error}</p>}
                <button type="submit">Login</button>
            </form>
        </div>
    );
};

export default Login;
