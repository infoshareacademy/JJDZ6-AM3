<template>
  <span>{{JSON.stringify(project)}}</span>
</template>

<script>
import { getUser } from "../services/scrum-board-api";

export default {
  name: "User",
  props: {
    userId: { type: Number, required: true }
  },
  data() {
    return {
      project: undefined
    };
  },
  async mounted() {
    const { data: user } = await getUser(this.userId);
    this.user = user;
  }
};
</script>

<style scoped>
</style>
