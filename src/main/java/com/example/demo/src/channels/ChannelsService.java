package com.example.demo.src.channels;

import com.example.demo.config.BaseException;
import com.example.demo.src.channels.model.*;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.SHA256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

// Service Create, Update, Delete 의 로직 처리
@Service
public class ChannelsService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ChannelsDao channelsDao;
    private final ChannelsProvider channelsProvider;
    private final JwtService jwtService;


    @Autowired
    public ChannelsService(ChannelsDao channelsDao, ChannelsProvider channelsProvider, JwtService jwtService) {
        this.channelsDao = channelsDao;
        this.channelsProvider = channelsProvider;
        this.jwtService = jwtService;

    }
    
    //POST
    public PostSubscriptionRes subscription(PostSubscriptionReq postSubscriptionReq) throws BaseException {
        
        try{

            int rs = channelsDao.subscription(postSubscriptionReq);
            return new PostSubscriptionRes(rs);
        } catch (Exception exception) {
            throw new BaseException(SUBSCRIPTION_ERROR);
        }
    }

    //DELETE
    public DeleteSubscriptionRes cancelSubscription(DeleteSubscriptionReq deleteSubscriptionReq) throws BaseException {
        
        try{
            int rs = channelsDao.cancelSubscription(deleteSubscriptionReq);
            return new DeleteSubscriptionRes(rs);
        } catch (Exception exception) {
            throw new BaseException(CANCLE_SUBSCRIPTION_ERROR);
        }
    }


}
