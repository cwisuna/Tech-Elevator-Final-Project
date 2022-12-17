package com.techelevator.dao;

import com.techelevator.model.Type;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTypeDao implements TypeDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcTypeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Type> listTypes() {
        List<Type> list = new ArrayList<>();
        String sql = "SELECT types.id, types.name " +
                "FROM types";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
        while(result.next()){
            Type type = mapRowToType(result);
            list.add(type);
        }

        return list;
    }

    @Override
    public Type getType(int id) {
        Type type = listTypes().get(id-1);
        return type;
    }

    private Type mapRowToType(SqlRowSet results){
        Type type = new Type();
        type.setTypeId(results.getInt("id"));
        type.setName(results.getString("name"));
        return type;
    }
}
