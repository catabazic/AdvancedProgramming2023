package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.Request.UpdateName;
import org.example.entities.Player;
import org.example.repositories.PlayerRepository;
import org.example.services.PlayerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping({"/player"})
public class PlayerController {
    PlayerRepository personRepository;
    PlayerService playerService;

    @GetMapping(path = "/all")
    public List<Player> getAll() {
        return playerService.getAll();
    }

    @PostMapping(path  ="/add")
    public void addPlayer(@RequestBody String name){
        playerService.addPlayer(name);
    }

    @DeleteMapping(path = "/delete")
    public void deletePlayer(@RequestBody Integer id){
        playerService.deletePlayer(personRepository.findById(id));
    }

    @PutMapping(path = "/update")
    public void updateName(@RequestBody UpdateName update){
        playerService.updateName(personRepository.findById(update.getId()),update.getName());
    }


}
