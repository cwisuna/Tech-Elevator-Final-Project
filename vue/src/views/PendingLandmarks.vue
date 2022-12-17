<template>
  <div class="pendingLandmarksPage">
      <HomeHeader></HomeHeader>
      <PendingLandmarks />
  </div>
</template>

<script>
import HomeHeader from '../components/HomeHeader.vue'
import PendingLandmarks from '../components/PendingLandmarks.vue'

export default {
    components: {
        HomeHeader,
        PendingLandmarks
    }
}
</script>

<style>

</style>