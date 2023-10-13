package com.example.demo.src.user.model;

import org.hibernate.type.descriptor.sql.BigIntTypeDescriptor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetMyChannelReq{
    private int userId;
    private String jwt;
}
