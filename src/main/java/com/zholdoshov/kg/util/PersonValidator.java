package com.zholdoshov.kg.util;

import com.zholdoshov.kg.models.Person;
import com.zholdoshov.kg.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class PersonValidator implements Validator {
private final PeopleService peopleService;
@Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }



    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        // Получаем из базы данных запись о человеке с таким именем (если она есть)
        Optional<Person> existingPerson = Optional.ofNullable(peopleService.findById(person.getId()));

        // Проверяем, существует ли человек с таким именем, и не является ли он самим собой (при изменении)
        if (existingPerson.isPresent() && existingPerson.get().getId() != person.getId()) {
            errors.rejectValue("name", "duplicate", "This person already exists");
        }
    }
}
