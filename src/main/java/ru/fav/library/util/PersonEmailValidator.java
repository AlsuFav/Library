package ru.fav.library.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.fav.library.models.Person;
import ru.fav.library.services.PeopleService;

import java.util.Optional;


@Component
public class PersonEmailValidator implements Validator {
    private final PeopleService peopleService;

    @Autowired
    public PersonEmailValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person targetPerson = (Person) target;
        System.out.println(targetPerson.getId());

        Optional<Person> person = peopleService.findByEmail(targetPerson.getEmail());

        if (person.isPresent() && person.get().getId() != targetPerson.getId()) {
            errors.rejectValue("email", "", "Эта почта уже существует");
        }
    }
}




