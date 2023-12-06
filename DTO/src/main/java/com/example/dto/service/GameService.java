package com.example.dto.service;


import com.example.dto.model.entitiy.dto.AllGamesDto;
import com.example.dto.model.entitiy.dto.DetailGameDto;
import com.example.dto.model.entitiy.dto.GameAddDto;

import java.math.BigDecimal;
import java.util.List;

public interface GameService {
    void addGame(GameAddDto gameAddDto);

    void editGame(Long gameId, BigDecimal price, Double size);

    void deleteGame(long gameId);

    List<AllGamesDto> allGames();

    DetailGameDto detailGame(String title);
}