<template>
  <div id="reviews">
      <h2 id="reviews-title">Reviews</h2>
      <div id="review-container" v-for="review in this.$store.state.reviews" v-bind:key="review.id">
          <h3>{{review.title}}</h3>
          <h4>Reviewer: {{review.username}}</h4>
          <p v-if="review.is_liked">Liked</p>
          <p v-else>Disliked</p>
          <p>{{review.description}}</p>
      </div>
  </div>
</template>

<script>
import ReviewService from '../services/ReviewService';

export default {
    name: "reviews",
    created() {
        this.retrieveReviews();
    },
    methods: {
        retrieveReviews(){
            ReviewService.listReviews(this.$route.params.id)
            .then(response => {
              this.$store.commit("SET_REVIEWS", response.data);  
            })
            .catch(error => {
                if (error.response && error.response.status === 404) {
                    alert(
                        "Reviews not available. This landmark may not have any reviews or the ID is incorrect."
                    );
                    this.$router.push({ name: 'Home' });
          }
            });
        },
    },
    
}
</script>

<style>

#reviews{
    background-color:#004E64; 
    color: #F3FCED;
    padding: 10px;
}

#reviews-title {
    color: #F3FCED;
    width: 100%;
}

#review-container{
    font-family: 'Montserrat Alternates', 'Franklin Gothic Medium', 'Arial Narrow', 'Arial';
    border: 5px #BD92DD solid;
    border-radius: 12px;
    text-align: center;
    margin: 20px 100px;
    padding: 10px;
}


</style>