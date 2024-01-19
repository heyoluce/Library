//package com.zholdoshov.kg.dao;
//
//import com.zholdoshov.kg.models.*;
//import com.zholdoshov.kg.models.Person;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.Optional;
//
//@Component
//public class PersonDAO {
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public PersonDAO(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Person> index() {
//        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
//    }
//
//    public Person show(int id) {
//        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?",
//                        new Object[]{id},
//                        new BeanPropertyRowMapper<>(Person.class))
//                .stream()
//                .findAny()
//                .orElse(null);
//    }
//
//    public void save(Person person) {
//        jdbcTemplate.update("INSERT INTO Person(name, dateOfBirth) VALUES(?,?)", person.getName(), person.getDateOfBirth());
//    }
//
//    public void update(int id, Person updatedPerson) {
//        jdbcTemplate.update("UPDATE Person SET name=?, dateOfBirth=? WHERE id=?", updatedPerson.getName(), updatedPerson.getDateOfBirth(), id);
//    }
//
//    public void delete(int id) {
//        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
//    }
//
//    public List<Book> getBooks(int id) {
//        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id=?", new Object[]{id},
//                new BeanPropertyRowMapper<>(Book.class));
//
//    }
//
//    public Optional<Person> getPersonByName(String selectedName) {
//        return jdbcTemplate.query("SELECT * FROM Person WHERE Person.name=?", new Object[]{selectedName}, new BeanPropertyRowMapper<>(Person.class))
//                .stream().findAny();
//    }
//
//    public boolean hasBooks(int id) {
//        List<Book> books = getBooks(id);
//        return !books.isEmpty();
//    }
//}
