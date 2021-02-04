package by.velichko.home.dao.impl;

import by.velichko.home.dao.IDirectorDAO;
import by.velichko.home.model.Director;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DirectorDAOImpl implements IDirectorDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DirectorDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Director> getDirectors() {
        return jdbcTemplate.query("SELECT * FROM director", new DirectorMapper());
    }

    @Override
    public Director showDirector(int id) {
        Director director = jdbcTemplate.query("SELECT * FROM director WHERE id = ?",
                new DirectorMapper(), id).stream()
                .findAny()
                .get();
        director.setFilms(jdbcTemplate.query("SELECT film.*, director.first_name, director.last_name, " +
                "director.birth_date FROM film, director WHERE film.director_id = ? " +
                "AND director.id = film.director_id", new FilmMapper(), director.getId()));
        return director;
    }

    @Override
    public void saveDirector(Director director) {
        jdbcTemplate.update("INSERT INTO director (first_name, last_name, birth_date) " +
                "VALUES (?, ?, ?)", director.getFirstName(), director.getLastName(), director.getBirthDate());
    }

    @Override
    public void updateDirector(int id, Director updatedDirector) {
        jdbcTemplate.update("UPDATE director SET first_name=?, last_name=?, birth_date=? WHERE id=?",
                updatedDirector.getFirstName(), updatedDirector.getLastName(), updatedDirector.getBirthDate(), id);
    }

    @Override
    public void deleteDirector(int id) {
        jdbcTemplate.update("DELETE FROM film WHERE director_id=?", id);
        jdbcTemplate.update("DELETE FROM director WHERE id=?", id);
    }
}
