package by.velichko.home.controller;

import by.velichko.home.dao.IDirectorDAO;
import by.velichko.home.model.Director;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/directors")
public class DirectorController {

    private final IDirectorDAO directorDAO;

    @Autowired
    public DirectorController(IDirectorDAO directorDAO) {
        this.directorDAO = directorDAO;
    }

    @GetMapping("/all")
    public String getAll(Model model) {
        model.addAttribute("directors", directorDAO.getDirectors());
        return "directors/all";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("director", directorDAO.showDirector(id));
        return "directors/showDirector";
    }

    @GetMapping("/new")
    public String newDirector(@ModelAttribute("director") Director director) {
        return "directors/new";
    }

    @PostMapping
    public String create(@ModelAttribute("director") Director director) {
        directorDAO.saveDirector(director);
        return "redirect:/directors/all";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("director", directorDAO.showDirector(id));
        return "directors/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("director") Director director) {
        directorDAO.updateDirector(id, director);
        return "redirect:/directors/" + id;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        directorDAO.deleteDirector(id);
        return "redirect:/directors/all";
    }
}
