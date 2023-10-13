package com.example.demo.src.channels.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Subscribed {
    private int userId;
    private int subscribedChannelUserId;
}
