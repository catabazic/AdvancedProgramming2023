package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.entities.Player;
import org.example.repositories.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PlayerService {
    PlayerRepository playerRepository;

    public List<Player> getAll() {
        return playerRepository.findAll();
    }

    public void addPlayer(String name){
        if(name==null){
            throw new NullPointerException("Player is null");
        }
        Player player=new Player();
        player.setName(name);
        this.playerRepository.save(player);
    }

    public void deletePlayer(Optional<Player> player){
        if(player.isEmpty()){
            throw new NullPointerException("Player is null");
        }
        Player pl=player.get();
        this.playerRepository.delete(pl);
    }

    public void updateName(Optional<Player> player, String name){
        if(player==null){
            throw new NullPointerException("Player is null");
        }
        if(name==null){
            throw new NullPointerException("Name is null");
        }
        Player player1=player.get();
        player1.setName(name);
        this.playerRepository.delete(player.get());
        this.playerRepository.save(player1);
    }


}
