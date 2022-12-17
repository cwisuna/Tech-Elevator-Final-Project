<template>
  <div>
      <div id="route-container" v-for="route in this.$store.state.routes" v-bind:key="route.id">
          <p>{{route.summary}}</p>
          <div>{{route.segments}}</div>
          <div>{{route.geometry}}</div>
          <div>{{route.wayPoints}}</div>
      </div>
  </div>
</template>

<script>
import RouteService from "../services/RouteService"

export default {
    name: "routes",
    data(){

    },
    created() {
        this.getRoute();
    },
    methods: {
        getRoute(){
            //pass user's selected landmarks into retrieveRoute
            RouteService.retrieveRoute(this.$store.state.itineraryLandmarks)
            .then(response => {
                this.$store.commit('SET_ROUTES', response.data);
            })
            .catch(error => {
                if (error.response && error.response.status === 404) {
                    alert(
                        "Route not available. You may have entered invalid landmark coordinates."
                    );
                    this.$router.push({ name: 'home' });
                }
            });
        },
    }
}
</script>

<style>

</style>