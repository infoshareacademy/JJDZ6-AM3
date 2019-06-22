<template>
  <div>
    <header>
      <h3>{{ project.name }}</h3>
    </header>
    <div class="content">
      <router-view/>
    </div>
  </div>
</template>

<script>
import { getProject } from "../services/scrum-board-api";

export default {
    name: "Project",
    props: {
        projectId: {
            type: Number,
            required: true
        }
    },
    data() {
        return { project: {} };
    },
    async mounted() {
        const { data: project } = await getProject(this.projectId);
        this.project = project;
    }
};
</script>
<style scoped>
.content {
    padding: 20px;
}
</style>
