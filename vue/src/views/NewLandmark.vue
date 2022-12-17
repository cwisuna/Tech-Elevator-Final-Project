<template>
  <div>
    <home-header />
    <new-landmark />
  </div>
</template>

<script>
import HomeHeader from '../components/HomeHeader.vue'
import NewLandmark from '../components/NewLandmark.vue'

export default {
    components: {
        NewLandmark,
        HomeHeader
    },
    // created() {
    //   this.$store.commit('ADD_LANDMARK_FALSE');
    // },
    // beforeDestroy() {
    //   this.$store.commit('ADD_LANDMARK_TRUE');
    // }
}
</script>

<style>

</style>