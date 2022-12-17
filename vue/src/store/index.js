import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if(currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    landmarkStyles: [],
    landmarks: [],
    currentLandmark: [],
    pendingLandmarks: [],
    itineraryLandmarks: [],
    routes: [],
    reviews: [],
    hotels: [],
    currentHotel: [],
    itineraries: []
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user',JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },
    SET_ITINERARIES(state, list){
      state.itineraries = list;
    },
    SET_LANDMARKS(state, list)  {
      state.landmarks = list.filter(landmark => {
        return landmark.pending === false;
      });
    },
    SET_PENDING_LANDMARKS(state, list){
      state.pendingLandmarks = list.filter(landmark => {
        return landmark.pending === true;
      });
    },
    SET_PENDING_LANDMARK(state, landmark){
      state.pendingLandmarks.push(landmark);
    },
    SET_LANDMARK(state, landmark) {
      state.currentLandmark = landmark;
    },
    SET_CURRENT_HOTEL(state, hotel){
      state.currentHotel = hotel;
    },
    SET_HOTELS(state, list){
      state.hotels = list;
    },
    SET_ITINERARY_LANDMARK(state, landmark) {
      state.itineraryLandmarks.push(landmark);
    },
    REMOVE_ITINERARY_LANDMARK(state, landmarkId) {
     state.itineraryLandmarks = state.itineraryLandmarks.filter(landmark => {
            return landmark.id != landmarkId;
      })
    },
    EMPTY_ITINERARY_LANDMARKS(state) {
      state.itineraryLandmarks = [];
    },
    SET_ROUTES(state, list){
      state.routes = list;
    },
    SET_REVIEWS(state, list){
      state.reviews = list;
    },
  }
})
