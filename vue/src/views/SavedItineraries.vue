<template>
  <div id="saved-itinerary-page">
      <HomeHeader id="homeHeader" />
      <saved-itineraries />
  </div>
</template>

<script>
import HomeHeader from '../components/HomeHeader.vue'
import SavedItineraries from '../components/SavedItineraries.vue'


export default {
    name: 'itinerary',
    components: {
        HomeHeader,
        SavedItineraries
    },
}
</script>

<style>

    #homeHeader {
        border-bottom: #1fd6c1 5px solid;
    }
</style>