<template>
    <Modal heading="Create Sprint" @submit="createSprint" @cancel="onClose">
        <div slot="content">
            <FieldGroup label="Name" required>
                <Input v-model="sprint.name" auto-focus/>
            </FieldGroup>
        </div>
    </Modal>
</template>

<script>
    import { Modal, FieldGroup, Input } from '@spartez/vue-atlaskit'
    import { createSprint } from "../services/scrum-board-api";

    export default {
        name: "CreateSprint",
        components: { Modal, FieldGroup, Input },
        props: {
            projectId: {
                type: Number,
                require: true
            }
        },
        data() {
            return {
                sprint: {
                    name: '',
                    projectId: this.projectId
                }
            }
        },
        methods: {
            async createSprint() {
                try {
                    await createSprint({ ...this.sprint });
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
    }
</script>
