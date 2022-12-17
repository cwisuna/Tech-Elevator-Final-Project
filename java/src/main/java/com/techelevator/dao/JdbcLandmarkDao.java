package com.techelevator.dao;

import com.techelevator.model.Address;
import com.techelevator.model.Landmark;
import com.techelevator.model.Review;
import com.techelevator.model.Type;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcLandmarkDao implements LandmarkDao{

    private JdbcTemplate jdbcTemplate;
    private JdbcAddressDao jdbcAddressDao;

    public JdbcLandmarkDao(JdbcTemplate jdbcTemplate, JdbcAddressDao jdbcAddressDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcAddressDao = jdbcAddressDao;
    }

    @Override
    public List<Landmark> listLandmarks(){
        List<Landmark> list = new ArrayList<>();
        String sql = "SELECT landmarks.id, address_id, landmarks.name, landmarks.type, types.name AS type_name, description, likes, img_URL, is_pending" +
                " FROM landmarks " +
                " JOIN types ON landmarks.type = types.id " +
                " ORDER BY landmarks.name ";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
        while(result.next()){
            Landmark landmark = mapRowToLandmark(result);

            //add appropriate address to each landmark
            String addressSql = " SELECT id, street, city, state, zip " +
                " FROM addresses " +
                " WHERE id = ?";
            SqlRowSet addressResult = jdbcTemplate.queryForRowSet(addressSql, landmark.getAddressId());
            if (addressResult.next()){
                landmark.setAddress(mapRowToAddress(addressResult));
            }

            //add appropriate reviews to each landmark
            String reviewSql = " SELECT id, title, description, landmark_id, is_liked, user_id, username " +
                    " FROM reviews " +
                    " WHERE landmark_id = ?";
            SqlRowSet reviewResult = jdbcTemplate.queryForRowSet(reviewSql, landmark.getLandmarkId());
            while (reviewResult.next()){
                landmark.addReview(mapRowToReview(reviewResult));
            }

            list.add(landmark);
        }
        return list;
    }

    @Override
    public Landmark getLandmark(int landmarkId){
        List<Landmark> landmarks = listLandmarks();
        for(Landmark landmark: landmarks){
            if(landmark.getLandmarkId() == landmarkId){
                return landmark;
            }
        }
        return new Landmark();
    }


    //TODO: check sql
    @Override
    public boolean createLandmark(Landmark landmark, int addressId){

        //String imgUrl = "data:image/jpeg;base64,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";

        String imgUrl = "data:image/jpeg;base64,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";
        String sql = "INSERT INTO landmarks (address_id, name, type, description, likes, img_URL, is_pending) " +
                    " VALUES(?, ?, ?, ?, ?, ?, ?); ";

         return jdbcTemplate.update(sql, addressId, landmark.getName(),
                                    landmark.getType().getTypeId(),
                                    landmark.getDescription(),
                                    landmark.getLikes(),
                                    imgUrl,
                                    landmark.isPending()) == 1;
    }

    @Override
    public Landmark updateLandmarkLikes(Landmark landmark, int landmarkId){
        Landmark result = landmark;
        int likeCount = landmark.getLikes() + 1;

        String sql = " UPDATE landmarks " +
                    " SET likes = ? " +
                    " WHERE id = ? ";
        int num = jdbcTemplate.update(sql, likeCount, landmarkId);

        if(num != 1){
            return null;
        }
        return result;
    }

    @Override
    public Landmark updatePendingStatus(Landmark landmark){
        Landmark result = landmark;

        String sql = " UPDATE landmarks " +
                " SET is_pending = ? " +
                " WHERE id = ? ";

        int num = jdbcTemplate.update(sql, false, landmark.getLandmarkId());

        if(num != 1){
            return null;
        }

        return result;
    }


    @Override
    public boolean deleteLandmark(int landmarkId){

        int lengthBefore = listLandmarks().size();

        String sql = " DELETE FROM landmarks " +
                " WHERE id = ? ";

        jdbcTemplate.update(sql, landmarkId);

        int lengthAfter = listLandmarks().size();

        if(lengthBefore == lengthAfter){
            return false;
        }
        return true;

    }




    private Landmark mapRowToLandmark(SqlRowSet results){
        Landmark landmark = new Landmark();

        landmark.setLandmarkId(results.getInt("id"));
        landmark.setAddressId(results.getInt("address_id"));
        landmark.setName(results.getString("name"));
        landmark.setType(mapRowToType(results));
        landmark.setDescription(results.getString("description"));
        landmark.setLikes(results.getInt("likes"));
        landmark.setImgUrl(results.getString("img_url"));
        landmark.setPending(results.getBoolean("is_pending"));
        return landmark;
    }
    private Address mapRowToAddress(SqlRowSet results){
        Address address = new Address();
        address.setAddressId(results.getInt("id"));
        address.setStreet(results.getString("street"));
        address.setCity(results.getString("city"));
        address.setStreet(results.getString("street"));
        address.setStateAbbrev(results.getString("state"));
        address.setZipCode(results.getInt("zip"));
        return address;
    }

    private Type mapRowToType(SqlRowSet results){
        Type type = new Type();
        type.setName(results.getString("type_name"));
        type.setTypeId(results.getInt("type"));
        return type;
    }

    private Review mapRowToReview(SqlRowSet results){
        Review review = new Review();
        review.setReviewId(results.getInt("id"));
        review.setTitle(results.getString("title"));
        review.setDescription(results.getString("description"));
        review.setLandmarkId(results.getInt("landmark_id"));
        review.setLiked(results.getBoolean("is_liked"));
        review.setUserId(results.getInt("user_id"));
        review.setUsername(results.getString("username"));

        return review;
    }
}
