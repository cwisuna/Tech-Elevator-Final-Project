<template>
  <div id="landmark-body">
    <div class="loading" v-if="isLoading">
      <img src="../assets/paper-plane.gif" />
    </div>
    <div id="form-inputs">
    <fieldset>
      <input
        id="filter-list"
        type="text"
        placeholder="Search landmark by name"
        v-model="search"
      />
    </fieldset>
    <fieldset>
      <select class="type-filter" id="type-filter" v-model="type">
        <option value="">Select Type</option>
        <option value="Amusement">Amusement</option>
        <option value="Art">Art</option>
        <option value="Educational">Educational</option>
        <option value="Historic">Historic</option>
        <option value="Outdoor">Outdoor</option>
        <option value="Restaurant">Restaurant</option>
        <option value="Shopping">Shopping</option>
        <option value="Venue">Venue</option>
      </select>
    </fieldset>
    </div>
    <div id="landmark-list">
    <div id="container" v-for="landmark in filteredList" v-bind:key="landmark.id" :landmarkId="landmark.id">
      
        <div id="landmark-container" v-on:click="routeToDetails(landmark.id)">
        <h3>{{landmark.name}}</h3>
        <div id= "img-container">
            <img class="display-img" :src="landmark.img_URL" />
        </div>
        <p>{{ landmark.type.name }}</p>
      </div>
      
    </div>
    </div>
  </div>
</template>

<script>
import landmarkService from "../services/LandmarkService";

export default {
  name: "landmarks",
  data() {
    return {
      search: "",
      type: "",
      i: 1,
      imgURL: "",
      isLoading: true
    };
  },
  created() {
    this.retrieveLandmarks();
  },
  methods: {
    retrieveLandmarks() {
      landmarkService.list().then((response) => {
        this.$store.commit("SET_LANDMARKS", response.data);
        this.isLoading = false;
      });
    },
    routeToDetails(landmarkId) {
      this.$router.push(`/details/${landmarkId}`)
    }
  },
  computed: {
      filterByType(){
          const filteredLandmarks = this.$store.state.landmarks;
          return filteredLandmarks.filter((landmark)=> {
              if(!landmark.type.name.indexOf(this.type)){
                  return true;
              }
              return false;
          })
      },
      filteredList() {
      const filteredLandmarks = this.$store.state.landmarks;
      if (this.search != "") {
        return filteredLandmarks.filter((landmark) => {
          if (
              landmark.name
              .toLowerCase()
              .includes(this.search.toLowerCase())
              ) {
                    return true;
                }
          return false;
        });
      }
      return this.filterByType;
    }
  },
};
</script>

<style>

#landmark-list {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
}

#landmark-container{
    margin: 10px 10px;
    padding: 2%;
    border: 2px #129D8D solid;
    background-color: #129D8D;
    color: #F3FCED;
    border-radius: 42px;
    text-align: center;
}

#landmark-container:hover {
  cursor: pointer;
}

#landmark-container h3 {
  height: 35px;
}

#form-inputs {
  display: flex;
  flex-direction: row;
  justify-content:space-evenly;
}

fieldset {
  width: 15%;
  text-align: center;
  background-color: #129D8D;
  border-width: 0px;
  border-radius: 30px;
  padding-top: .6em;
}

.display-img {
  height: 7rem;
  border: 5px white solid;
  border-radius: 5px;
}

#container {
    width: 300px;
    margin-left: 12.5px;
    margin-right: 12.5px;
    margin-bottom: 10px;
}

.loadings{
  flex: 3;
}

</style>