<template>
  <div>
    <HomeHeader></HomeHeader>
    <Routes></Routes>
  </div>
</template>

<script>
import Routes from '../components/Routes.vue'
import HomeHeader from '../components/HomeHeader.vue'
export default {
    components: {
        Routes,
        HomeHeader
    }
}
</script>

<style>

</style>