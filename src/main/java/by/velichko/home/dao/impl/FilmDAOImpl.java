package by.velichko.home.dao.impl;

import by.velichko.home.dao.IFilmDAO;
import by.velichko.home.model.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class FilmDAOImpl implements IFilmDAO {

    private static final int FIRST_FILM_DATE = 1895;

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FilmDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Film> getFilms() {
        return jdbcTemplate.query("SELECT film.*, director.first_name, director.last_name, director.birth_date " +
                "FROM film LEFT JOIN director ON film.director_id = director.id;", new FilmMapper());
    }

    @Override
    public Film showFilm(int id) {
        return jdbcTemplate.query("SELECT film.*, director.first_name, director.last_name, director.birth_date " +
                "FROM film LEFT JOIN director ON film.director_id = director.id WHERE film.id = ?;",
                new FilmMapper(), id).stream()
                .findAny()
                .get();
    }

    @Override
    public void saveFilm(Film film, int directorId) {
        jdbcTemplate.update("INSERT INTO film (director_id, name, release_date, genre) " +
                "VALUES (?, ?, ?, ?)", directorId, film.getName(), film.getReleaseDate(), film.getGenre());
    }

    @Override
    public void updateFilm(int id, Film updatedFilm) {
        jdbcTemplate.update("UPDATE film SET name=?, release_date=?, genre=? WHERE id=?",
                updatedFilm.getName(), updatedFilm.getReleaseDate(), updatedFilm.getGenre(), id);
    }

    @Override
    public void deleteFilm(int id) {
        jdbcTemplate.update("DELETE FROM film WHERE id=?", id);
    }

    @Override
    public List<Film> findFilms(String lastName, String releaseYearFrom, String releaseYearUntil) {
        int from;
        int until;
        lastName += "%";

        if (releaseYearFrom.equals("")) {
            from = FIRST_FILM_DATE;
        } else {
            from = Integer.parseInt(releaseYearFrom);
        }

        if (releaseYearUntil.equals("")) {
            until = LocalDate.now().getYear();
        } else {
            until = Integer.parseInt(releaseYearUntil);
        }

        if (lastName.equals("")) {
            return jdbcTemplate.query("SELECT film.*, director.first_name, director.last_name, " +
                    "director.birth_date FROM film LEFT JOIN director ON film.director_id = director.id " +
                    "WHERE EXTRACT(YEAR FROM film.release_date) >= ? AND EXTRACT(YEAR FROM film.release_date) <= ?",
                    new FilmMapper(), from, until);
        } else {
            return jdbcTemplate.query("SELECT film.*, director.first_name, director.last_name, " +
                    "director.birth_date FROM film LEFT JOIN director ON film.director_id = director.id " +
                    "WHERE director.last_name LIKE ? AND EXTRACT(YEAR FROM film.release_date) >= ? " +
                    "AND EXTRACT(YEAR FROM film.release_date) <= ?",
                    new FilmMapper(), lastName, from, until);
        }
    }
}
