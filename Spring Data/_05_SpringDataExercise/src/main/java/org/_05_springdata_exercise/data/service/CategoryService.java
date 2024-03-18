package org._05_springdata_exercise.data.service;

import org._05_springdata_exercise.data.entities.Category;

import java.io.IOException;
import java.util.Set;

public interface CategoryService {
    void seedCategories() throws IOException;

    Set<Category> getRandomCategories();
}
