package com.example.demo.src.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.user.model.*;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import static com.example.demo.config.BaseResponseStatus.*;
//import static com.example.demo.utils.ValidationRegex.isRegexEmail;

@RestController
@RequestMapping("/app/users")
public class UserController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final UserProvider userProvider;
    @Autowired
    private final UserService userService;
    @Autowired
    private final JwtService jwtService;




    public UserController(UserProvider userProvider, UserService userService, JwtService jwtService){
        this.userProvider = userProvider;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    /**
     * 회원 가입 API
     * [POST] /app/users/signup
     * 로그인 API
     * [POST] /app/users/login
     * 유튜브 채널(유저)조회 API
     * [GET] /app/users/{userId}
     * 내채널 홈 API
     * [GET] /app/users/home/{myId}
     * 중복아이디확인 API
     * [GET] /app/users/duplicateId/{loginId}
     * 구독한 채널 알림on API 
     * [POST] /app/users/alarmon
     * 구독한 채널 알림off API (userId : 알림off한 해당채널)
     * [DELETE] /app/users/{userId}/alarmoff
     * @return BaseResponse<List<GetUserRes>>
     */
    //Query String exap

    @ResponseBody
    @GetMapping("") // (GET) 127.0.0.1:9000/app/users
    public BaseResponse<List<GetUserRes>> getUsers(@RequestParam(required = false) String Email) {
        try{
            if(Email == null){
                List<GetUserRes> getUsersRes = userProvider.getUsers();
                return new BaseResponse<>(getUsersRes);
            }
            // Get Users
            List<GetUserRes> getUsersRes = userProvider.getUsersByEmail(Email);
            return new BaseResponse<>(getUsersRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 구독한 채널 알림on API 
     * [POST] /app/users/alarmon
     * @return BaseResponse<PostAlarmRes>
     */
    // Body
    @ResponseBody
    @PostMapping("/alarmon")
    public BaseResponse<PostAlarmRes> AlarmOn(@RequestBody PostAlarmReq postAlarmReq) {
        try{
            PostAlarmRes postAlarmRes = userService.AlarmOn(postAlarmReq);
            return new BaseResponse<>(postAlarmRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 구독한 채널 알림off API (userId : 알림off한 해당채널)
     * [DELETE] /app/users/{userId}/alarmoff
     * @return BaseResponse<DeleteAlarmRes>
     */
    // Body
    @ResponseBody
    @DeleteMapping("/{userId}/alarmoff")
    public BaseResponse<DeleteAlarmRes> AlarmOff(@PathVariable("userId") int userId) {
        try{
            DeleteAlarmRes deleteAlarmRes = userService.AlarmOff(userId);
            return new BaseResponse<>(deleteAlarmRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 내채널 홈 API
     * [GET] /app/users/myhome/{userId}
     * @return BaseResponse<GetUserRes>
     */
    // Path-variable
    @ResponseBody
    @GetMapping("/myhome/{userId}") 
    public BaseResponse<List<GetMyChannelRes>> getMyChannel(@PathVariable("userId") int userId) {

        try{
            int userIdByJwt = jwtService.getUserId();
            
            //userIdx와 접근한 유저가 같은지 확인

            if(userId != userIdByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            List<GetMyChannelRes> getMyChannelRes = userProvider.getMyChannel(userIdByJwt);
            return new BaseResponse<>(getMyChannelRes);

        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }

    }

   
    

    /**
     * 유튜브 채널(유저)조회 API
     * [GET] /app/users/{userId}
     * @return BaseResponse<GetUserRes>
     */
    // Path-variable
    @ResponseBody
    @GetMapping("/{userId}") // (GET) 127.0.0.1:9000/app/users/{userId}
    public BaseResponse<GetUserRes> getUser(@PathVariable("userId") int userId) {
        // Get Users
        try{
            GetUserRes getUserRes = userProvider.getUser(userId);
            return new BaseResponse<>(getUserRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }

    }


    /**
     * 회원가입 API
     * [POST] /app/users/signup
     * @return BaseResponse<PostUserRes>
     */
    // Body
    @ResponseBody
    @PostMapping("/signup")
    public BaseResponse<PostUserRes> createUser(@RequestBody PostUserReq postUserReq) {
    
        if(postUserReq.getLoginId() == null){
            return new BaseResponse<>(BaseResponseStatus.POST_USERS_EMPTY_LOGINID);
        }

        if(postUserReq.getPassword() == null){
            return new BaseResponse<>(BaseResponseStatus.POST_USERS_EMPTY_PASSWORD);
        }

        if(postUserReq.getNickname() == null){
            return new BaseResponse<>(BaseResponseStatus.POST_USERS_EMPTY_NICKNAME);
        }

        if(postUserReq.getCountryId() == null){
            return new BaseResponse<>(BaseResponseStatus.POST_USERS_EMPTY_COUNTRYID);
        }

        if(postUserReq.getBirthday() == null){
            return new BaseResponse<>(BaseResponseStatus.POST_USERS_EMPTY_BIRTHDAT);
        }

        if(postUserReq.getPhonenumber() == null){
            return new BaseResponse<>(BaseResponseStatus.POST_USERS_EMPTY_PHONENUMBER);
        }

        if(postUserReq.getSex() == null){
            return new BaseResponse<>(BaseResponseStatus.POST_USERS_EMPTY_SEX);
        }

        if(postUserReq.getGoogleName() == null){
            return new BaseResponse<>(BaseResponseStatus.POST_USERS_EMPTY_GOOGLENAME);
        }

        if(postUserReq.getMembershipId() == null){
            return new BaseResponse<>(BaseResponseStatus.POST_USERS_EMPTY_MEMBERSHIPID);
        }

        if(postUserReq.getMembershipPaymentCardId() == null){
            return new BaseResponse<>(BaseResponseStatus.POST_USERS_EMPTY_MEMBERSHIPPYAMENTCARDID);
        }

        if(postUserReq.getDesign() == null){
            return new BaseResponse<>(BaseResponseStatus.POST_USERS_EMPTY_DESIGN);
        }

        if(postUserReq.getLimitedMode() == null){
            return new BaseResponse<>(BaseResponseStatus.POST_USERS_EMPTY_LIMITEDMODE);
        }

        
        /* 
        //이메일 정규표현
        if(!isRegexEmail(postUserReq.getPassword())){
            return new BaseResponse<>(POST_USERS_INVALID_EMAIL);
        }
        */
        try{
            PostUserRes postUserRes = userService.createUser(postUserReq);
            return new BaseResponse<>(postUserRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
        
    }


    /**
     * 로그인 API
     * [POST] /users/login
     * @return BaseResponse<PostLoginRes>
     */
    @ResponseBody
    @PostMapping("/login")
    public BaseResponse<PostLoginRes> login(@RequestBody PostLoginReq postLoginReq){

        if(postLoginReq.getLoginId() == null){
            return new BaseResponse<>(BaseResponseStatus.POST_USERS_EMPTY_LOGINID);
        }

        if(postLoginReq.getPassword() == null){
            return new BaseResponse<>(BaseResponseStatus.POST_USERS_EMPTY_PASSWORD);
        }
        try{
            // TODO: 로그인 값들에 대한 형식적인 validatin 처리해주셔야합니다!
            // TODO: 유저의 status ex) 비활성화된 유저, 탈퇴한 유저 등을 관리해주고 있다면 해당 부분에 대한 validation 처리도 해주셔야합니다.
            PostLoginRes postLoginRes = userProvider.login(postLoginReq);
            return new BaseResponse<>(postLoginRes);
        } catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 유저정보변경 API
     * [PATCH] /users/:userIdx
     * @return BaseResponse<String>
     */
    @ResponseBody
    @PatchMapping("/{userIdx}")
    public BaseResponse<String> modifyUserName(@PathVariable("userIdx") int userIdx, @RequestBody User user){
        try {
            //jwt에서 idx 추출.
            int userIdxByJwt = jwtService.getUserId();
            //userIdx와 접근한 유저가 같은지 확인
            if(userIdx != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            //같다면 유저네임 변경
            PatchUserReq patchUserReq = new PatchUserReq(userIdx,user.getLoginId());
            userService.modifyUserName(patchUserReq);

            String result = "";
        return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

      /**
     *중복아이디확인 API
     * [GET] /app/users/duplicateId/{loginId}
     * @return BaseResponse<GetUserRes>
     */
    // Path-variable
    @ResponseBody
    @GetMapping("/duplicateId/{loginId}") 
    public BaseResponse<GetCheckLoginIdRes> getCheckLoginId(@PathVariable("loginId") String loginId) {
        // Get Users
        try{
            GetCheckLoginIdRes getCheckLoginIdRes = userProvider.checkLoginId(loginId);
            return new BaseResponse<>(getCheckLoginIdRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }

    }

    /**
     * 나의 재생목록 가져오기 API
     * [GET] /app/users/myplaylist
     * @return BaseResponse<GetUserRes>
     */
    // Path-variable
    @ResponseBody
    @GetMapping("/myplaylist") 
    public BaseResponse<List<GetMyPlaylistRes>> getMyPlaylist() {
        
        try{
            int userIdByJwt = jwtService.getUserId();
            

            List<GetMyPlaylistRes> getMyPlaylistRes = userProvider.getMyPlaylist(userIdByJwt);
            return new BaseResponse<>(getMyPlaylistRes);

        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }


    }


}

