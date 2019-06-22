import Projects from '../components/Projects'
import ProjectActions from '../components/ProjectActions'
import addProjectDialog from '../components/AddProjectDialog'
import Backlog from '../components/Backlog'
import Sprints from '../components/Sprints'
import ProjectSidebar from '../components/ProjectSidebar'
import Tasks from '../components/Tasks'
import Project from '../components/Project'
import User from '../components/User'
import Users from '../components/Users'
import CreateIssueDialog from '../components/CreateIssueDialog'
import CreateUserDialog from '../components/CreateUserDialog'
import AddUserToProjectDialog from '../components/AddUserToProjectDialog'


const backlog = {
    path: 'backlog',
    component: Backlog,
    props: route => ({
        projectId: parseInt(route.params.id, 10) || undefined
    })
};
const sprints = {
    path: 'sprints',
    component: Sprints,
    props: route => ({
        projectId: parseInt(route.params.id, 10) || undefined
    })
};

const addUserToProject = {
    path: 'add',
    component: AddUserToProjectDialog,
    props: route => ({
        projectId: parseInt(route.params.id, 10) || undefined
    })
};

const users = {
    path: 'users',
    component: Users,
    children: [addUserToProject],
    props: route => ({
        projectId: parseInt(route.params.id, 10) || undefined
    })
};

const addProject = {
    path: 'add',
    component: addProjectDialog
};

export const routes = [
    { path: '/projects', components: { default: Projects, sidebar: ProjectActions }, children: [addProject] },
    { path: '/tasks', component: Tasks },
    {
        path: '/projects/:id',
        components: {
            default: Project,
            sidebar: ProjectSidebar,
        },
        props: {
            default: route => ({
                projectId: parseInt(route.params.id, 10) || undefined
            }),
            sidebar: route => ({
                projectId: parseInt(route.params.id, 10) || undefined
            })
        },
        children: [backlog, sprints, users]
    },
    { path: '/users/:id', component: User, props: (route) => ({ userId: parseInt(route.params.id, 10) }) },
    {
        name: 'create-issue',
        path: '/create-issue',
        components: { default: CreateIssueDialog }
    },
    { path: '/create-user', component: CreateUserDialog },
];
