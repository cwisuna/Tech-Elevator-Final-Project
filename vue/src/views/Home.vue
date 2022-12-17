<template>
  <div class="home">
    <HomeHeader></HomeHeader>
    <div id="filter" class="home-element">
      <h2 id="filter-names">Minneapolis Attractions</h2>
    </div>
    <div id="main" class="home-element">
      <landmarks />
      

    </div>
  </div>
</template>

<script>
  import HomeHeader from "../components/HomeHeader.vue";
  import Landmarks from '../components/Landmarks.vue'

  export default {
    name: "home",
    components: {
      HomeHeader,
      Landmarks
  }
};
</script>

<style>

#filter-names {
  color: #129D8D;
}

div.home {
  display: grid;
  grid-template-areas: 
  "header"
  "filter"
  "main";
  grid-template-rows: .1fr .1fr 1fr;
  font-family: 'Montserrat Alternates', 'Franklin Gothic Medium', 'Arial Narrow', 'Arial', 'sans-serif';
}

#filter {
  grid-area: filter;
}

#filter-names {
  width: 100%;
}

#main {
  grid-area: main;
}

</style>