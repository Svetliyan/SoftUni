package org._05_springdata_exercise.data.service.impl;

import org._05_springdata_exercise.data.entities.Author;
import org._05_springdata_exercise.data.repositories.AuthorRepository;
import org._05_springdata_exercise.data.service.AuthorService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class AuthorServiceImpl implements AuthorService {
    private static final String FILE_PATH = "src/main/resources/files/authors.txt";
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void seedAuthors() throws IOException {
        if (this.authorRepository.count() == 0) {
            Files.readAllLines(Path.of(FILE_PATH))
                    .stream()
                    .filter(row -> !row.isEmpty())
                    .forEach(row -> {
                        String[] tokens = row.split("\\s+");
                        this.authorRepository.saveAndFlush(new Author(tokens[0], tokens[1]));
                    });
        }
    }

    @Override
    public Author getRandomAuthor() {
        int randomId = ThreadLocalRandom
                .current()
                .nextInt(1, (int) this.authorRepository.count() + 1);
        return this.authorRepository.findById(randomId).get();
    }
}
