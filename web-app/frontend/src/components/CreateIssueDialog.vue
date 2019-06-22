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
                <Select :options="normalizedTypes" @input="onTypeSelected"/>
            </FieldGroup>
            <FieldGroup label="Project" required>
                <Select :options="normalizedProjects" @input="onProjectSelected"/>
            </FieldGroup>
        </div>
    </Modal>
</template>

<script>
import { getTypes, createTask, getProjects } from "../services/scrum-board-api";
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
                projectId: undefined
            },
            types: [],
            projects: []
        };
    },
    computed: {
        normalizedTypes() {
            return this.types.map((label, id) => ({ id, label }))
        },
        normalizedProjects() {
            return this.projects.map(project => ({ id: project.id, label: project.name }))
        }
    },
    async mounted() {
        const [types, projects] = await Promise.all([getTypes(), getProjects()]);
        this.types = types.data;
        this.projects = projects.data;
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
        onTypeSelected(id) {
            this.task.type = this.normalizedTypes.find(type => type.id === id).label;
        },
        onProjectSelected(id) {
            this.task.projectId = id;
        },
        onClose() {
            this.$router.back();
        }
    }
};
</script>
