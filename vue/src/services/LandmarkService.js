import axios from "axios";

const http = axios.create({
    baseURL: "http://localhost:9000"
})

export default {

        list() {
            return http.get('/landmark/list');
        },
        getDetails(id){
            return http.get(`/landmark/${id}`);
        },
        addLandmark(landmark){
            //console.log(JSON.stringify(landmark));
            return axios.post('/landmark/new', landmark);
        },
        updateLandmarkLikes(landmark){
            return axios.put(`/landmark/${landmark.id}`, landmark);
        },
        setPendingToTrue(landmark){
            return axios.put('/pending/approve', landmark);
        }, 
        deleteLandmark(id){
            return http.delete(`/landmark/${id}/delete`);
        }
}