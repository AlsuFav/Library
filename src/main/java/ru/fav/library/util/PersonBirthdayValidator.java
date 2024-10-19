package ru.fav.library.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.fav.library.models.Person;
import ru.fav.library.services.PeopleService;

import java.util.Objects;


@Component
public class PersonBirthdayValidator implements Validator {
    private final PeopleService peopleService;

    @Autowired
    public PersonBirthdayValidator(PeopleService peopleService) {
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

        if (Objects.isNull(targetPerson.getBirthday())) {
            errors.rejectValue("birthday", "", "Дата рождения должна быть в формате дд.мм.гггг");
        }
    }
}




