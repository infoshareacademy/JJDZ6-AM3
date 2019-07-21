<template>
  <Modal heading="Form Demo" @submit="createTask" @cancel="onClose">
        <div slot="content">
            <FieldGroup label="Title" required>
                <Input v-model="user.fullName" auto-focus/>
            </FieldGroup>
            <FieldGroup label="Type" required>
                <Input v-model="user.email"/>
            </FieldGroup>
            <FieldGroup label="Type" required>
                <TextArea v-model="user.role"/>
            </FieldGroup>
        </div>
    </Modal>
</template>

<script>
import { getUserRoles, createUser } from "../services/scrum-board-api";
import { FieldGroup, Input, TextArea, Modal } from '@spartez/vue-atlaskit';


export default {
    name: "CreateUserDialog",
    components: { FieldGroup, Input, TextArea, Modal },
    data() {
        return {
            user: {
                fullName: "",
                email: "",
                role: ""
            },
            roles: []
        };
    },
    async mounted() {
        const roles = await getUserRoles();
        this.roles = roles.data;
    },
    methods: {
        async createTask() {
            try {
                await createUser({ ...this.user });
            } catch (e) {
                console.dir(e);
            } finally {
                this.onClose();
            }
        },
        onClose() {
            this.$router.back();
        }
    }
};
</script>

<style scoped>
.dialog {
    display: inline-flex;
}

.content {
    padding: 16px;
    width: 40%;
}
</style>
