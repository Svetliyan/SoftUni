package org._05_springdata_exercise;

import org._05_springdata_exercise.data.service.AuthorService;
import org._05_springdata_exercise.data.service.BookService;
import org._05_springdata_exercise.data.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final BookService bookService;

    public CommandLineRunnerImpl(AuthorService authorService, CategoryService categoryService, BookService bookService) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        getAllBooksAfter2000Year();
    }

    private void getAllBooksAfter2000Year() {
        this.bookService.findAllBooksAfterYear2000()
                .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        this.categoryService.seedCategories();
        this.authorService.seedAuthors();
        this.bookService.seedBooks();
    }
}
