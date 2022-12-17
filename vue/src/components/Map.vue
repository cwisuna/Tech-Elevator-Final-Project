<template>
  <div>
      <link rel = "stylesheet" href = "http://cdn.leafletjs.com/leaflet-0.7.3/leaflet.css" />
      <!-- <h2>Minneapolis Map</h2> -->
      <div class="map">
          <iframe width="425" height="350" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" 
            src="https://www.openstreetmap.org/export/embed.html?bbox=-93.40061187744142%2C44.88263391919232%2C-93.20835113525392%2C45.05872899067149&amp;layer=mapnik" 
            style="border: 1px solid black"
            v-bind="LMarker">
          </iframe><br/>
          <!-- <l-map style="height: 300px" :zoom="zoom" :center="center">
            <l-tile-layer :url="url" :attribution="attribution"></l-tile-layer>
            <l-marker :lat-lng="markerLatLng"></l-marker>
          </l-map> -->
          <small>
              <a href="https://www.openstreetmap.org/#map=12/44.9707/-93.3045">View Larger Map</a>
           </small>
      </div>
      
  </div>
</template>

<script >
// import L from 'leaflet';
// import { LMap, LTileLayer, LMarker } from 'vue2-leaflet';

// let mapOptions = {
//     center:[48.8702, 2.3368],
//     zoom:15
// }
// let map = new L.map('map', mapOptions);

// let layer = new L.TileLayer(`http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png`);
// map.addLayer(layer);

export default {
    components: {
    // LMap,
    // LTileLayer,
    // LMarker,
    },
    data(){
        return {   
            LMarker: '',
        }
    },
    methods: {
        // addMarkersToMap(){
        //     const currentItinerary = this.$store.state.itineraryLandmarks;
        //     currentItinerary.forEach(element => {
        //         new L.Marker([element.landmark.address.longitude, element.landmark.address.latitude])
        //         .addTo(LMarker)
        //     }); 
        // }
    }
}
</script>

<style>

h2 {
color: #004E64;
font-family: 'Montserrat Alternates', 'Franklin Gothic Medium', 'Arial Narrow', 'Arial', 'sans-serif';
}

.map{
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}

</style>