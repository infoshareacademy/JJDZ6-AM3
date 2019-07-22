<template>
<Modal heading="Start Sprint">
    <template slot="content">
        <FieldGroup label="Start Date">
        <DatePicker v-model="startDate"/>
    </FieldGroup>
    <FieldGroup label="End Date">
        <DatePicker v-model="endDate"/>
    </FieldGroup>
    </template>
</Modal>
</template>

<script>
    import { DatePicker, Modal, FieldGroup } from '@spartez/vue-atlaskit'

    export default {
        name: "StartSprint",
        components: { DatePicker, Modal, FieldGroup },
        data() {
            return {
                startDate: undefined,
                endDate: undefined
            }
        }
    }
</script>

<style scoped>

</style>
