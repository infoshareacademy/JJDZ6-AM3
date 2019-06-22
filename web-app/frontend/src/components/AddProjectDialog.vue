<template>
    <Modal heading="Create Project" @submit="createProject" @cancel="onClose">
        <div slot="content">
            <FieldGroup label="Name" required>
                <Input v-model="project.name" auto-focus/>
            </FieldGroup>
            <FieldGroup label="Product Owner" required>
                <Select :options="[{id:1,label: '123'}]"/>
            </FieldGroup>
        </div>
    </Modal>
</template>

<script>
import { createProject } from "../services/scrum-board-api";
import { Modal, Input, FieldGroup, TextArea, Select } from '@spartez/vue-atlaskit'


export default {
    name: "AddProjectDialog",
    components: { Modal, Input, FieldGroup, TextArea, Select },
    data() {
        return {
            showDialog: false,
            project: {
                name: ""
            }
        };
    },
    methods: {
        async createProject() {
            try {
                await createProject({ ...this.project });
            } catch (e) {
                console.error(e);
            } finally {
                this.onClose();
            }
        },
        onClose() {
            this.$router.push({ path: "." });
            this.$emit("operation-finished");
        }
    }
};
</script>

<style scoped>
.content {
    padding: 16px;
}
</style>
