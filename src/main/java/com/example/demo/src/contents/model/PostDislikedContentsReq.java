package com.example.demo.src.contents.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class PostDislikedContentsReq {
    private int userId;
    private int dislikedContentsId;
    
}
