<template>
<div class="home-header">
     <link rel="stylesheet" href='https://fonts.googleapis.com/css?family=Montserrat Alternates'>

    <div id="header" class="home-element">
      <router-link id="title-img-router" v-bind:to="{name: 'home'}" >
        <div id="title-img">
          <h1 id="title">Glider</h1>
          <img id="glider-img" src="..\assets\Glider-1.png" alt="Glider Image" >
        </div>
      </router-link>

      <div id="right-side">

        <div id="buttons">

          <router-link v-if="isAdmin" :to="{ name: 'pendingLandmarks' }">
            <button class="button" id="pending-landmarks">Pending Landmarks</button>
          </router-link>

          <router-link :to="{ name: 'savedItineraries' }">
            <button class="button" id="saved-itineraries">Saved Itineraries</button>
          </router-link>

          <router-link v-bind:to="{name: 'itinerary'}">
            <button class="button" id="itinerary">View Itinerary</button>
          </router-link>

          <router-link v-bind:to="{name: 'addLandmark'}">
            <button class="button" id="addLandmark">Add Landmark</button>
          </router-link>

        </div>

        <div id="dropdown" class="dropdown">
          <img src="https://i.ibb.co/TK8GSbw/Screenshot-28.png" alt="Profile" id="profile-img">
          <router-link id="router-link" v-bind:to="{ name: 'logout' }" >
            <div class="dropdown-content">
            Logout
          </div>
          </router-link>
        </div>
      </div>
    </div>
    
   </div>


</template>

<script>
export default {
  computed: {
    isAdmin() {
      return this.$store.state.user.authorities[0].name === 'ROLE_ADMIN';
    }
  }
}
</script>


<style>


#title-img {
  display: flex;
}

#title {
  align-self: center;
  font-family: 'Montserrat Alternates';
  color: #129D8D;
}

#glider-img {
  display: inline-block;
  height: 4.0em;
  margin: auto;
}

#header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-family: 'Montserrat Alternates', 'Franklin Gothic Medium', 'Arial Narrow', 'Arial';
  padding-left: 10px;
  padding-right: 10px;
}

.button{
  border: 5px solid;
  padding: 8px;
  background-color: #004E64;
  color: #F3FCED;
  border-radius: 12px;
  font-family: 'Montserrat Alternates', 'Franklin Gothic Medium', 'Arial Narrow', 'Arial';
}

.button:hover {
  cursor: pointer;
}

#profile-img {
  height: 3.6rem;
  border-radius: 40px;
  border-width: 50px;
}

.dropdown {
  display: inline-block;
  position: relative;
  text-decoration-line: none;
}

.dropdown-content {
  display: none;
  position: absolute;
  width: 105%;
  overflow: auto;
  background-color: #1fd6c1;
}

.dropdown:hover .dropdown-content {
  display: block;
}

#router-link {
  color: #F3FCED;
}

#title-img-router {
  text-decoration: none;
}

#right-side {
  display: flex;
  align-items: center;
}

</style>