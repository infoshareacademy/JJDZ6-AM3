<template>
    <Modal heading="Create Issue" @submit="createTask" @cancel="onClose">
        <div slot="content">
            <FieldGroup label="Title" required>
                <Input v-model="task.title" auto-focus/>
            </FieldGroup>
            <FieldGroup label="Description" required>
                <TextArea v-model="task.description"/>
            </FieldGroup>
            <FieldGroup label="Type" required>
                <Select v-model="task.type" :options="types"/>
            </FieldGroup>
            <FieldGroup label="Project" required>
                <Select v-model="project" :normalizer="normalizer" :options="projects"/>
            </FieldGroup>
            <FieldGroup label="Priority" required>
                <Select v-model="task.priority" :options="priorities"/>
            </FieldGroup>
            <FieldGroup label="Status" required>
                <Select v-model="task.status" :options="statuses"/>
            </FieldGroup>
        </div>
    </Modal>
</template>

<script>
import { getTypes, createTask, getProjects, getPriorities, getStatuses } from "../services/scrum-board-api";
import { Modal, Input, FieldGroup, TextArea, Select } from '@spartez/vue-atlaskit'


export default {
    name: "CreateIssueDialog",
    components: { Modal, FieldGroup, Input, TextArea, Select },
    data() {
        return {
            task: {
                title: "",
                description: "",
                type: "",
                priority: "",
                status: "",
                projectId: undefined
            },
            project: undefined,
            types: [],
            priorities: [],
            statuses: [],
            projects: []
        };
    },
    watch: {
        project(project) {
            this.task.projectId = project && project.id;
        }
    },

    async mounted() {
        const [types, projects, priorities, statuses] = await Promise.all([getTypes(), getProjects(), getPriorities(), getStatuses()]);
        this.types = types.data;
        this.projects = projects.data;
        this.priorities = priorities.data;
        this.statuses = statuses.data;
    },
    methods: {
        async createTask() {
            try {
                await createTask({ ...this.task });
            } catch (e) {
                console.dir(e);
            } finally {
                this.onClose();
            }
        },

        normalizer(project) {
            return { id: project.id, label: project.name, value: project }
        },

        onClose() {
            this.$router.back();
        }
    }
};
</script>
