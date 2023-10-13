package com.example.demo.src.user.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetUserRes {
    private String nickname;
    private String googleName;
    //private Number subscribedChannelUserId;
    //private userId;
}
