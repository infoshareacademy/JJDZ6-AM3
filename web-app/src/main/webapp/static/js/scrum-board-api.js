const client = axios.create({
    baseURL: 'http://localhost:8080/rest',
    timeout: 10000,
    headers: {
        Accept: 'application/json'
    }
});

const goTo = (path) => {
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

const createTask = async (params) => client.post('/tasks', { ...params }).catch(handleError);
const deleteTask = async (id) => client.delete(`/tasks/${id}`).catch(handleError);
const updateTask = async (id, params) => client.put(`/tasks/${id}`, { ...params }).catch(handleError);

const createUser = async (params) => client.post('/users', { ...params }).catch(handleError);
const deleteUser = async (id) => client.delete(`/users/${id}`).catch(handleError);
const updateUser = async (id, params) => client.put(`/users/${id}`, { ...params }).catch(handleError);

const createProject = async (params) => client.post('/projects', { ...params }).catch(handleError);
const deleteProject = async (id) => client.delete(`/projects/${id}`).catch(handleError);
const updateProject = async (id, params) => client.put(`/projects/${id}`, { ...params }).catch(handleError);

