package by.velichko.home.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Director {

    private int id;
    private String firstName;
    private String lastName;
    private Date birthDate;

    private List<Film> films = new ArrayList<>();

    public Director() {
    }

    public Director(int id, String firstName, String lastName, Date birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public Director(int id, String firstName, String lastName, Date birthDate, List<Film> films) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.films = films;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Director director = (Director) o;

        if (id != director.id) return false;
        if (!firstName.equals(director.firstName)) return false;
        if (!lastName.equals(director.lastName)) return false;
        if (!birthDate.equals(director.birthDate)) return false;
        return films != null ? films.equals(director.films) : director.films == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + birthDate.hashCode();
        result = 31 * result + (films != null ? films.hashCode() : 0);
        return result;
    }
}
