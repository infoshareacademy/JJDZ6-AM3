<template>
  <component :is="tag"
             @drop.stop="onDrop"
             @dragover.prevent
             @dragenter.prevent
  >
    <slot/>
  </component>
</template>

<script>
export default {
    props: {
        target: {
            type: [String, Number],
            default: ''
        },
        tag: {
            type: String,
            default: 'div'
        }
    },
    methods: {
        onDrop(e) {
            this.$emit('drop', e.dataTransfer, this.target)
        }
    }
}
</script>
