<template>
  <div class="container">
    <Button appearance="primary" class="md-primary" @click="createProject">Create Project</Button>
    <br>
    <br>
    <header>
      <h1 class="md-title">Projects</h1>
    </header>
    <table v-model="projects">
      <thead>
        <tr>
          <td>ID</td>
          <td>Name</td>
          <td>Members</td>
        </tr>
      </thead>
      <tbody>
        <tr v-for="project in projects">
          <td>{{ project.id }}</td>
          <td>
            <a href @click.prevent="onRouterPush(project.id)">
              {{project.name}}
            </a>
          </td>
          <td>{{ project.users.length }}</td>
        </tr>
      </tbody>
    </table>
    <router-view @operation-finished="fetchProjects"/>
  </div>
</template>

<script>
import { getProjects } from "../services/scrum-board-api";
import { Button } from "@spartez/vue-atlaskit";

export default {
    name: "Projects",
    components: { Button },
    data: () => ({
        projects: []
    }),
    async mounted() {
        this.fetchProjects();
    },
    methods: {
        onRouterPush(id) {
            this.$router.push({ path: `/projects/${id}/backlog` });
        },
        createProject() {
            this.$router.push({ path: "add", append: true });
        },
        async fetchProjects() {
            try {
                const { data: projects } = await getProjects();
                this.projects = projects;
                console.log(projects)
            } catch (e) {
                console.error(e);
            }
        }
    }
};
</script>
<style scoped>
  .container {
      height: 100%;
  }
</style>
