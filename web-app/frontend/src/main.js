import Vue from 'vue'
import VueRouter from 'vue-router'
import '@spartez/vue-atlaskit/dist/bundle.css';
import { routes } from './routes/routes'
import './styles.css'


Vue.use(VueRouter)
import Main from './components/Main.vue'

Vue.config.productionTip = false;

const router = new VueRouter({
    routes,
    mode: 'history'
});

new Vue({
    router,
    render: h => h(Main),
}).$mount('#app');
