package by.velichko.home.dao.impl;

import by.velichko.home.model.Director;
import by.velichko.home.model.Film;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class DirectorMapper implements RowMapper<Director> {
    @Override
    public Director mapRow(ResultSet resultSet, int i) throws SQLException {
        Director director = new Director();

        director.setId(resultSet.getInt("id"));
        director.setFirstName(resultSet.getString("first_name"));
        director.setLastName(resultSet.getString("last_name"));
        director.setBirthDate(resultSet.getDate("birth_date"));

        return director;
    }
}
