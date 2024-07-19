import axios from 'axios';

export const login = async (email, password) => {
    const response = await axios.post('http://localhost:8080/api/auth/login', { email, password });
    if (response.data.success) {
        localStorage.setItem('token', response.data.token);
        localStorage.setItem('role', response.data.role);
    }
    return response.data;
};

export const getToken = () => localStorage.getItem('token');
export const getUserRole = () => localStorage.getItem('role');

export const logout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('role');
};
