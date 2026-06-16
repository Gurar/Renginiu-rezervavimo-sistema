import axios from "axios";

const baseUrl = '/api/';

const options = {
    baseURL: baseUrl
};

export const $api = axios.create(options);

export const $host = axios.create(options);

$api.interceptors.request.use((config) => {
    const token = localStorage.getItem('token');
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
});