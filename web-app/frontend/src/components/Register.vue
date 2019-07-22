<template>
    <Modal heading="Login From" @submit="onSubmit" @cancel="onCancel">
        <template slot="content">
            <FieldGroup label="Login" required>
                <Input v-model="user.username"/>
            </FieldGroup>
            <FieldGroup label="Password" required>
                <Input typ="password" v-model="user.password"/>
            </FieldGroup>
            <FieldGroup label="Type" required>
                <Input v-model="user.email"/>
            </FieldGroup>
        </template>
    </Modal>
</template>

<script>
    import { Modal, FieldGroup, Input } from '@spartez/vue-atlaskit'

    export default {
        name: "Register",
        components: {
            Modal, FieldGroup, Input
        },
        data() {
            return {
                user: {
                    username: '',
                    password: '',
                    email: '',
                    role: ''
                },
                loading: false
            }
        },
        methods: {
            async onSubmit() {
                this.loading = true;
                try {
                    const { username, password } = this;
                    const { dispatch } = this.$store;
                    if (username && password) {
                        dispatch('authentication/register', { username, password });
                    }
                }
                catch (e) {
                    console.error(e)
                }
                finally {
                    this.onCancel();
                    this.loading = false;
                }
            },
            onCancel() {
                this.$router.back();
            }
        }
    }
</script>

<style scoped>

</style>
