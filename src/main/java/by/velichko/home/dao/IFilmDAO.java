package by.velichko.home.dao;

import by.velichko.home.model.Film;

import java.util.List;

public interface IFilmDAO {

    List<Film> getFilms();

    Film showFilm(int id);

    void saveFilm(Film film, int directorId);

    void updateFilm(int id, Film updatedFilm);

    void deleteFilm(int id);

    List<Film> findFilms(String lastName, String releaseYearFrom, String releaseYearUntil);
}
