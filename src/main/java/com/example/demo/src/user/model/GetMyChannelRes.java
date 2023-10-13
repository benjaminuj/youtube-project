package com.example.demo.src.user.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetMyChannelRes {
    private String nickname;
    private String googleName;
    //private Number subscribedChannelUserId;
    private String title;
    private Number playTime;
    //private Object theDetails;
}
