package com.example.demo.src.channels;


import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.channels.model.*;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.SHA256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;


//Provider : Read의 비즈니스 로직 처리
@Service
public class ChannelsProvider {

    private final ChannelsDao channelsDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ChannelsProvider(ChannelsDao channelsDao, JwtService jwtService) {
        this.channelsDao = channelsDao;
        this.jwtService = jwtService;
    }

    public List<GetSubscriptionRes> getMySubscription (int userIdByJwt) throws BaseException {
        try {
            List<GetSubscriptionRes> getSubscriptionRes = channelsDao.getMySubscription(userIdByJwt);
            return getSubscriptionRes;
        } catch (Exception exception) {
            throw new BaseException(GET_MY_SUBSCRIPTION_ERROR);
        }
    }
}