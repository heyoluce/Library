package com.zholdoshov.kg.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Date;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "The name must not be empty")
    @Size(min = 2, max = 255, message = "Name should be between 2 and 255 characters")
    @Column(name="name")
    private String name;

    @NotEmpty(message = "Author must not be empty")
    @Size(min = 2, max = 255, message = "Author's should be between 2 and 255 characters")
    @Column(name="author")
    private String author;

    @Min(value = 1000, message = "Year should be greater than or equal to 1000")
    @Max(value = 9999, message = "Year should be less than or equal to 9999")
    @Column(name="year")
    private int year;
    @ManyToOne
    @JoinColumn(name="person_id")
    private Person person;

    @Column(name="taken_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date taken_at;

    @Transient
    private boolean expired;
    public Book() {
    }

    public Book(String name, String author, int year) {
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Date getTaken_at() {
        return taken_at;
    }

    public void setTaken_at(Date taken_at) {
        this.taken_at = taken_at;
    }


    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }
}
