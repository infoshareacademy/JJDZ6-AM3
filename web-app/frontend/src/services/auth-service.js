import axios from 'axios';

const client = axios.create({
    baseURL: `/rest`,
    timeout: 10000
});

export const login = async (username, password) => {
    const { headers } = await client.post('/authorization/login', { username, password });
    const [, token] = headers.authorization.split(" ");
    localStorage.setItem('token', token);
    return token;
};

export const register = async (username, password) => {
    const { headers } = await client.post('/authorization/register', { username, password });
    const [, token] = headers.authorization.split(" ");
    localStorage.setItem('token', token);
    return token;
};

export const logout = () => {
    // remove user from local storage to log user out
    localStorage.removeItem('token');
};
