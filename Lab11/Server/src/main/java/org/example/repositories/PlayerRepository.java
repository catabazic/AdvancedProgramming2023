package org.example.repositories;

import org.example.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PlayerRepository extends  JpaRepository<Player, Integer> {

    public List<Player> findAll();
}
