import axios from 'axios';

export default{
    listHotels(){
        return axios.get('/hotel/list');
    },
    getHotel(id){
        return axios.get(`/hotel/${id}`)
    }
}