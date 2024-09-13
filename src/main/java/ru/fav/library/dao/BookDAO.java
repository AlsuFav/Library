package ru.fav.library.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.fav.library.models.Book;
import ru.fav.library.models.Person;
import java.util.List;
import java.util.Optional;


@Component
public class BookDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public BookDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Book> index() {
        Session session = sessionFactory.getCurrentSession();
        return session.createSelectionQuery("select b from Book b", Book.class).getResultList();
    }

    @Transactional(readOnly = true)
    public Book show(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Book.class, id);
    }

    @Transactional
    public void save(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class, id);

        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setPublished_year(updatedBook.getPublished_year());
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Book.class, id));
    }

    @Transactional(readOnly = true)
    public Optional<Person> getBookOwner(int id) {
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class, id);

        return Optional.ofNullable(book.getPerson());
    }

    @Transactional
    public void release(int id) {
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class, id);
        book.setPerson(null);
    }

    @Transactional
    public void assign(int id, Person person) {
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class, id);
        book.setPerson(person);
    }
}
