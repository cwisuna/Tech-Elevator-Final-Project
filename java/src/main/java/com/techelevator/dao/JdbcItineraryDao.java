package com.techelevator.dao;

import com.techelevator.model.Itinerary;
import com.techelevator.model.Landmark;
import com.techelevator.model.Type;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcItineraryDao implements ItineraryDao {

    private JdbcTemplate jdbcTemplate;
    private JdbcUserDao jdbcUserDao;
    private JdbcAddressDao jdbcAddressDao;
    private JdbcHotelDao jdbcHotelDao;

    public JdbcItineraryDao(JdbcTemplate jdbcTemplate, JdbcUserDao jdbcUserDao, JdbcAddressDao jdbcAddressDao, JdbcHotelDao jdbcHotelDao) {

        this.jdbcTemplate = jdbcTemplate;
        this.jdbcUserDao = jdbcUserDao;
        this.jdbcAddressDao = jdbcAddressDao;
        this.jdbcHotelDao = jdbcHotelDao;
    }

    //TODO fix sql
    //gets itinerary from database
    @Override
    public List<Itinerary> listItinerary(Principal principal){
        int userId = jdbcUserDao.findIdByUsername(principal.getName());

        List<Itinerary> list = new ArrayList<>();
        String sql = "SELECT itinerary.id, user_id, hotel_id " +
                " FROM itinerary " +
                " WHERE user_id = ?; ";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);

        while(result.next()){
            Itinerary itinerary = mapRowToIntinerary(result);

            String associativeSql = " SELECT landmark_id " +
                                    " FROM landmarks_itinerary " +
                                    " WHERE itinerary_id = ? ";
            SqlRowSet associativeResults = jdbcTemplate.queryForRowSet(associativeSql, itinerary.getItineraryId());

            while(associativeResults.next()){
                int landmarkId = associativeResults.getInt("landmark_id");

                String landmarkSql = "SELECT landmarks.id, address_id, landmarks.name, landmarks.type, types.name AS type_name, description, likes, img_URL, is_pending" +
                                    " FROM landmarks " +
                                    " JOIN types ON landmarks.type = types.id " +
                                    " WHERE landmarks.id = ? ";
                SqlRowSet landmarkResults = jdbcTemplate.queryForRowSet(landmarkSql, landmarkId);
                while(landmarkResults.next()){
                    Landmark landmark = mapRowToLandmark(landmarkResults);

                    //add appropriate landmark to list of landmarks
                    itinerary.addLandmark(landmark);
                }
            }
            list.add(itinerary);
        }
        return list;
    }

    //gets specific itinerary from database
    @Override
    public Itinerary getItinerary(int itineraryId, Principal principal){
        return listItinerary(principal).get(itineraryId-1);

    }

    //insert new itinerary into database
    @Override
    public int createItinerary(int hotelId, Itinerary itinerary, Principal principal){

        int userId = jdbcUserDao.findIdByUsername(principal.getName());

        String sql = " INSERT INTO itinerary(user_id, hotel_id) " +
                " VALUES(?, ?) RETURNING id ";

        int itineraryId = jdbcTemplate.queryForObject(sql, int.class, userId, hotelId);

        return itineraryId;
    }

    //inserts itinerary id and landmark ids into associative table
    @Override
    public void insertItineraryIntoAssociative(int itineraryId, List<Landmark> landmarks){
        for(Landmark landmark: landmarks){
            String sql = " INSERT INTO landmarks_itinerary (itinerary_id, landmark_id) " +
                    " VALUES(?, ?) ";
            jdbcTemplate.update(sql, itineraryId, landmark.getLandmarkId());
        }
    }

    //deletes the itinerary from landmarks_itinerary table
    @Override
    public void deleteItineraryFromAssociative(int itineraryId){

        String sql = " DELETE FROM landmarks_itinerary " +
                " WHERE itinerary_id = ? ";

        jdbcTemplate.update(sql, itineraryId);

    }

    //deletes the itinerary from itinerary table
    @Override
    public void deleteItinerary(int itineraryId){

        String sql = " DELETE FROM itinerary " +
                " WHERE id = ? ";

        jdbcTemplate.update(sql, itineraryId);

    }


    //adds landmark to itinerary
    @Override
    public void addLandmarkToItinerary(Itinerary itinerary, int landmarkId){
        if(itinerary == null) throw new IllegalArgumentException("Itinerary cannot be null");

        String sql = " INSERT INTO landmarks_itinerary (itinerary_id, landmark_id) " +
                    " VALUES(?, ?) ";

        jdbcTemplate.update(sql, itinerary.getItineraryId(), landmarkId);

    }

    //removes landmark from itinerary
    @Override
    public void removeLandmarkToItinerary(Landmark landmark, int itineraryId){
        if(landmark == null) throw new IllegalArgumentException("Landmark cannot be null");

        String sql = " DELETE FROM landmarks_itinerary " +
                " WHERE landmark_id = ? AND itinerary_id = ?  ";

        jdbcTemplate.update(sql, landmark.getLandmarkId(), itineraryId);

    }

    private Itinerary mapRowToIntinerary(SqlRowSet results){
        Itinerary itinerary = new Itinerary();

        itinerary.setItineraryId(results.getInt("id"));
        itinerary.setUserId(results.getInt("user_id"));
        itinerary.setHotel(jdbcHotelDao.getHotel(results.getInt("hotel_id")));


        return itinerary;
    }

    private Landmark mapRowToLandmark(SqlRowSet results){
      Landmark landmark = new Landmark();

      landmark.setLandmarkId(results.getInt("id"));
      landmark.setAddressId(results.getInt("address_id"));
      landmark.setLikes(results.getInt("likes"));
      landmark.setName(results.getString("name"));
      landmark.setType(mapRowToType(results));
      landmark.setDescription(results.getString("description"));
      landmark.setImgUrl(results.getString("img_url"));
      landmark.setPending(results.getBoolean("is_pending"));
      landmark.setAddress(jdbcAddressDao.getAddress(results.getInt("address_id")));

      return landmark;
    }

    private Type mapRowToType(SqlRowSet results){
        Type type = new Type();
        type.setName(results.getString("type_name"));
        type.setTypeId(results.getInt("type"));
        return type;
    }

}
