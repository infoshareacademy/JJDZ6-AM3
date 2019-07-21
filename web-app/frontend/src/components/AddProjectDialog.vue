<template>
    <Modal heading="Create Project" @submit="createProject" @cancel="onClose">
        <div slot="content">
            <FieldGroup label="Name" required>
                <Input v-model="project.name" auto-focus/>
            </FieldGroup>
            <FieldGroup label="Product Owner" required>
                <Select v-model="user" :async="true" :is-fetching="isFetching"
                        :normalizer="normalizer"
                        @search-change="fetchUsers" :options="options"/>
            </FieldGroup>
        </div>
    </Modal>
</template>

<script>
import { createProject, getUsers } from "../services/scrum-board-api";
import { Modal, Input, FieldGroup, TextArea, Select } from '@spartez/vue-atlaskit'


export default {
    name: "AddProjectDialog",
    components: { Modal, Input, FieldGroup, TextArea, Select },
    data() {
        return {
            showDialog: false,
            project: {
                name: ""
            },
            options: [],
            user: undefined,
            isFetching: false
        };
    },

    watch: {
        user(user) {
            this.project.productOwner = user;
        }
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

        normalizer(user) {
            return {
                id: user.id,
                label: user.fullName,
                value: user
            }
        },

        async fetchUsers(query) {
            this.isFetching = true;
            const { data: users } = await getUsers({ query });
            this.isFetching = false;
            this.options = users;
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
