package org._05_springdata_exercise.data.repositories;

import org._05_springdata_exercise.data.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Set<Book> findAllByReleaseDateAfter(LocalDate date);
}
