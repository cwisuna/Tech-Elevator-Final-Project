<template>
  <div id="landmark-details-div">
  <landmark-details/>
  <!-- <landmarks/> -->
  <reviews />
  </div>
</template>

<script>
import LandmarkDetails from '../components/LandmarkDetails.vue'
import Reviews from '../components/Reviews.vue'
//import Landmarks from '../components/Landmarks.vue'
export default {
  components: {
       LandmarkDetails,
       Reviews
    //Landmarks 
},

}
</script>

<style>
  #landmark-details-div {
    height: 100vh;
    background-color: #004e64;
  }
</style>