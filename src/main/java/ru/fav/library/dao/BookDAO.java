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
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("select * from book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return jdbcTemplate.query("select * from book where id = ?", new BeanPropertyRowMapper<>(Book.class), id).stream().findFirst().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("insert into book (title, author, published_year) values (?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getPublished_year());
    }

    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("update book set title=?, author=?, published_year=? where id=?",
                updatedBook.getTitle(), updatedBook.getAuthor(), updatedBook.getPublished_year(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from book where id=?", id);
    }

    public Optional<Person> getBookOwner(int id) {
        return jdbcTemplate.query("select person.* from book " +
                "join person on book.person_id = person.id " +
                "where book.id = ?", new BeanPropertyRowMapper<>(Person.class), id).stream().findFirst();
    }

    public void release(int id) {
        jdbcTemplate.update("update book set person_id=null where id=?", id);
    }

    public void assign(int id, Person person) {
        jdbcTemplate.update("update book set person_id=? where id=?",
                person.getId(), id);
    }
}
