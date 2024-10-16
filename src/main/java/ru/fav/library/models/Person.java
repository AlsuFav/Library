package ru.fav.library.models;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;


@Entity
@Table(name = "Person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "ФИО не должно быть пустым")
    @Size(max = 70, message = "ФИО должно содержать не более 70 символов")
    @Pattern(regexp = "[А-Я][а-я]+ [А-Я][а-я]+ [А-Я][а-я]+",
            message = "ФИО должно быть следующего формата: Фамилия Имя Отчество")
    @Column(name = "full_name")
    private String fullName;


    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date birthday;

    @NotEmpty(message = "Почта не должна быть пустой")
    @Column(name = "email")
    private String email;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @OneToMany(mappedBy = "person", cascade = CascadeType.PERSIST)
    private List<Book> books;

    public Person() {
    }

    public Person(String fullName, Date birthday, String email) {
        this.fullName = fullName;
        this.birthday = birthday;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotEmpty(message = "ФИО не должно быть пустым") @Size(max = 70, message = "ФИО должно содержать не более 70 символов") @Pattern(regexp = "[А-Я][а-я]+ [А-Я][а-я]+ [А-Я][а-я]+",
            message = "ФИО должно быть следующего формата: Фамилия Имя Отчество") String getFullName() {
        return fullName;
    }

    public void setFullName(@NotEmpty(message = "ФИО не должно быть пустым") @Size(max = 70, message = "ФИО должно содержать не более 70 символов") @Pattern(regexp = "[А-Я][а-я]+ [А-Я][а-я]+ [А-Я][а-я]+",
            message = "ФИО должно быть следующего формата: Фамилия Имя Отчество") String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public @NotEmpty(message = "Почта не должна быть пустой") String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty(message = "Почта не должна быть пустой") String email) {
        this.email = email;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
