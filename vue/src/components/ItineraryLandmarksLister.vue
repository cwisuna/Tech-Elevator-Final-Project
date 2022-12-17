<template>
  <div>
    <base-modal v-if="isSuccessful">
      <h3>Itinerary Saved</h3>
      <button class="modal-button" @click="routeToSaved()">Okay</button>
    </base-modal>
    <div id="itinerary-container">
      <h1>Your Itinerary</h1>
      <Map />
      <div class="remove-status success" v-show="landmarkDeleted">Landmark successfully removed</div>
      <div class="remove-status success" v-show="itineraryDeleted">Itinerary successfully reset</div>
      
      <div class="loading" v-if="isLoading">
        <img src="../assets/paper-plane.gif" />
      </div>

      <div
        id="landmarks"
        v-for="landmark in this.$store.state.itineraryLandmarks"
        v-bind:key="landmark.id"
      >
        <div id="title-btn-container">
          <h2 id="landmark-name">{{ landmark.name }}</h2>
          <button id="removeBtn" v-on:click="removeFromItinerary(landmark.id)">
            Remove
          </button>
        </div>
        <div id="desc-img-container">
          <div id="img-container">
            <img id="landmark-img" :src="landmark.img_URL" alt="Landmark Img" />
          </div>
          <p id="landmark-description">{{ landmark.description }}</p>
        </div>
      </div>
      <div id="hotel-selection">
        <label for="hotel-search">Hotel</label>
        <select
          class="hotel-search"
          id="hotel-search"
          v-model="hotelStringId"
          v-on:click="setHotelId"
        >
          <option value="">--- Select your hotel ---</option>
          <option value="1">The Marquette Hotel</option>
          <option value="2">Hyatt Place Minneapolis/Downtown</option>
          <option value="3">Hewing Hotel</option>
          <option value="4">Hotel Ivy</option>
          <option value="5">The Westin Minneapolis</option>
          <option value="6">Hyatt Regency Minneapolis</option>
          <option value="7">Millennium Minneapolis</option>
          <option value="8">Aloft Minneapolis</option>
          <option value="9">Radisson RED Minneapolis Downtown</option>
          <option value="10">Hilton Minneapolis</option>
          <option value="11">The Royal Sonesta Minneapolis Downtown</option>
          <option value="12">Four Seasons Hotel Minneapolis</option>
          <option value="13">Minneapolis Marriott City Center</option>
          <option value="14">
            Embassy Suites by Hilton Minneapolis Downtown
          </option>
          <option value="15">Graduate Minneapolis</option>
          <option value="16">Moxy Minneapolis Uptown</option>
          <option value="17">Rand Tower Hotel, Minneapolis</option>
          <option value="18">Hyatt Centric Downtown Minneapolis</option>
          <option value="19">The Foshay</option>
          <option value="20">Renaissance Minneapolis Hotel</option>
          <option value="21">Hampton Inn & Suites Minneapolis/Downtown</option>
        </select>
        <div id="current-hotel-container" v-if="hotelSelected">
          <h4>{{ this.$store.state.currentHotel.name }}</h4>
          <p>
            {{ this.$store.state.currentHotel.address.street }}
            {{ this.$store.state.currentHotel.address.city }},
            {{ this.$store.state.currentHotel.address.state }}
            {{ this.$store.state.currentHotel.address.zip }}
          </p>
        </div>
      </div>
      <div class="saved-itinerary-status failure" v-show="noHotelSelected">
        Itinerary was not saved. Please select a hotel.</div>
      <div id="delete-save-container">
        <button id="resetBtn" v-on:click="reset()">Reset Itinerary</button>
        <button id="save-itinerary-btn" v-on:click="saveItinerary">
          Save Itinerary
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import Map from "../components/Map.vue";
import HotelService from "../services/HotelService";
import ItineraryService from "../services/ItineraryService";
import BaseModal from "../components/BaseModal.vue";

export default {
  name: "itinerary-landmarks-lister",
  components: {
    Map,
    BaseModal,
  },
  data() {
    return {
      isSuccessful: false,
      hotelStringId: "",
      hotelSelected: false,
      landmarkDeleted: false,
      itineraryDeleted: false,
      noHotelSelected: false,
      isLoading: true,
      itinerary: {
        userId: this.$store.state.user.id,
        hotelId: 0,
        landmarks: this.$store.state.itineraryLandmarks,
      },
    };
  },
  created() {
    this.retrieveHotels();
    this.itineraryLandmarks();
  },
  methods: {
    removeFromItinerary(id) {
      this.$store.commit("REMOVE_ITINERARY_LANDMARK", id);
      this.landmarkDeleted = true;
    },
    reset() {
      this.$store.commit("EMPTY_ITINERARY_LANDMARKS");
      this.landmarkDeleted = false;
      this.itineraryDeleted = true;
      this.hotelStringId = "";
      this.hotelSelected = false;
    },
    saveItinerary() {
      //pass in landmarks to send to back in
      if (this.hotelSelected === true){
        ItineraryService.saveItinerary(
        this.itinerary.hotelId,
        this.itinerary
      ).then((response) => {
        this.$store.commit("SET_ITINERARIES", response.data);
        this.isSuccessful = true;
        this.reset();
      })
      } else if (this.hotelSelected === false){
        this.noHotelSelected = true;
      }
    },
    retrieveHotels() {
      HotelService.listHotels().then((response) => {
        this.$store.commit("SET_HOTELS", response.data);
        this.isLoading = false;
      });
    },
    retrieveCurrentHotel() {
      this.isLoading = true;
      HotelService.getHotel(this.itinerary.hotelId).then((response) => {
        this.$store.commit("SET_CURRENT_HOTEL", response.data);
      });
      this.hotelSelected = true;
      this.isLoading = false;
    },
    routeToSaved() {
      this.$router.push({ name: "savedItineraries" });
    },
    setHotelId() {
      if (this.hotelStringId === "1") {
        this.itinerary.hotelId = 1;
      } else if (this.hotelStringId === "2") {
        this.itinerary.hotelId = 2;
      } else if (this.hotelStringId === "3") {
        this.itinerary.hotelId = 3;
      } else if (this.hotelStringId === "4") {
        this.itinerary.hotelId = 4;
      } else if (this.hotelStringId === "5") {
        this.itinerary.hotelId = 5;
      } else if (this.hotelStringId === "6") {
        this.itinerary.hotelId = 6;
      } else if (this.hotelStringId === "7") {
        this.itinerary.hotelId = 7;
      } else if (this.hotelStringId === "8") {
        this.itinerary.hotelId = 8;
      } else if (this.hotelStringId === "9") {
        this.itinerary.hotelId = 9;
      } else if (this.hotelStringId === "10") {
        this.itinerary.hotelId = 10;
      } else if (this.hotelStringId === "11") {
        this.itinerary.hotelId = 11;
      } else if (this.hotelStringId === "12") {
        this.itinerary.hotelId = 12;
      } else if (this.hotelStringId === "13") {
        this.itinerary.hotelId = 13;
      } else if (this.hotelStringId === "14") {
        this.itinerary.hotelId = 14;
      } else if (this.hotelStringId === "15") {
        this.itinerary.hotelId = 15;
      } else if (this.hotelStringId === "16") {
        this.itinerary.hotelId = 16;
      } else if (this.hotelStringId === "17") {
        this.itinerary.hotelId = 17;
      } else if (this.hotelStringId === "18") {
        this.itinerary.hotelId = 18;
      } else if (this.hotelStringId === "19") {
        this.itinerary.hotelId = 19;
      } else if (this.hotelStringId === "20") {
        this.itinerary.hotelId = 20;
      } else if (this.hotelStringId === "21") {
        this.itinerary.hotelId = 21;
      }

      this.retrieveCurrentHotel();
    },
  },
};
</script>

<style>
#itinerary-container {
  border: 2px #129d8d solid;
  margin: 20px 30px;
  padding: 1%;
  border-radius: 42px;
}

h1 {
  text-align: center;
  color: #004e64;
  font-family: "Montserrat Alternates", "Franklin Gothic Medium", "Arial Narrow",
    "Arial", "sans-serif";
}

#landmarks {
  display: flex;
  flex-direction: column;
  margin: 10px 10px;
  padding: 1%;
}

#title-btn-container {
  display: flex;
  justify-content: space-between;
}

#desc-img-container {
  display: flex;
  justify-content: space-between;
}

#landmark-description {
  font-family: "Montserrat Alternates", "Franklin Gothic Medium", "Arial Narrow",
    "Arial";
  color: #004e64;
}

#landmark-img {
  padding: 2px;
  margin-right: 10px;
  height: 10rem;
}

#removeBtn {
  font-family: "Montserrat Alternates", "Franklin Gothic Medium", "Arial Narrow",
    "Arial";
  border: 5px solid;
  padding: 8px;
  background-color: #004e64;
  color: #f3fced;
  border-radius: 12px;
}

#removeBtn:hover {
  cursor: pointer;
}

#hotel-selection {
  font-family: "Montserrat Alternates", "Franklin Gothic Medium", "Arial Narrow",
    "Arial";
  text-align: center;
  margin-top: 5%;
  color: #004e64;
}

#delete-save-container {
  display: flex;
  justify-content: center;
  align-items: center;
}

#resetBtn {
  font-family: "Montserrat Alternates", "Franklin Gothic Medium", "Arial Narrow",
    "Arial";
  border: 5px solid;
  padding: 8px;
  background-color: #004e64;
  color: #f3fced;
  border-radius: 12px;
  margin-top: 10px;
}

#resetBtn:hover {
  cursor: pointer;
}

#save-itinerary-btn {
  font-family: "Montserrat Alternates", "Franklin Gothic Medium", "Arial Narrow",
    "Arial";
  border: 5px solid;
  padding: 8px;
  background-color: #004e64;
  color: #f3fced;
  border-radius: 12px;
  margin-top: 10px;
}

#save-itinerary-btn:hover {
  cursor: pointer;
}

#go-itinerary-btn {
  font-family: "Montserrat Alternates", "Franklin Gothic Medium", "Arial Narrow",
    "Arial";
  border: 5px solid;
  padding: 8px;
  background-color: #004e64;
  color: #f3fced;
  border-radius: 12px;
  margin-top: 10px;
}

#go-itinerary-btn:hover {
  cursor: pointer;
}

.remove-status {
  border-radius: 5px;
  font-weight: bold;
  text-align: center;
  padding: 10px;
  width: 350px;
  margin: 10px auto 10px;
  font-family: "Montserrat Alternates", "Franklin Gothic Medium", "Arial Narrow",
    "Arial";
  color: #004e64;
}

.remove-status.success {
  background-color: #90ee90;
}

.saved-itinerary-status{
  border-radius: 5px;
  font-weight: bold;
  text-align: center;
  padding: 10px;
  width: 350px;
  margin: 10px auto 10px;
  font-family: "Montserrat Alternates", "Franklin Gothic Medium", "Arial Narrow",
    "Arial";
  color: #004e64;
}

.saved-itinerary-status.failure{
  background-color: rgb(228, 104, 104);
}

.itinerary-saved.success {
  border-radius: 5px;
  font-weight: bold;
  text-align: center;
  padding: 10px;
  width: 350px;
  margin: 10px auto 10px;
  font-family: "Montserrat Alternates", "Franklin Gothic Medium", "Arial Narrow",
    "Arial";
  color: #004e64;
  background-color: #90ee90;
}

.loading {
  flex: 3;
}

button.modal-button {
  width: 200px;
  height: 4rem;
  padding: 0.75rem 1.5rem;
  background-color: transparent;
  border-radius: 10px;
  font-family: inherit;
  text-align: center;
  cursor: pointer;
  box-shadow: 0 0 10px #333;
  overflow: hidden;
  transition: 0.3s;
  border: 2px solid #004E64;
}
button:hover.modal-button {
  background-color: #1fd6c1;
  transform: scale(1.2);
  box-shadow: 0 0 4px #111;
  transition: 0.3s;
}
</style>

