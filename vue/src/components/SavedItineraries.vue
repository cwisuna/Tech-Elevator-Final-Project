<template>
  <div>
    <base-modal v-if="isSuccessful">
      <h3>Itinerary Deleted</h3>
      <button class="modal-button" @click="reloadPage()">Okay</button>
    </base-modal>
    <div class="saved-itineraries-page">
      <h1>Itineraries</h1>
      <div class="landmark-status success" v-show="itineraryDeleted">Itinerary deleted.</div>
      
      <div class="itinerary-container" v-for="itinerary in this.$store.state.itineraries" :key="itinerary.id">
        <div class="title-remove-container">
          <h2>Itinerary: {{ counter(itinerary) }}</h2>
          <button class="delete-itinerary-btn" v-on:click="deleteItinerary(itinerary.id)">
            Delete Itinerary
          </button>
        </div>
        <div class="hotel-container">
          <h3>Hotel: {{ itinerary.hotel.name }}</h3>
          <p>
            Hotel Address: {{ itinerary.hotel.address.street }}
            {{ itinerary.hotel.address.city }},
            {{ itinerary.hotel.address.state }}
            {{ itinerary.hotel.address.zip }}
          </p>
        </div>
        <div class="landmark-container" v-for="landmark in itinerary.landmarks" :key="landmark.id">
          <div class="img-div">
            <img v-bind:src="landmark.img_URL" alt="Landmark photo" />
          </div>
          <div class="landmark-text-container">
            <h3>{{ landmark.name }}</h3>
            <h5>Type: {{ landmark.type.name }}</h5>
            <p>{{ landmark.description }}</p>
            <p>
              Address: {{ landmark.address.street }}
              {{ landmark.address.city }},
              {{ landmark.address.state }}
              {{ landmark.address.zip }}
            </p>
          </div>
        </div>
      </div>

    </div>
  </div>
</template>

<script>
import ItineraryService from "../services/ItineraryService";
import BaseModal from "../components/BaseModal.vue";

export default {
  name: "saved-itineraries",
  components: {
    BaseModal,
  },
  data() {
    return {
      isSuccessful: false,
      itineraryDeleted: false,
      hotelId: 0,
      hotelName: "",
      hotelAddress: "",
    };
  },
  created() {
    this.listItineraries();
  },
  methods: {
    listItineraries() {
      ItineraryService.listItineraries().then((response) => {
        this.$store.commit("SET_ITINERARIES", response.data);
      });
    },
    deleteItinerary(id) {
      ItineraryService.removeItinerary(id).then((response) => {
        if (response.status === 200) {
          this.isSuccessful = true;
        } else {
          alert("Itinerary was not deleted.");
        }
      });
    },
    counter(itinerary) {
      let itineraries = this.$store.state.itineraries;
      return itineraries.indexOf(itinerary) + 1;
    },
    reloadPage() {
      window.location.reload();
    },
    setHotel() {
      if (this.hotelId === 1) {
        this.hotelName = "The Marquette Hotel, Curio Collection by Hilton";
        this.hotelAddress = "";
      } else if (this.hotelId === 2) {
        this.hotelName = "Hyatt Place Minneapolis/Downtown";
        this.hotelAddress = "";
      } else if (this.hotelId === 3) {
        this.hotelName = "Hewing Hotel";
        this.hotelAddress = "";
      }
    },
  },
};
</script>

<style>
.itinerary-container {
  display: flex;
  flex-direction: column;
  justify-content: left;
  align-items: left;
  overflow: hidden;

  border-radius: 7px;
  padding: 10px 40px;
  border: 2px #004e64 solid;
  margin: 10px 10px;
  color: #f3fced;
  margin: 20px 50px;
  font-family: "Montserrat Alternates", "Franklin Gothic Medium", "Arial Narrow",
    "Arial";

  background-color: #004e64;

  box-shadow: 10px 10px 20px #bebebe, -10px -10px 20px #ffffff;
}

.title-remove-container {
  display: flex;
  justify-content: space-between;
}

.title-remove-container h2 {
  color: #f3fced;
}

.hotel-container {
  margin: 2px 50px;
}

.landmark-container {
  display: flex;
  gap: 3rem;
  flex-wrap: wrap;

  margin: 2px 50px;
  padding: 15px 1px;
  border-top: 1px solid;
}

.landmark-text-container {
  max-width: 900px;
}

.delete-itinerary-btn {
  border: 5px rgb(228, 96, 96) solid;
  padding: 8px;
  background-color: rgb(228, 96, 96);
  color: #f3fced;
  border-radius: 12px;
  font-family: "Montserrat Alternates", "Franklin Gothic Medium", "Arial Narrow",
    "Arial";
}

.delete-itinerary-btn:hover {
  cursor: pointer;
}

.img-div {
  height: 300px;
}

.img-div img {
  height: 100%;
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
  border: 2px solid #004e64;
}
button:hover.modal-button {
  background-color: #1fd6c1;
  transform: scale(1.2);
  box-shadow: 0 0 4px #111;
  transition: 0.3s;
}
</style>