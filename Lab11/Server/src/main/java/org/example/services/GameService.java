package org.example.services;

import org.example.entities.Game;
import org.example.repositories.GameRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;


import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {
    GameRepository gameRepository;
    public List<Game> getAll() {
        return gameRepository.findAll();
    }
}
