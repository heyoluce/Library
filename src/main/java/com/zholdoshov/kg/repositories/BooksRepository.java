package com.zholdoshov.kg.repositories;

import com.zholdoshov.kg.models.Book;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    List<Book> findBookByNameIsStartingWith(@NotBlank(message = "The name must not be empty") @Size(min = 2, max = 255, message = "Name should be between 2 and 255 characters") String name);
}
