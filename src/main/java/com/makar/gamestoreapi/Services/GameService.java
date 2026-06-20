package com.makar.gamestoreapi.Services;

import com.makar.gamestoreapi.Models.Game;
import com.makar.gamestoreapi.Repository.GameRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class GameService {

    GameRepository gameRepository;
    public GameService(GameRepository gameRepository){
        this.gameRepository = gameRepository;
    }

    public List<Game> getGamesList(){
        return gameRepository.findAll();
    }

    public Game getGamesById(int gameId){
        return gameRepository.findById(gameId).orElse(null);
    }

    public Game addGame(Game game , MultipartFile gameThumbnail) throws IOException {
        game.setGameThumbnailName(gameThumbnail.getOriginalFilename());
        game.setGameThumbnailType(gameThumbnail.getContentType());
        game.setGameThumbnailData(gameThumbnail.getBytes());
        return gameRepository.save(game);
    }


    public Game updateGame(Game game,MultipartFile gameThumbnail) throws IOException {
        game.setGameThumbnailName(gameThumbnail.getOriginalFilename());
        game.setGameThumbnailType(gameThumbnail.getContentType());
        game.setGameThumbnailData(gameThumbnail.getBytes());
        return gameRepository.save(game);
    }

    public void deleteGame(int gameId){
        gameRepository.deleteById(gameId);
    }

    public Game getGameByName(String gameName){
        return gameRepository.findByGameName(gameName);
    }
}
