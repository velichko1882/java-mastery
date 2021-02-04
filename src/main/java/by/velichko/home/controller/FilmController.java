package by.velichko.home.controller;

import by.velichko.home.dao.IFilmDAO;
import by.velichko.home.model.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/films")
public class FilmController {

    private final IFilmDAO filmDAO;

    @Autowired
    public FilmController(IFilmDAO filmDAO) {
        this.filmDAO = filmDAO;
    }

    @GetMapping("/all")
    public String getAll(Model model) {
        model.addAttribute("films", filmDAO.getFilms());
        return "films/all";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("film", filmDAO.showFilm(id));
        return "films/showFilm";
    }

    @GetMapping("/find")
    public String find(@RequestParam("lastName") String lastName,
                       @RequestParam("from") String releasedFrom,
                       @RequestParam("until") String releasedUntil,
                       Model model) {
        model.addAttribute("lastName", lastName);
        model.addAttribute("from", releasedFrom);
        model.addAttribute("until", releasedUntil);
        model.addAttribute("films", filmDAO.findFilms(lastName, releasedFrom, releasedUntil));
        return "films/find";
    }

    @GetMapping("/new")
    public String newFilm(@RequestParam("directorId") int directorId,
                          @ModelAttribute("film") Film film,
                          Model model) {
        model.addAttribute("directorId", directorId);
        return "films/new";
    }

    @PostMapping
    public String create(@RequestParam("directorId") int directorId,
                         @ModelAttribute("flm") Film film) {
        filmDAO.saveFilm(film, directorId);
        return "redirect:/directors/" + directorId;
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("film", filmDAO.showFilm(id));
        return "films/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("film") Film film) {
        filmDAO.updateFilm(id, film);
        return "redirect:/films/" + id;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        filmDAO.deleteFilm(id);
        return "redirect:/films/all";
    }
}
