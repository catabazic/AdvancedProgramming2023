package org.example.services;

import org.example.entities.Game;
import org.example.repositories.GameRepository;

import java.util.List;

public class GameService {
    GameRepository gameRepository;
    public List<Game> getAll() {
        return gameRepository.findAll();
    }
}
