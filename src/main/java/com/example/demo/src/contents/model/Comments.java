package com.example.demo.src.contents.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Comments {
    private String comment;
    private int commentId;
    private int userId;
}
