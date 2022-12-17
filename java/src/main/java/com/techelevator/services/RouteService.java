package com.techelevator.services;


import com.techelevator.model.Landmark;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Component
public class RouteService {

//    //TODO: confirm base URL is correct
//    private static final String API_URL = "https://api.openrouteservice.org/v2/directions/driving-car/";
//
//    //this is Layla's API key that we can use for every request
//    private static final String API_KEY = "5b3ce3597851110001cf624825acbbd93a1c4750ac7148d566eec1ad";
//
//    private RestTemplate restTemplate = new RestTemplate();
//
//    public RouteService(){}
//
//    public ArrayList<RouteAPI> createRoute(Landmark[] landmarks){
//        HttpEntity<Integer[][]> entity = makeRouteEntity(landmarks);
//
//        Root root = null;
//        ArrayList<RouteAPI> routeAPI = null;
//        try{
//            root = restTemplate.exchange(API_URL, HttpMethod.POST, entity, Root.class).getBody();
//            //TODO: decide what's needed here ObjectMapper or use restTemplate
////            ObjectMapper om = new ObjectMapper();
////            root = om.readValue(API_URL, Root.class);
//            routeAPI = root.getRoutes();
//
//        } catch (RestClientResponseException | ResourceAccessException e){
//            System.out.println(e.getMessage());
//        }
//        return routeAPI;
//    }
//
//    //Post and Put
//    //pass in a list of landmarks, loop through it to then make an array of coordinates which then gets passed into the entity as the body
//    private HttpEntity<Integer[][]> makeRouteEntity(Landmark[] landmarks) {
//        Coordinates coordinates = new Coordinates();
//        //TODO: check if this needs to be String[][] instead of int[][]
//        Integer[][] array = new Integer[landmarks.length][];
//        coordinates.setCoordinatesArray(array);
//
//        for (int i = 0; i < landmarks.length; i++) {
//            Integer[] intArray = new Integer[2];
//            String[] a = landmarks[i].getAddress().getLongLat().split(",");
//            intArray[0] = Integer.parseInt(a[0]);
//            intArray[1] = Integer.parseInt(a[1]);
//
//            array[i] = intArray;
//        }
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        //pass API_KEY as authorization
//        headers.add("Authorization", API_KEY);
//
//        return new HttpEntity<Integer[][]>(coordinates.getCoordinatesArray(), headers);
//    }



}
