package by.velichko.home.dao;

import by.velichko.home.model.Director;

import java.util.List;

public interface IDirectorDAO {

    List<Director> getDirectors();

    Director showDirector(int id);

    void saveDirector(Director director);

    void updateDirector(int id, Director updatedDirector);

    void deleteDirector(int id);
}
