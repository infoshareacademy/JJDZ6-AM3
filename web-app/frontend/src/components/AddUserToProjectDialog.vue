<template>
    <Modal heading="Create Project" @submit="addUser" @cancel="onClose">
        <div slot="content">
            <FieldGroup label="Name" required>
                <Input v-model="user.fullName" auto-focus/>
            </FieldGroup>
        </div>
    </Modal>
</template>

<script>
import { getUsers, addUserToProject } from "../services/scrum-board-api";
import { Modal, Input, FieldGroup, TextArea } from '@spartez/vue-atlaskit'


export default {
    name: "AddUserToProjectDialog",
    components: { Input, FieldGroup, TextArea, Modal },
    props: {
        projectId: {
            type: Number,
            required: true
        }
    },
    data() {
        return {
            users: [],
            id: ""
        };
    },
    computed: {
        user() {
            return this.users.find(user => (user.id = this.id));
        }
    },
    async mounted() {
        const { data: users } = await getUsers();
        this.users = users;
    },
    methods: {
        async addUser() {
            try {
                await addUserToProject({ id: this.projectId, user: this.user });
                this.onClose();
            } catch (e) {
                console.error(e);
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
