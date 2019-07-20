<template>
  <div>
    <router-view/>
    <Button appearance="primary" @click="onSprintCreate">Crate Sprint</Button>
    <header>
        <h1>Sprints</h1>
    </header>
    <div class="sprint" v-for="sprint in sprints">
        <h2>{{sprint.name}}</h2>
        <DropTarget tag="table" @drop="addToSprint" :target="sprint.id">
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
            <DragTarget v-for="task in sprint.tasks" tag="tr" :transferData="task">
                <td>{{ task.id }}</td>
                <td>{{ task.type }}</td>
                <td>{{ task.priority }}</td>
                <td>
                    <a href @click.prevent="onRouterPush(task.id)">{{ task.title }}</a>
                </td>
                <td>{{ task.assignee }}</td>
                <td>{{ task.assignee }}</td>
              </DragTarget>
            </tbody>
        </DropTarget>
    </div>
    <h1>Backlog</h1>
      <DropTarget :target="null" tag="table" @drop="addToBacklog">
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
                <DragTarget v-for="task in backlog" tag="tr" :transferData="task">
            <td>{{ task.id }}</td>
            <td>{{ task.type }}</td>
            <td>{{ task.priority }}</td>
            <td>
                <a href @click.prevent="onRouterPush(task.id)">{{ task.title }}</a>
            </td>
            <td>{{ task.assignee }}</td>
        </DragTarget>
          </tbody>
      </DropTarget>
  </div>
</template>

<script>
import { addTasksToSprint, getBacklogForProject, getSprintsForProject, getTask, updateTask } from "../services/scrum-board-api";
import { Button } from '@spartez/vue-atlaskit'
import DragTarget from './common/DragTarget'
import DropTarget from './common/DropTarget'

export default {
    name: "Backlog",
    components: { Button, DragTarget, DropTarget },
    props: {
        projectId: { type: Number, required: true }
    },
    data() {
        return {
            backlog: [],
            sprints: []
        };
    },
    methods: {
        onRouterPush(id) {
            this.$router.push({ path: `/tasks/${id}` });
        },
        onSprintCreate() {
            this.$router.push({ path: `create-sprint`, append: true });
        },
        async addToBacklog(payload) {
            console.log(payload);
            const id = payload.getData('id');
            const { data: task } = await getTask(id);
            const updatedTask = { ...task, sprintId: null };
            await updateTask(Number.parseInt(id), updatedTask);
            this.fetchResources();
        },
        async addToSprint(payload, sprintId) {
            const id = payload.getData('id');
            await addTasksToSprint([Number.parseInt(id)], sprintId);
            this.fetchResources();
        },
        async fetchResources() {
            const [backlog, sprints] = await Promise.all([getBacklogForProject(this.projectId), getSprintsForProject(this.projectId)])
            this.backlog = backlog.data;
            this.sprints = sprints.data;
        }
    },
    async mounted() {
        this.fetchResources()
    }
};
</script>
<style scoped>

   header{
       margin-bottom: 50px;
   }

  table {
      width: 100%;
  }

  .sprint {
      margin-bottom: 50px;
  }

  tbody tr {
      cursor: grab;
  }

  tbody {
      min-height: 500px;
      padding: 50px;
      width: 100%;
  }

  .drop-zone {
      height: 50px;
      width: 100%;
      border: dashed 2px grey;
  }
</style>
