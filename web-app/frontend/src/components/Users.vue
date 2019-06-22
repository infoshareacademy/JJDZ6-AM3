<template>
  <div>
    <Button appearance="primary" @click="createUser">Add User</Button>
    <br>
    <br>
    <header>
        <h1>Users</h1>
    </header>
    <table>
      <thead>
      <tr>
        <td>ID</td>
        <td>Name</td>
      </tr>
      </thead>
      <tr v-for="user in users">
        <td>{{ user.id }}</td>
        <td>
          <a href @click.prevent="onRouterPush(user.id)">{{ user.fullName }}</a>
        </td>
      </tr>
    </table>
    <router-view/>
  </div>
</template>

<script>
import { getUsersForProject } from "../services/scrum-board-api";
import { Button } from '@spartez/vue-atlaskit'


export default {
    name: "Users",
    components: { Button },
    props: {
        projectId: {
            type: Number,
            required: true
        }
    },
    data: () => ({
        users: []
    }),
    async mounted() {
        this.fetchUsers();
    },
    methods: {
        onRouterPush(id) {
            this.$router.push({ path: `/users/${id}` });
        },
        createUser() {
            this.$router.push({ path: "add", append: true });
        },
        async fetchUsers() {
            try {
                const { data: users } = await getUsersForProject(this.projectId);
                this.users = users;
            } catch (e) {
                console.error(e);
            }
        }
    }
};
</script>

<style scoped>
</style>
