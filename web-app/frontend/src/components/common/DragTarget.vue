<template>
  <component :is="tag"
             draggable
             @dragstart.self="onDrag"
             @dragover.prevent
             @dragenter.prevent
  >
    <slot/>
  </component>
</template>

<script>
export default {
    props: {
        transferData: {
            type: Object,
            required: true
        },
        tag: {
            type: String,
            default: 'div'
        }
    },
    methods: {
        onDrag(e) {
            e.dataTransfer.effectAllowed = 'move';
            e.dataTransfer.dropEffect = 'move';
            Object.keys(this.transferData).forEach((prop) => {
                e.dataTransfer.setData(prop, this.transferData[prop])
            });
        }
    }
}
</script>
