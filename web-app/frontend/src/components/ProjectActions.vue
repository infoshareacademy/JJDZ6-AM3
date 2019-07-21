<template>
  <ul>
    <router-link tag="li" to="/create-user">Add User</router-link>
    <router-link tag="li" to="/search">Search for Issues</router-link>
  </ul>
</template>

<script>
export default {
    name: "ProjectActions"
};
</script>

<style scoped>
    ul {
        list-style-type: none;
        width: 100%;
        padding-left: 5px;
    }

    li {
        color: rgb(66, 82, 110);
        padding: 8px 6px;
        cursor: pointer;
        display: flex;
        align-items: center;
    }

    li:hover {
        background-color: rgba(9, 30, 66, 0.04);
    }

    .icon {
        margin-right: 5px;
    }
</style>
