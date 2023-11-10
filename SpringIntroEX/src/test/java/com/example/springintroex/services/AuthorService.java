package com.example.springintroex.services;

import com.example.springintroex.models.entities.Author;

import java.io.IOException;
import java.util.List;

public interface AuthorService{
    void seedAuthors() throws IOException;

    Author getRandomAuthor();

    List<String> getAllAuthorsOrderByCountOfTheirBooks();
}
