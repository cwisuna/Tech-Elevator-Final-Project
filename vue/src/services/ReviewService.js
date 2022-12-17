import axios from "axios";

export default {
    addReview(review, landmarkId){
        return axios.post(`/review/new/${landmarkId}`, review);
    },
    listReviews(landmarkId){
        return axios.get(`/review/list/${landmarkId}`);
    }
}