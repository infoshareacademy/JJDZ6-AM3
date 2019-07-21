<template>
  <div>
    <router-view @operation-finished="fetchResources"/>
    <Button class="create-sprint" appearance="primary" @click="onSprintCreate">Crate Sprint</Button>
    <div class="sprint" v-for="sprint in sprints">
        <h2>{{sprint.name}}</h2>
        <DropTarget @drop="addToSprint" :target="sprint.id" class="sprint-drop-zone" :drop-zone="!sprint.tasks.length">
            <DragTarget class="issue" v-for="task in sprint.tasks" :transferData="task">
                <Issue :task="task" @router-push="onRouterPush"/>
            </DragTarget>
        </DropTarget>
    </div>
    <h1>Backlog</h1>
      <DropTarget :target="null" @drop="addToBacklog">
          <DragTarget class="issue" v-for="task in backlog" :transferData="task">
              <Issue :task="task" @router-push="onRouterPush"/>
          </DragTarget>
      </DropTarget>
  </div>
</template>

<script>
import { addTasksToSprint, getBacklogForProject, getSprintsForProject, getTask, updateTask } from "../services/scrum-board-api";
import { Button, Select } from '@spartez/vue-atlaskit'
import DragTarget from './common/DragTarget'
import DropTarget from './common/DropTarget'
import Issue from './Issue'

export default {
    name: "Backlog",
    components: { Button, DragTarget, DropTarget, Select, Issue },
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

   .create-sprint {
       margin-bottom: 30px;
   }

   table {
       width: 100%;
       table-layout: fixed;
       text-align: left;
   }

   h2 {
       text-transform: uppercase;
       margin-bottom: 20px;
       border-bottom: 1px solid gray;
   }

   .sprint {
       margin-bottom: 50px;
   }

   .sprint-drop-zone[drop-zone] {
       padding: 30px;
       border: 2px dashed #DFE1E6;
       position: relative;
   }

   .sprint-drop-zone[drop-zone]::after {
       position: absolute;
       top: 50%;
       left: 50%;
       transform: translate(-50%, -50%);
       content: 'Drop task here';
       color: #DFE1E6;
   }

   .issue:first-of-type {
       border-top: 1px solid #DFE1E6;
   }
</style>
