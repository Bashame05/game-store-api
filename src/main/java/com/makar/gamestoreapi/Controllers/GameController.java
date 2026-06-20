package com.makar.gamestoreapi.Controllers;


import com.makar.gamestoreapi.Models.Game;
import com.makar.gamestoreapi.Services.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService){
        this.gameService = gameService;
    }

    @GetMapping("/games")
    public ResponseEntity<List<Game>> getGames(){
        return new ResponseEntity<>(gameService.getGamesList(), HttpStatus.OK);
    }

    @GetMapping("/games/{gameId}")
    public ResponseEntity<Game> getGamesById(@PathVariable int gameId){
        return new ResponseEntity<>(gameService.getGamesById(gameId),HttpStatus.OK);
    }

    @PostMapping("/games")
    public ResponseEntity<?> addGame(@RequestPart Game game,
                                          @RequestPart MultipartFile gameThumbnail){
        try{
            Game addedGame = gameService.addGame(game,gameThumbnail);
            return new ResponseEntity<>(addedGame,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/games/{gameId}")
    public ResponseEntity<?> updateGame(@PathVariable int gameId,
                                             @RequestPart Game game,
                                             @RequestPart MultipartFile gameThumbnail){
        Game gameToUpdate = gameService.getGamesById(gameId);
        if(gameToUpdate == null){
            return new ResponseEntity<>("GAME NOT FOUND",HttpStatus.NOT_FOUND);
        }
        try {
            Game updatedGame = gameService.updateGame(game,gameThumbnail);
            return new ResponseEntity<>(updatedGame,HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/games/{gameId}")
    public ResponseEntity<String> deleteGame(@PathVariable int gameId){
        Game gameToDelete = gameService.getGamesById(gameId);
        if(gameToDelete != null) {
            gameService.deleteGame(gameId);
            return new ResponseEntity<>("Game Deleted Successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Game not Found",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/games/byName/{gameName}")
    public ResponseEntity<Game> getGameByGameName(@PathVariable String gameName){
        return new ResponseEntity<>(gameService.getGameByName(gameName),HttpStatus.OK);
    }

    @GetMapping("/games/{gameId}/image")
    public ResponseEntity<byte[]> getGameThumbnailById(@PathVariable int gameId){
        Game requiredGame = gameService.getGamesById(gameId);
        byte[] gameThumbnailData = requiredGame.getGameThumbnailData();
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(requiredGame.getGameThumbnailType()))
                .body(gameThumbnailData);
    }
}
