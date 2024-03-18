package org._05_springdata_exercise.data.service.impl;

import org._05_springdata_exercise.data.entities.Category;
import org._05_springdata_exercise.data.repositories.CategoryRepository;
import org._05_springdata_exercise.data.service.CategoryService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private static final String FILE_PATH = "src/main/resources/files/categories.txt";
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedCategories() throws IOException {
        if (this.categoryRepository.count() == 0) {
            Files.readAllLines(Path.of(FILE_PATH))
                    .stream()
                    .filter(row -> !row.isEmpty())
                    .forEach(row -> this.categoryRepository.saveAndFlush(new Category(row)));
        }
    }

    @Override
    public Set<Category> getRandomCategories() {
        Set<Category> categories = new HashSet<>();

        int randomId = ThreadLocalRandom.current()
                .nextInt(1, 4);
        categories.add(this.categoryRepository.findById(randomId).get());

        return categories;
    }
}
