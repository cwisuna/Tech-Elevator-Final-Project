<template>
  <div id="pending-landmarks-container">
    <div>
      <base-modal v-if="isSuccessful">
        <h3>Landmark Approved</h3>
        <button class="modal-button" @click="refreshPage()">Okay</button>
      </base-modal>
    </div>
    <h2>Pending Landmarks</h2>
    <div
      id="pending-landmark"
      v-for="landmark in this.$store.state.pendingLandmarks"
      :key="landmark.id"
    >
      <div class="landmark-info">
        <h3>{{ landmark.name }}</h3>
        <p>Type: {{ landmark.type.name }}</p>
        <p>{{ landmark.description }}</p>
        <p>
          Address: {{ landmark.address.street }} {{ landmark.address.city }},
          {{ landmark.address.state }}
          {{ landmark.address.zip }}
        </p>
      </div>
      <img
        class="pending-landmark-img"
        :src="landmark.img_URL"
        alt="landmark image"
      />
      <div class="approve-reject-btns">
        <button class="approve-btn" v-on:click="approveLandmark(landmark)">
          Approve Landmark
        </button>
        <button class="reject-btn" v-on:click="rejectLandmark(landmark.id)">
          Reject Landmark
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import landmarkService from "../services/LandmarkService";
import BaseModal from "../components/BaseModal.vue";

export default {
  components: {
      BaseModal
  },
  data(){
      return{
          isSuccessful: false
      }
  },
  created() {
    this.listPendingLandmarks();
  },
  methods: {
    listPendingLandmarks() {
      landmarkService.list().then((response) => {
        this.$store.commit("SET_PENDING_LANDMARKS", response.data);
      });
    },
    approveLandmark(landmark) {
      landmarkService.setPendingToTrue(landmark).then((response) => {
        if (response.status === 200) {
          this.isSuccessful = true;
        }
      });
    },
    rejectLandmark(id) {
      landmarkService.deleteLandmark(id).then((response) => {
        if (response.status === 200) {
          this.refreshPage();
        }
      });
    },
    refreshPage(){
        window.location.reload();
    }
  },
};
</script>

<style>
#pending-landmarks-container {
  border-top: #1fd6c1 5px solid;
  color: #004e64;
  font-family: "Montserrat Alternates", "Franklin Gothic Medium", "Arial Narrow",
    "Arial", "sans-serif";
  
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: left;
}

#pending-landmarks-container h2{
    text-align: center;

}

#pending-landmark {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  gap: 3rem;
  border-top: 1px #004e64 solid;

  margin: 5px 150px;
}

.approve-reject-btns {
  display: flex;
  flex-direction: column;
  gap: 1rem;
  margin-top: 30px;
  color: #004e64;
  font-family: "Montserrat Alternates", "Franklin Gothic Medium", "Arial Narrow",
    "Arial", "sans-serif";
}

.approve-reject-btns button {
  padding: 8px;
  border-radius: 12px;
  color: #f3fced;
  background-color: #004e64;
  font-family: "Montserrat Alternates", "Franklin Gothic Medium", "Arial Narrow",
    "Arial", "sans-serif";
}

.approve-reject-btns button:hover {
  cursor: pointer;
}

.pending-landmark-img {
  margin-top: 10px;
  height: 150px;
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