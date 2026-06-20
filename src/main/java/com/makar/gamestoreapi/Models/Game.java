package com.makar.gamestoreapi.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
@Entity
@Table(name = "game_store")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int gameId;
    private  String gameName;
    private  int gamePrice;
    private String gameDeveloper;
    //Image variables
    private String gameThumbnailName;
    private String gameThumbnailType;
    @Lob
    private byte[] gameThumbnailData;

}
