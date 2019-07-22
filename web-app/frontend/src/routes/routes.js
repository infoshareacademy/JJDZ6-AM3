import Projects from '../components/Projects'
import Login from '../components/Login'
import StartSprint from '../components/StartSprint'
import Register from '../components/Register'
import Search from '../components/Search'
import ProjectActions from '../components/ProjectActions'
import addProjectDialog from '../components/AddProjectDialog'
import Backlog from '../components/Backlog'
import Sprints from '../components/Sprints'
import Sprint from '../components/Sprint'
import ProjectSidebar from '../components/ProjectSidebar'
import Tasks from '../components/Tasks'
import Project from '../components/Project'
import User from '../components/User'
import Users from '../components/Users'
import CreateIssueDialog from '../components/CreateIssueDialog'
import CreateUserDialog from '../components/CreateUserDialog'
import AddUserToProjectDialog from '../components/AddUserToProjectDialog'
import CreateSprint from '../components/CreateSprint'

const createSprint = {
    path: 'create-sprint',
    component: CreateSprint,
    props: route => ({
        projectId: parseInt(route.params.projectId, 10) || undefined
    })
};

const startSprint = {
    path: 'start/:id',
    component: StartSprint,
    props: route => ({
        projectId: parseInt(route.params.projectId, 10) || undefined
    })
};

const backlog = {
    path: 'backlog',
    component: Backlog,
    children: [createSprint, startSprint],
    props: route => ({
        projectId: parseInt(route.params.projectId, 10) || undefined
    })
};

const sprints = {
    path: 'sprints',
    component: Sprints,
    props: route => ({
        projectId: parseInt(route.params.projectId, 10) || undefined
    })
};

const sprint = {
    path: 'sprints/:id',
    component: Sprint,
    props: route => ({
        sprintId: parseInt(route.params.id, 10) || undefined
    })
};

const addUserToProject = {
    path: 'add',
    component: AddUserToProjectDialog,
    props: route => ({
        projectId: parseInt(route.params.projectId, 10) || undefined
    })
};

const users = {
    path: 'users',
    component: Users,
    children: [addUserToProject],
    props: route => ({
        projectId: parseInt(route.params.projectId, 10) || undefined
    })
};

const addProject = {
    path: 'add',
    component: addProjectDialog
};

export const routes = [
    {
        path: '/projects',
        components: { default: Projects, sidebar: ProjectActions },
        children: [addProject],
        meta: { authorize: true }
    },
    { path: '/tasks', component: Tasks, meta: { authorize: true } },
    {
        path: '/projects/:projectId',
        components: {
            default: Project,
            sidebar: ProjectSidebar,
        },
        props: {
            default: route => ({
                projectId: parseInt(route.params.projectId, 10) || undefined
            }),
            sidebar: route => ({
                projectId: parseInt(route.params.projectId, 10) || undefined
            })
        },
        children: [backlog, sprints, users, sprint]
    },
    { path: '/users/:id', component: User, props: (route) => ({ userId: parseInt(route.params.id, 10) }) },
    {
        name: 'create-issue',
        path: '/create-issue',
        components: { default: CreateIssueDialog }
    },
    { path: '/create-user', component: CreateUserDialog },
    { path: '/search', component: Search },
    { path: '/login', component: Login },
    { path: '/register', component: Register },
];
