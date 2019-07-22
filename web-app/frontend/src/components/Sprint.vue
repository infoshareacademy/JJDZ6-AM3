<template>
    <div class="board">
        <DropTarget v-for="status in statuses" class="column"
                    @drop="onDrop" :target="status">
            <h2>{{status}}</h2>
                <div v-for="task in sprint.tasks" title="To Do">
                    <Task :task="task" status="todo"/>
                </div>
        </DropTarget>
    </div>
</template>

<script>
import Task from './common/Task';
import DropTarget from './common/DropTarget';
import { getSprint, getStatuses } from "../services/scrum-board-api";

export default {
    name: "Sprint",
    props: {
        sprintId: {
            type: Number,
            required: true
        }
    },
    components: {
        Task,
        DropTarget
    }
    ,
    data() {
        return {
            sprint: {},
            statuses: []
        };
    },

    async mounted() {
        const { data: statuses } = await getStatuses();
        const { data: sprint } = await getSprint(this.sprintId);
        this.statuses = statuses;
        this.sprint = sprint;
    },

    methods: {
        onDrop(payload) {
            const { status, id, target } = payload;
        }
    }
};
</script>

<style scoped>
.board {
    display: flex;
    width: 100%;
    justify-content: space-between;
    height: 100%;
}

.column {
    width: 100%;
    margin-right: 5px;
    background-color: red;
    height: 100%;
}
</style>
