package com.example.springintro.service;

import com.example.springintro.model.entity.Author;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public interface AuthorService {
    void seedAuthors() throws IOException;

    Author getRandomAuthor();

    List<String> getAllAuthorsOrderByCountOfTheirBooks();

    List<String> findAuthorsWithFirstNameEndsWithAGivenString(String ch);

    List<String> findAllAuthorsAndTheirCopies();
}
