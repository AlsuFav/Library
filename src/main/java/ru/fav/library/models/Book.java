package ru.fav.library.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Book {
    private int id;

    @NotEmpty(message = "Название книги не должно быть пустым")
    @Size(max = 70, message = "Название книги должно содержать не более 70 символов")
    private String title;

    @NotEmpty(message = "Имя автора не должно быть пустым")
    @Size(max = 70, message = "Имя автора должно содержать не более 70 символов")
    private String author;

    private int published_year;

    public Book() {}

    public Book(String title, String author, int published_year) {
        this.title = title;
        this.author = author;
        this.published_year = published_year;
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

    public int getPublished_year() {
        return published_year;
    }

    public void setPublished_year(int published_year) {
        this.published_year = published_year;
    }
}
