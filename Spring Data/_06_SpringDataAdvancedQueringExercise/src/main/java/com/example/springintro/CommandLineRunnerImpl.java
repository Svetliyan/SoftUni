package com.example.springintro;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import com.example.springintro.repository.BookInfo;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();
//        exercise 01
//        printBooksByAgeRestriction();

//        exercise 02
//        printGoldenBooksWithLessThan5000Copies();

//        exercise 03
//        printBooksWithPriceOutsideOf();

//        exercise 04
//        printBooksNotReleasedIn();

//        exercise 05
//        printBooksInfoForBooksReleasedBefore();

//        exercise 06
//        printAuthorsEndingIn();

//        exercise 07
//        printBooksContains();

//        exercise 08
//        findBooksWithLastNameStarting();

//        exercise 09
//        printNumberOfBooksWithLongerTitle();

//        exercise 10
//        printBooksCountWrittenByAuthor();

//        exercise 11
//        printBooksProjection();
    }

    private void printBooksProjection() {
        Scanner scanner = new Scanner(System.in);
        String title = scanner.nextLine();

        BookInfo info = bookService.findInfoByTitle(title);
        System.out.println(info);
    }

    private void printBooksCountWrittenByAuthor() {
        Scanner scanner = new Scanner(System.in);
        String[] authorName = scanner.nextLine().split("\\s+");

        int count = authorService.getTotalCopiesCountFor(authorName[0], authorName[1]);
        System.out.printf("%s %s %d%n",
                authorName[0], authorName[1], count);
    }

    private void printNumberOfBooksWithLongerTitle() {
        Scanner scanner = new Scanner(System.in);
        int length = Integer.parseInt(scanner.nextLine());

        int count = bookService.findTitleCountLongerThat(length);
        System.out.printf("There are %d books with longer titles than %d symbols.%n",
                count, length);
    }

    private void findBooksWithLastNameStarting() {
        Scanner scanner = new Scanner(System.in);
        String lastNameStart = scanner.nextLine();

        bookService.findTitlesForAuthorNameStartingWith(lastNameStart);
    }

    private void printBooksContains() {
        Scanner scanner = new Scanner(System.in);
        String contains = scanner.nextLine();

        List<String> books = bookService.FindAllBooksContaining(contains);
        books.forEach(System.out::println);

    }

    private void printAuthorsEndingIn() {
        Scanner scanner = new Scanner(System.in);
        String ending = scanner.nextLine();

        List<String> names = authorService.findAllNamesEndingIn(ending);
        names.forEach(System.out::println);
    }

    private void printBooksInfoForBooksReleasedBefore() {
        Scanner scanner = new Scanner(System.in);
        String beforeDate = scanner.nextLine();
        LocalDate parsedDate = LocalDate.parse(beforeDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        List<Book> books = bookService.findAllBooksPublishedBefore(parsedDate);
        books.forEach(book -> System.out.printf("%s %s %.2f%n",
                book.getTitle(), book.getEditionType(), book.getPrice()));
    }

    private void printBooksNotReleasedIn() {
        List<String> titles = bookService.findAllBooksNotPublishedIn(2000);
        titles.forEach(System.out::println);
    }

    private void printBooksWithPriceOutsideOf() {
        List<Book> books = bookService.findAllBooksWithPriceOutsideOf(5, 40);
        books.forEach(book -> System.out.printf("%s - $%.2f%n",book.getTitle(), book.getPrice()));
    }

    private void printGoldenBooksWithLessThan5000Copies() {
        List<String> titles = bookService.findAllByEditionType(EditionType.GOLD, 5000);
        titles.forEach(System.out::println);
    }

    private void printBooksByAgeRestriction() {
        Scanner scanner = new Scanner(System.in);
        String restriction = scanner.nextLine();

        try {
            AgeRestriction ageRestriction = AgeRestriction.valueOf(restriction.toUpperCase());

            List<String> titles = bookService.findAllByAgeRestriction(ageRestriction);
            titles.forEach(System.out::println);
        }catch (IllegalArgumentException ex){
            System.out.println("Wrong age category");
            return;
        }
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
