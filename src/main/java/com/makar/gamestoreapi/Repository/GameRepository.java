package com.makar.gamestoreapi.Repository;

import com.makar.gamestoreapi.Models.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Integer> {


    Game findByGameName(String gameName);


}
