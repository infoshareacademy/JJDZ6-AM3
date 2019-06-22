import axios from 'axios';

const client = axios.create({
    baseURL: `/rest`,
    timeout: 10000,
    headers: {
        Accept: 'application/json'
    }
});

export const goTo = (path) => {
    location.href = `http://localhost:8080/${path}`;
};

const Status = {
    BAD_REQUEST: 400,
    VALIDATION_ERROR: 422,
    NOT_FOUND: 404,
    SERVER_ERROR: 500
};

const throwError = (status, message, fieldErrors) => {
    const error = new Error(message);
    error.status = status;
    error.fieldErrors = fieldErrors;
    throw error;
};

const handleError = (error) => {
    const { response } = error;
    if (response) {
        const { status } = response;
        switch (status) {
            case Status.NOT_FOUND:
            case Status.BAD_REQUEST:
                throwError(status, response.data.message);
                break;
            case Status.VALIDATION_ERROR:
                throwError(
                    status,
                    'Validation error',
                    response.data
                );
                break;
            case Status.SERVER_ERROR:
                if (response.data && response.data.message) {
                    throwError(status, response.data.message);
                }
                break;
            default:
                console.error('Unhandled error ', error);
                throw error;
        }
    } else if (error.code === 'ECONNABORTED') {
        console.error(`Connection failed:${error}`);
        throw new Error(
            `Connection to server was canceled/aborted: "${error}".`
        );
    } else {
        console.error(`Unhandled error:${error}`);
        throw new Error(
            'Unknown error while performing this action, please try again.'
        );
    }
};

export const getTasks = () => client.get('/tasks');
export const getTask = (id) => client.get(`/tasks/${id}`);
export const createTask = (params) => client.post('/tasks', { ...params }).catch(handleError);
export const deleteTask = (id) => client.delete(`/tasks/${id}`).catch(handleError);
export const updateTask = (id, params) => client.put(`/tasks/${id}`, { ...params }).catch(handleError);

export const getTypes = () => client.get('/tasks/types');

export const getUsers = () => client.get('/users');
export const getUser = (id) => client.get(`/users/${id}`);
export const getUserRoles = (id) => client.get(`/users/roles`);
export const createUser = (params) => client.post('/users', { ...params }).catch(handleError);
export const deleteUser = (id) => client.delete(`/users/${id}`).catch(handleError);
export const updateUser = (id, params) => client.put(`/users/${id}`, { ...params }).catch(handleError);

export const getProjects = () => client.get('/projects');
export const getProject = (id) => client.get(`/projects/${id}`);
export const getBacklogForProject = (id) => client.get(`/projects/${id}/backlog`);
export const getSprintsForProject = (id) => client.get(`/projects/${id}/sprint`);
export const getUsersForProject = (id) => client.get(`/projects/${id}/users`);
export const addUserToProject = ({ id, user }) => client.post(`/projects/${id}/user`, { ...user });
export const createProject = (params) => client.post('/projects', { ...params }).catch(handleError);
export const deleteProject = (id) => client.delete(`/projects/${id}`).catch(handleError);
export const updateProject = (id, params) => client.put(`/projects/${id}`, { ...params }).catch(handleError);
