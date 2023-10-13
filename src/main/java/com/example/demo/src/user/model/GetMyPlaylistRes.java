package com.example.demo.src.user.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class GetMyPlaylistRes {
    private int userId;
    private int playlistId;
    private String playlistName;
    
}
