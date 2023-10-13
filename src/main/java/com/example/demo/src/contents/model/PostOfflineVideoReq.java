package com.example.demo.src.contents.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostOfflineVideoReq {
    private int userId;
    private int savedContentsId;

}
