<template>
  <md-table v-model="tasks" md-sort="name" md-sort-order="asc" md-card>
    <md-table-toolbar>
      <h1 class="md-title">Tasks</h1>
    </md-table-toolbar>
    <md-table-row slot="md-table-row" slot-scope="{ item }">
      <md-table-cell md-label="ID" md-numeric>{{ item.id }}</md-table-cell>
      <md-table-cell md-label="Type" md-numeric>{{ item.type }}</md-table-cell>
      <md-table-cell md-label="Priority" md-numeric>{{ item.priority }}</md-table-cell>
      <md-table-cell md-label="Name" md-sort-by="name">
        <a href @click.prevent="onRouterPush(item.id)">{{ item.title }}</a>
      </md-table-cell>
      <md-table-cell md-label="Assignee" md-numeric>{{ item.assignee }}</md-table-cell>
    </md-table-row>
  </md-table>
</template>

<script>
import { getTasks } from "../services/scrum-board-api";

export default {
  name: "Tasks",
  data: () => ({
    tasks: []
  }),
  async mounted() {
    const { data: tasks } = await getTasks();
    this.tasks = tasks;
  },
  methods: {
    onRouterPush(id) {
      this.$router.push({ path: `/tasks/${id}` });
    }
  }
};
</script>
