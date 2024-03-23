package com.example.springintro.service;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import com.example.springintro.repository.BookInfo;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);

    List<String> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<String> findAllByEditionType(EditionType type, int copies);

    List<Book> findAllBooksWithPriceOutsideOf(int lowerBound, int higherBound);

    List<String> findAllBooksNotPublishedIn(int year);

    List<Book> findAllBooksPublishedBefore(LocalDate date);

    List<String> FindAllBooksContaining(String contains);

    List<String> findTitlesForAuthorNameStartingWith(String lastNameStart);

    int findTitleCountLongerThat(int length);

    BookInfo findInfoByTitle(String title);
}
