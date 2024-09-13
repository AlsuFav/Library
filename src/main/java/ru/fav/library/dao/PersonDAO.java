package ru.fav.library.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.fav.library.models.Book;
import ru.fav.library.models.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class PersonDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Person> index() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select p from Person p", Person.class).getResultList();
    }

    @Transactional(readOnly = true)
    public Person show(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class, id);
     }

    @Transactional(readOnly = true)
    public Optional<Person> showByName(String full_name) {
        Session session = sessionFactory.getCurrentSession();
        Person person = session.createQuery("select p from Person p where p.full_name = ?1", Person.class)
                .setParameter(1, full_name).uniqueResult();

        return Optional.ofNullable(person);
    }

    @Transactional(readOnly = true)
    public Optional<Person> showByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Person person = session.createQuery("select p from Person p where p.email = ?1", Person.class)
                .setParameter(1, email).uniqueResult();

        return Optional.ofNullable(person);
    }

    @Transactional
    public void save(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, id);

        person.setFull_name(updatedPerson.getFull_name());
        person.setBirth_year(updatedPerson.getBirth_year());
        person.setEmail(updatedPerson.getEmail());
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Person.class, id));
    }

    @Transactional(readOnly = true)
    public List<Book> getBooksByPersonId(int id) {
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, id);
        System.out.println(person.getBooks());
        return person.getBooks();
    }
}
