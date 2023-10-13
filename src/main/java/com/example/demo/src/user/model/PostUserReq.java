package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostUserReq {
    private String loginId;
    private String password;
    private String nickname;
    private Number countryId;
    private String birthday;
    private String phonenumber;
    private String sex;
    private String googleName;
    private Number membershipId;
    private Number membershipPaymentCardId;
    private String membershipPaymentDate;
    private String design;
    private Number limitedMode;
}
