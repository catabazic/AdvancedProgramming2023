package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.entities.Game;
import org.example.services.GameService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping({"/game"})
public class GameController {
    GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping(path = "/all")
    public List<Game> getAll(){
        return gameService.getAll();
    }
}
