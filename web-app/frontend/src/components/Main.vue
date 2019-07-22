<template>
  <main>
    <nav>
      <div class="navigation">
        <router-link tag="span" to="/"><div class="tab">Dashboard</div></router-link>
        <router-link tag="span" to="/projects"><div class="tab">Projects</div></router-link>
        <router-link v-if="!user" tag="span" to="/login"><div class="tab">Login</div></router-link>
        <router-link tag="span" to="/register"><div class="tab">Register</div></router-link>
      </div>
      <Button class="create-issue" appearance="danger" @click="onCreateIssue">Create Issue</Button>
      <Button v-if="user" class="create-issue" appearance="warning" @click="logout">Logout</Button>
      <span>{{user}}</span>
    </nav>
    <section class="content" role="main">
      <Sidebar class="left-panel">
        <router-view name="sidebar"/>
      </Sidebar>
      <div class="right-panel">
        <router-view/>
      </div>
    </section>
  </main>
</template>

<script>
import Sidebar from "./Sidebar";
import { Button } from '@spartez/vue-atlaskit'

export default {
    name: "Main",
    components: { Sidebar, Button },
    computed: {
        user() {
            return this.$store.state.authentication.user;
        }
    },
    methods: {
        onCreateIssue() {
            this.$router.push({ name: "create-issue" });
        },
        logout() {
            const { dispatch } = this.$store;
            dispatch('authentication/logout');
        }
    }
};
</script>
<style scoped>
.content {
    height: 100%;
    background: white;
    display: flex;
    flex-direction: row;
    overflow: hidden;
}

main {
    height: 100%;
}

.left-panel {
    width: 280px;
    flex-shrink: 0;
}

.right-panel {
    flex: 1;
    overflow-x: hidden;
    padding: 20px;
}

.content {
    height: 100%;
    overflow: auto;
}

.v-enter-active,
.v-leave-active {
    transition: opacity 0.5s;
}

.v-enter, .v-leave-to /* .fade-leave-active below version 2.1.8 */
{
    opacity: 0;
}

nav {
    display: flex;
    background-color: #0448a6;
    color: #fff;
    font-weight: 400;
    padding: 5px;
}

.navigation {
    display: flex;
    align-items: center;
}

.create-issue {
    margin-left: 10px;
}

.tab {
    padding: 6px 8px;
    cursor: pointer;
    display: flex;
    align-items: center;
    height: 100%;
    margin: 0 5px;
}

.tab:hover {
    background-color: #06f;
}
</style>
