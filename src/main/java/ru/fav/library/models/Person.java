package ru.fav.library.models;

import jakarta.validation.constraints.*;
// import java.time.Year;


public class Person {
    private int id;

    @NotEmpty(message = "ФИО не должно быть пустым")
    @Size(max = 70, message = "ФИО должно содержать не более 70 символов")
    @Pattern(regexp = "[А-Я][а-я]+ [А-Я][а-я]+ [А-Я][а-я]+",
            message = "ФИО должно быть следующего формата: Фамилия Имя Отчество")
    private String full_name;


    @Min(value = 1900,
            message = "Год рождения должен быть не меньше чем 1900")
    private int birth_year;


    public Person() {
    }

    public Person(String full_name, int birth_year) {
        this.full_name = full_name;
        this.birth_year = birth_year;
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
}
