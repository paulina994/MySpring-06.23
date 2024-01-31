package com.example.springintro.service;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);

    List<String> findAllBooksByAgeRestriction(AgeRestriction ageRestriction) throws IOException;

    List<String> findAllGoldBookTitlesWithCopiesLessThan5000();

    List<String> findBookTitlesAndPrices();

    List<String> findBookTitlesNotReleasedInGivenYear(int year) throws IOException;

    List<String> findBookTitleEditionTypeAndPriceBeforeReleaseDate(LocalDate releaseDateBefore);

    List<String> findBookTitleWhichContainAGivenString(String givenString);

    List<String> findAllTitleWithAuthorWithLastNameStartsWith(String str);

   int findNumberOfBooksWhoseTitleIsNoLongerThanAGivenNumber(int number);
}
