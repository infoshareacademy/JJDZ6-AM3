<template>
  <div class="container">
        <Button appearance="default" class="md-primary" @click="createSprint">Create Sprint</Button>
          <table>
            <header>
              <h1>Sprints</h1>
            </header>
            <tr v-for="sprint in sprints">
              <td>{{ sprint.startDate }}</td>
              <td>{{ sprint.endDate }}</td>
              <td>{{ sprint.assignee }}</td>
            </tr>
      </table>
  </div>
</template>

<script>
import { getSprintsForProject } from "../services/scrum-board-api";
import { Button } from '@spartez/vue-atlaskit'

export default {
    name: "Sprints",
    components: { Button },
    props: {
        projectId: { type: Number, required: true }
    },
    data() {
        return {
            sprints: []
        };
    },
    methods: {
        onRouterPush(id) {
            this.$router.push({ path: `/sprints/${id}` });
        },
        createSprint() {

        }
    },
    async mounted() {
        const { data: sprints } = await getSprintsForProject(this.projectId);
        this.sprints = sprints;
        console.log(sprints)
    }
};
</script>
