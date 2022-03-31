<template>
  <PostsList/>
</template>

<script>
import {defineComponent} from 'vue';

// Components
import PostsList from '../components/posts/PostsList.vue';

export default defineComponent({
  name: 'BoardView',

  components: {
    PostsList,
  },
});
</script>
