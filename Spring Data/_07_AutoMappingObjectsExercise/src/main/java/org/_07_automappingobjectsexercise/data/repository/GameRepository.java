package org._07_automappingobjectsexercise.data.repository;

import org._07_automappingobjectsexercise.data.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
}
