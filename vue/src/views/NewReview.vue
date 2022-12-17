<template>
  <div class="newReviewPage">
  <HomeHeader></HomeHeader>
  <new-review />
  </div>
</template>

<script>
import HomeHeader from '../components/HomeHeader.vue'
import NewReview from '../components/NewReview.vue'

export default {
    components: {
        NewReview,
        HomeHeader
    }
}
</script>

<style>

</style>