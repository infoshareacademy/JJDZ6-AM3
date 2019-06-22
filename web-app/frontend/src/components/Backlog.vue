<template>
  <div>
    <h1 class="md-title">Backlog</h1>
    <table v-if="backlog.length">
      <thead>
        <tr>
            <td>ID</td>
            <td>Type</td>
            <td>Priority</td>
            <td>Title</td>
            <td>Assignee</td>
        </tr>
      </thead>
      <tbody>
        <tr v-for="task in backlog">
          <td>{{ task.id }}</td>
          <td>{{ task.type }}</td>
          <td>{{ task.priority }}</td>
          <td>
            <a href @click.prevent="onRouterPush(task.id)">{{ task.title }}</a>
          </td>
          <td>{{ task.assignee }}</td>
        </tr>
      </tbody>
    </table>
    <div v-else>Backlog is empty!</div>
  </div>
</template>

<script>
import { getBacklogForProject } from "../services/scrum-board-api";

export default {
    name: "Backlog",
    props: {
        projectId: { type: Number, required: true }
    },
    data() {
        return {
            backlog: []
        };
    },
    methods: {
        onRouterPush(id) {
            this.$router.push({ path: `/tasks/${id}` });
        }
    },
    async mounted() {
        const { data: backlog } = await getBacklogForProject(this.projectId);
        this.backlog = backlog;
    }
};
</script>
<style scoped>
  table {
      width: 100%;
  }
</style>
