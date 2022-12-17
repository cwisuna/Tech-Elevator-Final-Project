<template>
  <div class="mainDiv">
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Montserrat Alternates"
    />
    <div class="loading" v-if="isLoading">
      <img src="../assets/paper-plane.gif" />
    </div>
    <div id="img-title-details">
      <div class="imgDiv">
        <img
          v-bind:src="this.$store.state.currentLandmark.img_URL"
          id="imgId"
        />
      </div>
      <div id="title-desc">
        <div class="titleDiv">
          <h1 id="landmarkTitle">
            {{ this.$store.state.currentLandmark.name }}
          </h1>
        </div>

        <div class="descDiv">
          <p id="description">
            {{ this.$store.state.currentLandmark.description }}
          </p>
        </div>
        <div class="like-dislike-btn">
          <button class="like-btn" v-on:click="likeLandmark" @click="reloadPage">Like</button>
          <button class="dislike-btn">Dislike</button>
        </div>
      </div>
    </div>

    <div class="landmark-status success" v-show="landmarkAddedSuccess">Landmark successfully added</div>
    <div class="landmark-status failure" v-show="itineraryContains">Landmark already exists in itinerary</div>
    
    <div id="button-container">
      <button
        class="buttons"
        id="add-itinerary-Btn"
        v-on:click="addToItinerary()"
      >
        add to itinerary
      </button>

      <div class="cancelDiv">
        <button class="buttons">
          <router-link id="homeLink" :to="{ name: 'home' }"
            >view landmarks</router-link
          >
        </button>
      </div>

      <div class="reviewDiv">
        <button class="buttons">
          <router-link
            id="reviewLink"
            :to="{
              name: 'NewReview',
              params: { landmarkId: this.$store.state.currentLandmark.id },
            }"
            >leave a review</router-link
          >
        </button>
      </div>

      <div class="likeDiv">
        <button class="buttons">
          likes: {{ retrieveLikes }}
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import landmarkservice from "../services/LandmarkService.js";
export default {
  name: "landmark-details",
  props: ["landmarkId"],
  data() {
    return {
      landmarkAddedSuccess: false,
      itineraryContains: false,
      isLoading: true,
      
    };
  },
  computed: {
    retrieveLikes(){
      return this.$store.state.currentLandmark.likes;
    }
  }, 
  methods: {
    setDetails() {
      landmarkservice.getDetails(this.$route.params.id).then((response) => {
        this.$store.commit("SET_LANDMARK", response.data);
        this.isLoading = false;
      });
    },
    addToItinerary() {
      const itinerary = (this.$store.state.itineraryLandmarks.filter(element => {
        return element.id === this.$store.state.currentLandmark.id;
      }));
      if (itinerary.length === 0) {
        this.$store.commit("SET_ITINERARY_LANDMARK",this.$store.state.currentLandmark);
        this.landmarkAddedSuccess = true;
      } else {
        this.itineraryContains = true;
      }
    },
    likeLandmark(){
      this.isLoading = true;
      landmarkservice.updateLandmarkLikes(this.$store.state.currentLandmark)
      .then(response => {
        this.$store.commit("SET_LANDMARK", response.data);
        this.isLoading = false;
      })
    }, 
    reloadPage(){
      window.location.reload();
    },
  },
  mounted() {
    this.setDetails();
  },
};
</script>

<style>
body {
  margin: 0;
  padding: 0;
}
.mainDiv {
  background-color: #004e64;

  /* display: grid;
  grid-template-columns: repeat(2, 1fr);
  grid-template-rows: repeat(3, 1fr);
  grid-template-areas:
    "img desc"
    " . . "
    "footer footer";

  height: 100vh;
  width: 100vw; */

  display: flex;
  flex-direction: column;
}

#img-title-details {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  padding: 20px;
  gap: 2rem;
  align-items: center;
  justify-content: center;
}

#title-desc {
  width: 700px;
}

.imgDiv {
  height: 500px;
  width: 850px;
}

#imgId {
  /* grid-area: img; */
  height: 100%;
  width: 100%;
  /* margin-left: 8vh;
  margin-top: 8vh; */
  border-radius: 20%;
}

#add-itinerary-Btn:hover {
  cursor: pointer;
}

#button-container {
  /* grid-area: footer; */
  display: flex;
  justify-content: space-evenly;
  flex-wrap: wrap;
}

#homeLink {
  text-decoration-line: none;
  color: #f3fced;
}
#reviewLink {
  text-decoration-line: none;
  color: #f3fced;
}

.descDiv {
  /* grid-area: desc; */
  font-family: "Montserrat Alternates", "Franklin Gothic Medium", "Arial Narrow",
    "Arial";
  font-size: 25px;
  color: #f3fced;
  /* margin-top: 20%;
  margin-left: 0%;
  margin-right: 10%; */
  display: flex;
  flex-direction: column;
}

.titleDiv {
  /* grid-area: desc; */
  display: inline-block;
  font-family: "Montserrat Alternates", "Franklin Gothic Medium", "Arial Narrow",
    "Arial";
  font-size: 180%;
  text-decoration-line: underline;
  text-decoration-color: #f3fced;
  text-underline-offset: 20px;
  text-align: left;
  /* margin-top: 7%; */
  /* margin-right: 10px; */
}
#landmarkTitle {
  color: #f3fced;
}

.like-dislike-btn{
  display: flex;
  gap: 2rem;
  justify-content: center;
  align-items: center;
  margin-top: 20px;
  margin-bottom: 10px;
}

.like-btn{
  color: #004e64;
  background: #f3fced;
  border-color: #004e64;
  cursor: pointer;
  border-radius: 9px;
  font-weight: 600;
  font-size: 14px;
  height: 40px;
  width: 100px;
  font-family: "Montserrat Alternates", "Franklin Gothic Medium", "Arial Narrow",
    "Arial";
}

.dislike-btn{
  color: #004e64;
  background: #f3fced;
  border-color: #004e64;
  cursor: pointer;
  border-radius: 9px;
  font-weight: 600;
  font-size: 14px;
  height: 40px;
  width: 100px;
  font-family: "Montserrat Alternates", "Franklin Gothic Medium", "Arial Narrow",
    "Arial";
}

.buttons {
  background-color: #bd92dd;
  color: #f3fced;
  height: 60px;
  width: 250px;
  border-radius: 20px;
  text-decoration-line: none;
  font-size: 25px;
  border: none;
  font-family: "Montserrat Alternates", "Franklin Gothic Medium", "Arial Narrow",
    "Arial";
  margin: 10px;
}

.landmark-status {
  border-radius: 5px;
  font-weight: bold;
  text-align: center;
  padding: 10px;
  width: 350px;
  margin: 0 auto 10px;
  color: #004e64;
  font-family: "Montserrat Alternates", "Franklin Gothic Medium", "Arial Narrow",
    "Arial";
}
.landmark-status.success {
  background-color: #90ee90;
}

.landmark-status.failure{
  background-color: #F08080;
}

.loading{
  flex: 3;
}

</style>