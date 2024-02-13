package com.dtoEx.services;

import com.dtoEx.models.dto.GameAddDto;

import java.math.BigDecimal;

public interface GameService {
    void addGame(GameAddDto gameAddDto);

    void editGame(Long gameId , BigDecimal price, Double size);
}
