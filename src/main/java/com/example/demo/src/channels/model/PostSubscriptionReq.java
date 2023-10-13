package com.example.demo.src.channels.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostSubscriptionReq {
    private int userId;
    private Number subscribedChannelUserId;
}