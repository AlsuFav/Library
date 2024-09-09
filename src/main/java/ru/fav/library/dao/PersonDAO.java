package ru.fav.library.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.fav.library.models.Book;
import ru.fav.library.models.Person;
import java.util.List;
import java.util.Optional;


@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Person> index() {
        return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("select * from person where id = ?", new BeanPropertyRowMapper<>(Person.class), id).stream().findFirst().orElse(null);
    }

    public Optional<Person> show(String full_name) {
        return jdbcTemplate.query("select * from person where full_name = ?", new BeanPropertyRowMapper<>(Person.class), full_name).stream().findFirst();
    }

    public void save(Person person) {
        jdbcTemplate.update("insert into person (full_name, birth_year) values (?, ?)",
                person.getFull_name(), person.getBirth_year());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("update person set full_name=?, birth_year=? where id=?",
                updatedPerson.getFull_name(), updatedPerson.getBirth_year(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from person where id=?", id);
    }

    public List<Book> getBooksByPersonId(int id) {
        return jdbcTemplate.query("select * from book where person_id = ?", new BeanPropertyRowMapper<>(Book.class), id);
    }
}
