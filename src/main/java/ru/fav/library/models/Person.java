package ru.fav.library.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jdk.jfr.Name;

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
    private String full_name;


    @Min(value = 1900,
            message = "Год рождения должен быть не меньше чем 1900")
    @Column(name = "birth_year")
    private int birth_year;

    @NotEmpty(message = "Почта не должна быть пустой")
    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "person", cascade = CascadeType.PERSIST)
    private List<Book> books;

    public Person() {
    }

    public Person(String full_name, int birth_year, String email) {
        this.full_name = full_name;
        this.birth_year = birth_year;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotEmpty(message = "ФИО не должно быть пустым") @Size(max = 60, message = "ФИО должно содержать не более 70 символов") @Pattern(regexp = "[А-Я][а-я]+ [А-Я][а-я]+ [А-Я][а-я]+",
            message = "ФИО должно быть следующего формата: Фамилия Имя Отчество") String getFull_name() {
        return full_name;
    }

    public void setFull_name(@NotEmpty(message = "ФИО не должно быть пустым") @Size(max = 60, message = "ФИО должно содержать не более 70 символов") @Pattern(regexp = "[А-Я][а-я]+ [А-Я][а-я]+ [А-Я][а-я]+",
            message = "ФИО должно быть следующего формата: Фамилия Имя Отчество") String full_name) {
        this.full_name = full_name;
    }

    @Min(value = 1900, message = "Год рождения должен быть не меньше чем 1900")
    public int getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(@Min(value = 1900, message = "Год рождения должен быть не меньше чем 1900") int birth_year) {
        this.birth_year = birth_year;
    }

    public @NotEmpty(message = "Почта не должна быть пустой") String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty(message = "Почта не должна быть пустой") String email) {
        this.email = email;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
