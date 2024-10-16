package ru.fav.library.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.fav.library.models.Person;
import ru.fav.library.services.BooksService;
import ru.fav.library.services.PeopleService;
import ru.fav.library.util.PersonBirthdayValidator;
import ru.fav.library.util.PersonEmailValidator;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;
    private final BooksService booksService;
    private final PersonEmailValidator personEmailValidator;
    private final PersonBirthdayValidator personBirthdayValidator;

    @Autowired
    public PeopleController(PeopleService peopleService, BooksService booksService, PersonEmailValidator personEmailValidator, PersonBirthdayValidator personBirthdayValidator) {
        this.peopleService = peopleService;
        this.booksService = booksService;
        this.personEmailValidator = personEmailValidator;
        this.personBirthdayValidator = personBirthdayValidator;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("people", peopleService.findAll());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Person person = peopleService.findById(id);

        model.addAttribute("person", person);
        model.addAttribute("books", booksService.findByPerson(person));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {

        personEmailValidator.validate(person, bindingResult);
        personBirthdayValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "people/new";
        }
        peopleService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", peopleService.findById(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {

        personEmailValidator.validate(person, bindingResult);
        personBirthdayValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "people/edit";
        }

        peopleService.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        peopleService.delete(id);
        return "redirect:/people";
    }
}
