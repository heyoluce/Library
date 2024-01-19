package com.zholdoshov.kg.services;

import com.zholdoshov.kg.models.Book;
import com.zholdoshov.kg.models.Person;
import com.zholdoshov.kg.repositories.BooksRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BooksRepository booksRepository;

    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }


    public List<Book> findWithPagination(Integer page, Integer itemsPerPage, boolean sort) {
        if (sort) {
            return booksRepository.findAll(PageRequest.of(page, itemsPerPage, Sort.by("year"))).getContent();
        }
        else {
            return booksRepository.findAll(PageRequest.of(page, itemsPerPage)).getContent();

        }
    }

    public List<Book> findAll(boolean sort) {
            if (sort) {
                return booksRepository.findAll((Sort.by("year")));
            }
            else {
                return booksRepository.findAll();
            }
    }
    public List<Book> searchBookByNameStartsWith(String prefix) {
            return booksRepository.findBookByNameIsStartingWith(prefix);
    }

    public Book findOne(int id) {
        Optional<Book> foundedBook = booksRepository.findById(id);
        return foundedBook.orElse(null);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book book) {
        book.setId(id);
        booksRepository.save(book);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    public Optional<Person> getBookOwner(int id) {
        return Optional.ofNullable(findOne(id).getPerson());
    }


    @Transactional
    public void assign(int id, Person person) {
        Book assignedBook = findOne(id);
        assignedBook.setPerson(person);
        assignedBook.setTaken_at(new Date());
       // booksRepository.findById(id).ifPresent(book -> book.setPerson(person));
    }

    @Transactional
    public void release(int id) {
        Book releasedBook = findOne(id);
        releasedBook.setTaken_at(null);
        releasedBook.setPerson(null);
     //   booksRepository.findById(id).ifPresent(book -> book.setPerson(null));
    }

}


