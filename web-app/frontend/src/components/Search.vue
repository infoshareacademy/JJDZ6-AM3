<template>
    <div>
        <Input @input="onInput"/>
        <ul>
            <li v-for="task in tasks">{{task.title}}</li>
        </ul>
    </div>
</template>

<script>
    import { Input } from '@spartez/vue-atlaskit'
    import { getTasks } from "../services/scrum-board-api";

    export default {
        name: "Search",
        components: { Input },
        data() {
            return {
                tasks: []
            }
        },
        mounted() {
            this.fetchIssues()
        },
        methods: {
            async onInput(query) {
                this.fetchIssues(query)
            },
            async fetchIssues(query) {
                const { data: tasks } = await getTasks({ query });
                this.tasks = tasks;
            }
        }
    }
</script>
