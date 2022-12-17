<template>
  <div id="login" class="text-center">
    <link rel="stylesheet" href='https://fonts.googleapis.com/css?family=Montserrat Alternates'>
    <form id="form-signin" class="form-signin" @submit.prevent="login">
      <div
        class="alert alert-danger form-element"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username and password!</div>
      <div
        class="alert alert-success form-element"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div>
      <input
        type="text"
        id="username"
        class="form-control form-element"
        placeholder="Username or Email"
        v-model="user.username"
        required
        autofocus
      />
      <input
        type="password"
        id="password"
        class="form-control form-element"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <button class="form-element login-button" type="submit">login</button>
      <button class="register-button"> <router-link class="form-element routerstyle" :to="{ name: 'register' }">join the club</router-link></button>
    </form>
     
    <img class="logo" src="../assets/Glider-3.png">
    
  
  </div>
 
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/home");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  }
};
</script>

<style>

body{
  background-color: #F3FCED;
}
div#login.text-center{
  height: 100vh;
  width: 100vw;
  margin: 0;
  display: flex;
  flex-direction: row-reverse;
  justify-content: space-evenly;
  flex-wrap: wrap-reverse;
}

img{
  height: 90vh;
  justify-self: center;
  align-self: center;
}

#form-signin{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-self: center;
  border: 5px #004E64 solid;
  border-radius: 10%;
  padding: 2%;
  background: rgb(246, 242, 242);
  width: 300px;
  height: 275px;
  margin-right: 50px;
}

.form-element {
  margin-top: 1.5rem;
  margin-bottom: 1.5rem;
  border-radius: 9px;
  
}

button.login-button{
  background-color: #004E64;
  border-radius: 12px;
  color: #F3FCED;
  font-family: 'Montserrat Alternates', 'Franklin Gothic Medium', 'Arial Narrow', 'Arial', 'sans-serif';
  font-size: 25px;
  margin-left: 20%;
  margin-right: 20%;
}

button.register-button{
  background-color: #1fd6c1;
  border-radius: 12px;
  color: #F3FCED;
  font-family: 'Montserrat Alternates', 'Franklin Gothic Medium', 'Arial Narrow', 'Arial', 'sans-serif';
  font-size: 25px;
  margin-left: 20%;
  margin-right: 20%;
}
.routerstyle{
  color: #F3FCED;
  text-decoration: none;
}




</style>
