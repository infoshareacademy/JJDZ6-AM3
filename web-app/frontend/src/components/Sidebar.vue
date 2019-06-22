<template>
  <div class="sidebar">
    <div ref="content" class="sidebar-content">
      <nav class="navgroup navgroup-vertical">
          <slot/>
      </nav>
    </div>
  </div>
</template>

<script>
export default {
    name: "Sidebar"
};
</script>

<style scoped>
.sidebar {
    height: 100%;
    background: #f4f5f7;
    display: flex;
    flex-direction: column;
    overflow: hidden;
}

.sidebar-content {
    overflow: auto;
    display: flex;
    height: 100%;
}

.navgroup {
    flex-grow: 1;
    display: flex;
    width: 100%;
    padding: 10px;
}
</style>
