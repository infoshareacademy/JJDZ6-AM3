import { login, logout, register } from '../services/auth-service';
import jsonwebtoken from "jsonwebtoken";

const token = localStorage.getItem('token');
const decoded = jsonwebtoken.decode(token);
const user = decoded && decoded.sub;

const initialState = user
    ? { status: { loggedIn: true }, user }
    : { status: {}, user: null };

export const authentication = {
    namespaced: true,
    state: initialState,
    actions: {
        async login({ dispatch, commit }, { username, password }) {
            commit('loginRequest', { username });
            try {
                const token = await login(username, password);
                commit('loginSuccess', token);
            }
            catch (error) {
                console.log(error);
                commit('loginFailure', error);
            }
        },
        async register({ dispatch, commit }, { username, password }) {
            commit('loginRequest', { username });
            try {
                const token = await register(username, password);
                commit('loginSuccess', token);
            }
            catch (error) {
                console.log(error);
                commit('loginFailure', error);
            }
        },
        logout({ commit }) {
            logout();
            commit('logout');
        }
    },
    mutations: {
        loginRequest(state, user) {
            state.status = { loggingIn: true };
            state.user = user;
        },
        loginSuccess(state, user) {
            state.status = { loggedIn: true };
            state.user = user;
        },
        loginFailure(state) {
            state.status = {};
            state.user = null;
        },
        logout(state) {
            state.status = {};
            state.user = null;
        }
    }
}
