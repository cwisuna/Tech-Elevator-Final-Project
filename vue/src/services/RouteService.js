import axios from "axios";

export default {
    retrieveRoute(Landmarks){
        return axios.post('/route', Landmarks)
    }
}