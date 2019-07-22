import Vue from 'vue'
import VueRouter from 'vue-router'
import '@spartez/vue-atlaskit/dist/bundle.css';
import { store } from './store';
import router from './routes/router'
import './styles.css'


Vue.use(VueRouter);
import Main from './components/Main.vue'

Vue.config.productionTip = false;

new Vue({
    router,
    store,
    render: h => h(Main),
}).$mount('#app');

