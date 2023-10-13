package com.example.demo.src.channels;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.channels.model.*;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import static com.example.demo.config.BaseResponseStatus.*;

@RestController
@RequestMapping("/app/channels")
public class ChannelsController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final ChannelsProvider channelsProvider;
    @Autowired
    private final ChannelsService channelsService;
    @Autowired
    private final JwtService jwtService;

    public ChannelsController(ChannelsProvider channelsProvider, ChannelsService channelsService, JwtService jwtService){
        this.channelsProvider = channelsProvider;
        this.channelsService = channelsService;
        this.jwtService = jwtService;
    }


    /**
     * 채널 구독 API
     * [POST] /app/channels/subscription/{userId}
     * @return BaseResponse<PostSubscriptionRes>
     */
    // Body
    @ResponseBody
    @PostMapping("/subscription/{userId}")
    public BaseResponse<PostSubscriptionRes> subscription(@PathVariable("userId") int userId, @RequestBody PostSubscriptionReq postSubscriptionReq) {
    
        try{
            int userIdByJwt = jwtService.getUserId();

            if(userId != userIdByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }

            PostSubscriptionRes postSubscriptionRes = channelsService.subscription(postSubscriptionReq);
            return new BaseResponse<>(postSubscriptionRes);
        } catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }

        
    }

    /**
     * 채널 구독 취소 API
     * [DELETE] /app/channels/subscription/cancel
     * @return BaseResponse<DeleteSubscriptionRes>
     */
    // Body
    @ResponseBody
    @DeleteMapping("/subscription/cancel")
    public BaseResponse<DeleteSubscriptionRes> cancelSubscription(@RequestBody DeleteSubscriptionReq deleteSubscriptionReq) {
    
        try{
            DeleteSubscriptionRes deleteSubscriptionRes = channelsService.cancelSubscription(deleteSubscriptionReq);
            return new BaseResponse<>(deleteSubscriptionRes);
        } catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
        
    }

    /**
     * 내 구독리스트 확인 API
     * [GET] /app/channels/mysubscription
     * @return BaseResponse<GetUserRes>
     */
    // Path-variable
    @ResponseBody
    @GetMapping("/mysubscription") 
    public BaseResponse<List<GetSubscriptionRes>> getMySubscription() {
        // Get MySubscription
        try{
            int userIdByJwt = jwtService.getUserId();
        
            List<GetSubscriptionRes> getSubscriptionRes = channelsProvider.getMySubscription(userIdByJwt);
            return new BaseResponse<>(getSubscriptionRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }

    }


}