package com.example.springintroex.repositories;

import com.example.springintroex.models.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {


    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);
    List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOOrderByReleaseDateDescTitle(String author_firstName, String author_lastName);
}
