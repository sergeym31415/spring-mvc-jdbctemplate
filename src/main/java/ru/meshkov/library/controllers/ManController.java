package ru.meshkov.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.meshkov.library.dao.BookDAO;
import ru.meshkov.library.dao.ManDAO;
import ru.meshkov.library.models.Man;
import ru.meshkov.library.util.ManValidator;

import javax.validation.Valid;


@Controller
@RequestMapping("/men")
public class ManController {

    private final ManDAO manDAO;
    private final BookDAO bookDAO;
    private final ManValidator manValidator;

    @Autowired
    public ManController(ManDAO manDAO, BookDAO bookDAO, ManValidator manValidator) {
        this.manDAO = manDAO;
        this.bookDAO = bookDAO;
        this.manValidator = manValidator;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("men", manDAO.index());
        return "men/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("man", manDAO.show(id));
        model.addAttribute("books", bookDAO.getBooksByManId(id));
        return "men/show";
    }

    @GetMapping("/new")
    public String newMan(Model model) {
        model.addAttribute("man", new Man());
        return "men/new";
    }

    @PostMapping
    public String createMan(@ModelAttribute("man") @Valid Man man, BindingResult bindingResult) {
        manValidator.validate(man, bindingResult);
        if(bindingResult.hasErrors()) {
            return "men/new";
        }
        manDAO.save(man);
        return "redirect:/men";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("man", manDAO.show(id));
        return "men/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("man") @Valid Man man, BindingResult bindingResult, @PathVariable("id") int id) {
        manValidator.validate(man, bindingResult);
        if(bindingResult.hasErrors()) {
            return "men/edit";
        }
        manDAO.update(id, man);
        return "redirect:/men";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        manDAO.delete(id);
        return "redirect:/men";
    }
}
