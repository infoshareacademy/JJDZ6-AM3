<template>
    <div class="board">
        <DropTarget v-for="status in statuses" class="column"
                    @drop="onDrop" :target="status">
            <h2>{{status}}</h2>
                <div v-for="task in todo" title="To Do">
                    <Task :task="task" status="todo"/>
                </div>
        </DropTarget>
    </div>
</template>

<script>
import Task from './common/Task';
import DropTarget from './common/DropTarget';
import { getStatuses } from "../services/scrum-board-api";

export default {
    name: "Sprint",
    components: {
        Task,
        DropTarget
    },
    data() {
        return {
            todo: [{ name: 'todo', id: 1 }],
            isDragging: false,
            delayedDragging: false,
            statuses: []
        };
    },

    async mounted() {
        const { data: statuses } = await getStatuses();
        this.statuses = statuses;
    },

    methods: {
        onDrop(payload) {
            const { status, id, target } = payload;
            console.log(status, id, target)
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
