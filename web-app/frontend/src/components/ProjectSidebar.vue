<template>
  <ul>
    <router-link tag="li" :to="`/projects/${projectId}/users`"><PeopleIcon class="icon"/>Users</router-link>
    <router-link tag="li" :to="`/projects/${projectId}/backlog`"><BacklogIcon class="icon"/>Backlog</router-link>
    <router-link tag="li" :to="`/projects/${projectId}/sprints`"><BoardIcon class="icon"/>Sprints</router-link>
  </ul>
</template>

<script>
  import { BoardIcon, BacklogIcon, PeopleIcon } from '@spartez/vue-atlaskit'

  export default {
      name: "ProjectSidebar",
      components: { BoardIcon, PeopleIcon, BacklogIcon },
      props: {
          projectId: {
              type: Number,
              required: true
          }
      }
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
