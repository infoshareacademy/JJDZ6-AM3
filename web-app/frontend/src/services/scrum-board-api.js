import axios from 'axios';
import router from '../routes/router'

function headers() {
    const token = localStorage.getItem('token');
    const authHeader = token ? { 'Authorization': 'Bearer ' + token } : {};
    return {
        headers: {
            ...authHeader,
            'Content-Type': 'application/json'
        }
    }
}

const client = axios.create({
    baseURL: `/rest`,
    timeout: 10000
});

const Status = {
    BAD_REQUEST: 400,
    VALIDATION_ERROR: 422,
    NOT_FOUND: 404,
    SERVER_ERROR: 500,
    NOT_AUTHORIZED: 401
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
            case Status.NOT_AUTHORIZED:
                router.push({ path: '/login' });
                throwError(status, response.data.message);
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

export const getTasks = ({ query = "" }) => client.get('/tasks', { params: { query } }).catch(handleError);
export const getTask = (id) => client.get(`/tasks/${id}`,headers()).catch(handleError);
export const createTask = (params) => client.post('/tasks', { ...params },headers()).catch(handleError);
export const deleteTask = (id) => client.delete(`/tasks/${id}`,headers()).catch(handleError);
export const updateTask = (id, task) => client.put(`/tasks/${id}`, task,headers()).catch(handleError);

export const getTypes = () => client.get('/tasks/types');
export const getStatuses = () => client.get('/tasks/statuses');
export const getPriorities = () => client.get('/tasks/priorities');

export const getUsers = ({ query = "" }) => client.get('/users', { params: { query } }).catch(handleError);
export const getUser = (id) => client.get(`/users/${id}`,headers()).catch(handleError);
export const getUserRoles = (id) => client.get(`/users/roles`);
export const createUser = (params) => client.post('/users', { ...params },headers()).catch(handleError);
export const deleteUser = (id) => client.delete(`/users/${id}`,headers()).catch(handleError);
export const updateUser = (id, params) => client.put(`/users/${id}`, { ...params },headers()).catch(handleError);

export const createSprint = (params) => client.post('/sprints', { ...params },headers()).catch(handleError);
export const getSprint = (id) => client.get(`/sprints/${id}`,headers()).catch(handleError);
export const addTasksToSprint = (tasks, id) => client.post(`/sprints/${id}/tasks`, { tasks },headers()).catch(handleError);


export const getProjects = () => client.get('/projects',headers()).catch(handleError);
export const getProject = (id) => client.get(`/projects/${id}`,headers()).catch(handleError);
export const getBacklogForProject = (id) => client.get(`/projects/${id}/backlog`,headers()).catch(handleError);
export const getSprintsForProject = (id) => client.get(`/projects/${id}/sprint`,headers()).catch(handleError);
export const getUsersForProject = (id) => client.get(`/projects/${id}/users`,headers()).catch(handleError);
export const addUserToProject = ({ id, user }) => client.post(`/projects/${id}/user`, { ...user },headers()).catch(handleError);
export const createProject = (params) => client.post('/projects', { ...params },headers()).catch(handleError);
export const deleteProject = (id) => client.delete(`/projects/${id}`,headers()).catch(handleError);
export const updateProject = (id, params) => client.put(`/projects/${id}`, { ...params },headers()).catch(handleError);
