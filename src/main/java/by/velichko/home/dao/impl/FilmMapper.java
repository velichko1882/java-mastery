package by.velichko.home.dao.impl;

import by.velichko.home.model.Director;
import by.velichko.home.model.Film;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FilmMapper implements RowMapper<Film> {

    @Override
    public Film mapRow(ResultSet resultSet, int i) throws SQLException {

        Film film = new Film();
        Director director = new Director();

        director.setId(resultSet.getInt("director_id"));
        director.setFirstName(resultSet.getString("first_name"));
        director.setLastName(resultSet.getString("last_name"));
        director.setBirthDate(resultSet.getDate("birth_date"));

        film.setId(resultSet.getInt("id"));
        film.setName(resultSet.getString("name"));
        film.setReleaseDate(resultSet.getDate("release_date"));
        film.setGenre(resultSet.getString("genre"));
        film.setDirector(director);

        return film;
    }
}
