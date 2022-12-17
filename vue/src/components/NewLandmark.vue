<template>
  <div>
    <base-modal v-if="isSuccessful">
      <h3>New Landmark Form Submitted</h3>
      <button class="modal-button" @click="routeToHome()">Okay</button>
    </base-modal>
    
    <div id="new-landmark-container">
      <link
        rel="stylesheet"
        href="https://fonts.googleapis.com/css?family=Montserrat Alternates"
      />
      <div>
        <h2 id="page-title">Submit a New Landmark for Consideration</h2>
      </div>

      <div class="status-message error" v-show="formAddedFailure">{{errorMessage}}</div>

      <div class="form-container">
        <form class="landmark-form" v-on:submit.prevent="saveLandmark">
          <div class="form-group">
            <label for="name">Name</label>
            <input
              class="form-control"
              id="name"
              type="text"
              placeholder="Enter Landmark Name"
              v-model="newLandmark.name"
            />
          </div>
          <div class="form-group">
            <label for="type">Type</label>
            <select
              class="form-control"
              id="type"
              v-on:click="setType"
              v-model="typeId"
            >
              <option value="">Select Type</option>
              <option value="5">Amusement</option>
              <option value="1">Art</option>
              <option value="2">Educational</option>
              <option value="7">Historic</option>
              <option value="4">Outdoor</option>
              <option value="8">Restaurant</option>
              <option value="3">Shopping</option>
              <option value="6">Venue</option>
            </select>
          </div>
          <div class="form-group">
            <!-- <label for="imgUrl">Image URL</label>
            <input
              class="form-control"
              id="imgUrl"
              type="url"
              placeholder="Enter Image Address"
              v-model="newLandmark.imgUrl"
            /> -->
          </div>
          <div class="form-group">
            <label for="description">Description</label>
            <textarea
              class="form-control"
              id="description"
              rows="3"
              v-model="newLandmark.description"
            ></textarea>
          </div>

          <label id="addressLabel" for="address">Landmark Address:</label>
          <div id="address" class="form-group">
            <div>
              <label for="street">Street</label>
              <input
                class="form-control"
                id="street"
                type="text"
                placeholder="street"
                v-model="newLandmark.address.street"
              />
            </div>
            <div>
              <label for="city">City</label>
              <input
                class="form-control"
                type="text"
                id="city"
                placeholder="city"
                v-model="newLandmark.address.city"
              />
            </div>
            <div>
              <label for="state">State</label>
              <input
                class="form-control"
                type="text"
                id="state"
                placeholder="ex: MN"
                v-model="newLandmark.address.state"
              />
            </div>
            <div>
              <label id="zip-title" for="zip">Zip Code</label>
              <input
                class="form-control"
                type="number"
                id="zip"
                placeholder="ex: 55401"
                v-model="newLandmark.address.zip"
              />
            </div>
          </div>

          <div>
            <button id="btnAddLandmark" type="submit">Add Landmark</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import LandmarkService from "../services/LandmarkService";
import BaseModal from '../components/BaseModal.vue'

export default {
  components: {
    BaseModal
  },
  data() {
    return {
      typeId: "",
      newLandmark: {
        name: "",
        type: {
          id: "",
          name: "",
        },
        imgUrl: "",
        description: "",
        address: {
          street: "",
          city: "",
          state: "",
          zip: "",
        },
        pending: true,
      },
      isSuccessful: false,
      formAddedFailure: false,
      errorMessage: "",
    };
  },
  methods: {
    saveLandmark() {
      LandmarkService.addLandmark(this.newLandmark)
        .then((response) => {
          //200 is being returned
          if (response.status === 200) {
            this.isSuccessful = true;
          }
        })
        .catch((error) => {
          this.handleErrorResponse(error, "submitting");
          this.formAddedFailure = true;
        });
    },
    routeToHome() {
      this.$router.push({ name: "home" });
    },
    resetForm() {
      this.newLandmark = {};
    },
    setType() {
      if (parseInt(this.typeId) === 1) {
        this.newLandmark.type.id = 1;
        this.newLandmark.type.name = "Art";
      } else if (parseInt(this.typeId) === 2) {
        this.newLandmark.type.id = 2;
        this.newLandmark.type.name = "Educational";
      } else if (parseInt(this.typeId) === 3) {
        this.newLandmark.type.id = 3;
        this.newLandmark.type.name = "Shopping";
      } else if (parseInt(this.typeId) === 4) {
        this.newLandmark.type.id = 4;
        this.newLandmark.type.name = "Outdoor";
      } else if (parseInt(this.typeId) === 5) {
        this.newLandmark.type.id = 5;
        this.newLandmark.type.name = "Amusement";
      } else if (parseInt(this.typeId) === 6) {
        this.newLandmark.type.id = 6;
        this.newLandmark.type.name = "Venue";
      } else if (parseInt(this.typeId) === 7) {
        this.newLandmark.type.id = 7;
        this.newLandmark.type.name = "Historic";
      } else if (parseInt(this.typeId) === 8) {
        this.newLandmark.type.id = 8;
        this.newLandmark.type.name = "Restaurant";
      }
    },
    handleErrorResponse(error, verb) {
      if (error.response) {
        this.errorMessage = "";
        if (error.response.status === 500) {
          this.errorMessage =
            "Error " + verb + " landmark. An internal server error occurred.";
        } else if (error.response.status === 404) {
          this.errorMessage =
            "Error " + verb + " landmark. URL could not be found.";
        } else {
          this.errorMessage =
            "Error " +
            verb +
            " landmark. Response received was '" +
            error.response.statusText +
            "'.";
        }
      } else if (error.request) {
        this.errorMessage =
          "Error " + verb + " landmark. Server could not be reached.";
      } else {
        this.errorMessage =
          "Error " + verb + " landmark. Request could not be created.";
      }
    },
  },
};
</script>

<style>
body {
  background-color: #f3fced;
}

#new-landmark-container {
  border-top: #1fd6c1 5px solid;
  font-family: "Montserrat Alternates", "Franklin Gothic Medium", "Arial Narrow",
    "Arial", "sans-serif";
  color: #004e64;

  display: flex;
  flex-direction: column;
  align-items: center;
}

h2 {
  text-align: center;
  width: 100vw;
  justify-self: center;
}

.note {
  padding: 10px;
  background-color: #004e64;
  border-radius: 8px;
  color: #f3fced;
}

.landmark-form div {
  display: flex;
  justify-content: center;
}

#description {
  margin-bottom: 10px;
}

#addressLabel {
  margin-top: 10px;
  text-align: center;
  font-weight: bold;
}

#address {
  display: block;
  text-align: center;
}

#address div {
  padding: 5px;
}

#address div label {
  margin-right: 10px;
}

#btnAddLandmark {
  padding: 10px;
  background-color: #004e64;
  border-radius: 12px;
  color: #f3fced;
  font-family: "Montserrat Alternates", "Franklin Gothic Medium", "Arial Narrow",
    "Arial", "sans-serif";
  cursor: pointer;
  border: #004e64;
}

.status-message {
  border-radius: 5px;
  font-weight: bold;
  text-align: center;
  padding: 10px;
  width: 350px;
  margin: 0 auto 10px;
}

.status-message.error {
  background-color: #f08080;
}
.form-container {
  border: 5px #004e64 solid;
  border-radius: 15%;
  background: rgb(246, 242, 242);
  width: 650px;
  display: grid;
  grid-template-columns: repeat(36, 10px);
  grid-template-rows: repeat(60, 10px);
}

.landmark-form {
  grid-column-start: 12;
  grid-row-start: 8;
  width: 500px;
  text-align: center;
}
#zip-title {
  width: 105px;
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