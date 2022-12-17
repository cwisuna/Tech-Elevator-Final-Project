import axios from 'axios';

export default{
    listItineraries(){
        return axios.get('/itinerary/list');
    },
    getItinerary(id){
        return axios.get(`/itinerary/${id}`);
    },
    saveItinerary(hotelId, itinerary){
        return axios.post(`/itinerary/new/${hotelId}`, itinerary);
    },
    removeItinerary(id){
        return axios.delete(`/itinerary/${id}`);
    }
}