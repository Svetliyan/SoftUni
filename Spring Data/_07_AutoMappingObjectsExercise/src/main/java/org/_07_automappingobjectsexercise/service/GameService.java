package org._07_automappingobjectsexercise.service;

import org._07_automappingobjectsexercise.service.dto.GameAddDTO;
import org._07_automappingobjectsexercise.service.dto.GamesAllDTO;

import java.util.Map;
import java.util.Set;

public interface GameService {
    String addGame(GameAddDTO gameAddDTO);

    String editGame(long id, Map<String, String> map);

    String deleteGame(long id);

    Set<GamesAllDTO> getAllGames();

    String allGamesReadyForPrint();
}
