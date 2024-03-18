package org._05_springdata_exercise.data.service;

import org._05_springdata_exercise.data.entities.Author;

import java.io.IOException;

public interface AuthorService {
    void seedAuthors() throws IOException;

    Author getRandomAuthor();
}
