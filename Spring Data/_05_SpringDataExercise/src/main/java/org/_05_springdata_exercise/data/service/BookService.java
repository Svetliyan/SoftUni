package org._05_springdata_exercise.data.service;

import java.io.IOException;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<String> findAllBooksAfterYear2000();
}
