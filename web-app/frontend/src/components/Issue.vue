<template>
<div class="task">
    <div>{{ task.id }}</div>
    <div :type="task.type">{{ task.type }}</div>
    <div>{{ task.priority }}</div>
    <div>
        <a href @click.prevent="onRouterPush">{{ task.title }}</a>
    </div>
    <div>{{ task.assignee }}</div>
    <div>{{ task.storyPoints }}</div>
</div>
</template>

<script>
export default {
    name: "Issue",
    props: {
        task: {
            type: Object,
            required: true
        }
    },
    methods: {
        onRouterPush() {
            this.$emit('router-push', this.task.id)
        }
    }
}
</script>

<style scoped>
    .task {
        display: flex;
        width: 100%;
        cursor: grab;
        justify-content: space-between;
        border-bottom: 1px solid #DFE1E6;
        padding: 6px 8px;
    }

    [type="TASK"] {
        background-color: purple;
        color: #fff;
        border-radius: 3px;
        padding: 2px 4px;
    }

    [type="BUG"] {
        background-color: red;
        color: #fff;
        border-radius: 3px;
        padding: 2px 4px;
    }

    [type="STORY"] {
        background-color: lightblue;
        color: white;
        border-radius: 3px;
        padding: 2px 4px;
    }

    .task:hover {
        background-color: #deebff;
    }
</style>
