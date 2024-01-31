package com.example.springintro;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;
    private final BufferedReader bufferedReader;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService, BufferedReader bufferedReader) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
        this.bufferedReader = bufferedReader;
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();

        //printAllBooksAfterYear(2000);
//        printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(1990);
        //   printAllAuthorsAndNumberOfTheirBooks();
//printALlBooksByAuthorNameOrderByReleaseDate("George", "Powell");

        System.out.println("Select ex:");
        int exNum = Integer.parseInt(bufferedReader.readLine());
        switch (exNum) {
            case 1:
                booksTitlesByAgeRestriction();
            case 2:
                goldenBooks();
            case 3:
                booksByPrice();
            case 4:
                notReleasedBooks();
            case 5:
                booksReleasedBeforeDate();
            case 6:
                authorsSearch();
            case 7:
                booksSearch();
            case 8:
                bookTitlesSearch();
            case 9:
                countBooks();
            case 10:
                totalBookCopies();
        }


    }

    private void totalBookCopies() {
        authorService.findAllAuthorsAndTheirCopies()
                .forEach(System.out::println);
    }

    private void countBooks() throws IOException {
        System.out.println("Enter number:");
        int number = Integer.parseInt(bufferedReader.readLine());
        System.out.println(bookService.findNumberOfBooksWhoseTitleIsNoLongerThanAGivenNumber(number));
    }

    private void bookTitlesSearch() throws IOException {
        System.out.println("Enter Author name starts with str:");
        String str = bufferedReader.readLine();

        bookService.findAllTitleWithAuthorWithLastNameStartsWith(str)
                .forEach(System.out::println);
    }

    private void booksSearch() throws IOException {
        System.out.println("Enter string:");
        String givenString = bufferedReader.readLine();

        bookService.findBookTitleWhichContainAGivenString(givenString)
                .forEach(System.out::println);
    }

    private void authorsSearch() throws IOException {
        System.out.println("Enter string:");
        String ch = bufferedReader.readLine();
        authorService.findAuthorsWithFirstNameEndsWithAGivenString(ch)
                .forEach(System.out::println);
    }

    private void booksReleasedBeforeDate() throws IOException {
        System.out.println("Enter date:");
        LocalDate releaseDateBefore = LocalDate.parse(bufferedReader.readLine(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        bookService.findBookTitleEditionTypeAndPriceBeforeReleaseDate(releaseDateBefore)
                .forEach(System.out::println);
    }

    private void notReleasedBooks() throws IOException {
        System.out.println("Enter year:");
        int year = Integer.parseInt(bufferedReader.readLine());
        bookService.findBookTitlesNotReleasedInGivenYear(year)
                .forEach(System.out::println);
    }

    private void booksByPrice() {
        bookService.findBookTitlesAndPrices()
                .forEach(System.out::println);
    }

    private void goldenBooks() {
        bookService.findAllGoldBookTitlesWithCopiesLessThan5000()
                .forEach(System.out::println);
    }

    private void booksTitlesByAgeRestriction() throws IOException {
        System.out.println("Enter Age Restriction:");
        AgeRestriction ageRestriction = AgeRestriction.valueOf(bufferedReader.readLine()
                .toUpperCase());

        bookService.findAllBooksByAgeRestriction(ageRestriction)
                .forEach(System.out::println);
    }

    private void printALlBooksByAuthorNameOrderByReleaseDate(String firstName, String lastName) {
        bookService
                .findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(firstName, lastName)
                .forEach(System.out::println);
    }

    private void printAllAuthorsAndNumberOfTheirBooks() {
        authorService
                .getAllAuthorsOrderByCountOfTheirBooks()
                .forEach(System.out::println);
    }

    private void printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(int year) {
        bookService
                .findAllAuthorsWithBooksWithReleaseDateBeforeYear(year)
                .forEach(System.out::println);
    }

    private void printAllBooksAfterYear(int year) {
        bookService
                .findAllBooksAfterYear(year)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
