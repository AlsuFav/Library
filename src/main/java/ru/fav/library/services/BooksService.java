package ru.fav.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fav.library.models.Book;
import ru.fav.library.models.Person;
import ru.fav.library.repositories.BooksRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {

    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    public Book findById(int id) {
        Optional<Book> book = booksRepository.findById(id);
        return book.orElse(null);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        updatedBook.setId(id);
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    public List<Book> findByPerson(Person person) {
        return booksRepository.findByPerson(person);
    }

    @Transactional
    public void release(int id) {
        Book book = findById(id);
        if (book != null) {
            book.setPerson(null);
            booksRepository.save(book);
        }
    }

    @Transactional
    public void assign(int id, Person person) {
        Book book = findById(id);
        if (book != null) {
            book.setPerson(person);
            booksRepository.save(book);
        }
    }

    public Optional<Person> findBookOwner(int id) {
        Book book = findById(id);
        return Optional.ofNullable(book.getPerson());
    }
}









