package ru.fav.library.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Book")
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Название книги не должно быть пустым")
    @Size(max = 70, message = "Название книги должно содержать не более 70 символов")
    @Column(name = "title")
    private String title;

    @NotEmpty(message = "Имя автора не должно быть пустым")
    @Size(max = 70, message = "Имя автора должно содержать не более 70 символов")
    @Column(name = "author")
    private String author;

    @Column(name = "published")
    private int published;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    public Book() {}

    public Book(String title, String author, int published) {
        this.title = title;
        this.author = author;
        this.published = published;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotEmpty(message = "Название книги не должно быть пустым") @Size(max = 70, message = "Название книги должно содержать не более 70 символов") String getTitle() {
        return title;
    }

    public void setTitle(@NotEmpty(message = "Название книги не должно быть пустым") @Size(max = 70, message = "Название книги должно содержать не более 70 символов") String title) {
        this.title = title;
    }

    public @NotEmpty(message = "Имя автора не должно быть пустым") @Size(max = 70, message = "Имя автора должно содержать не более 70 символов") String getAuthor() {
        return author;
    }

    public void setAuthor(@NotEmpty(message = "Имя автора не должно быть пустым") @Size(max = 70, message = "Имя автора должно содержать не более 70 символов") String author) {
        this.author = author;
    }

    public int getPublished() {
        return published;
    }

    public void setPublished(int publishedYear) {
        this.published = publishedYear;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
