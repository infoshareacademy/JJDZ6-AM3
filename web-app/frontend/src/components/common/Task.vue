<template>
    <DragTarget :transferData="transferData">
        <div class="task">
            {{task.name}}
        </div>
    </DragTarget>
</template>

<script>
    import DropTarget from './DropTarget';
    import DragTarget from './DragTarget';

    export default {
        name: "Task",
        components: { DropTarget, DragTarget },
        props: {
            task: {
                type: Object,
                default: () => ({})
            },
            status: {
                type: String,
                required: true
            }
        },
        computed: {
            transferData() {
                return {
                    status: this.status,
                    id: this.task.id
                }
            }
        }
    }
</script>

<style scoped>

</style>
