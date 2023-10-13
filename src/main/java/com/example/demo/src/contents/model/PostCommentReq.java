package com.example.demo.src.contents.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostCommentReq {
    private String comment;
    private int contentsId;
    private int userId;
    private String reply;

}