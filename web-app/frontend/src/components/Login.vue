<template>
    <Modal heading="Login From" @submit="onSubmit" @cancel="onCancel">
        <template slot="content">
            <FieldGroup label="Login" required>
                <Input v-model="username"/>
            </FieldGroup>
            <FieldGroup label="Password" required>
                <Input typ="password" v-model="password"/>
            </FieldGroup>
        </template>
    </Modal>
</template>

<script>
    import { Modal, FieldGroup, Input } from '@spartez/vue-atlaskit'

    export default {
        name: "Login",
        components: {
            Modal, FieldGroup, Input
        },
        data() {
            return {
                username: '',
                password: '',
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
                        dispatch('authentication/login', { username, password });
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
                this.$router.push('/');
            }
        }
    }
</script>

<style scoped>

</style>
