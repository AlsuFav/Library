package ru.fav.library.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.fav.library.dao.PersonDAO;
import ru.fav.library.models.Person;


@Component
public class PersonValidator implements Validator {
    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if (personDAO.showByName(person.getFull_name()).isPresent()) {
            errors.rejectValue("full_name", "", "Это ФИО уже существует");
        }

        if (personDAO.showByEmail(person.getEmail()).isPresent()) {
            errors.rejectValue("email", "", "Эта почта уже существует");
        }
    }
}




