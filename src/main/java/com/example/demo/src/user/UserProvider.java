package com.example.demo.src.user;


import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.user.model.*;
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
public class UserProvider {

    private final UserDao userDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public UserProvider(UserDao userDao, JwtService jwtService) {
        this.userDao = userDao;
        this.jwtService = jwtService;
    }

    public List<GetUserRes> getUsers() throws BaseException{
        try{
            List<GetUserRes> getUserRes = userDao.getUsers();
            return getUserRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetUserRes> getUsersByEmail(String email) throws BaseException{
        try{
            List<GetUserRes> getUsersRes = userDao.getUsersByEmail(email);
            return getUsersRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }


    public GetUserRes getUser(int userId) throws BaseException {
        try {
            GetUserRes getUserRes = userDao.getUser(userId);
            return getUserRes;
        } catch (Exception exception) {
            throw new BaseException(GET_USER_ERROR);
        }
    }

    public List<GetMyChannelRes> getMyChannel(int userIdByJwt) throws BaseException {
        try {
            List<GetMyChannelRes> getMyChannelRes = userDao.getMyChannel(userIdByJwt);
            return getMyChannelRes;
        } catch (Exception exception) {
            throw new BaseException(GET_MY_CHANNEL_ERROR);
        }
        
    }

    public GetCheckLoginIdRes checkLoginId(String loginId) throws BaseException{
        try{
            return userDao.checkLoginId(loginId);
        } catch (Exception exception){
            throw new BaseException(CHECK_LOGINID_ERROR);
        }
    }

    public PostLoginRes login(PostLoginReq postLoginReq) throws BaseException{
        User user = userDao.getPwd(postLoginReq);
        String encryptPwd;
        try {
            encryptPwd=new SHA256().encrypt(postLoginReq.getPassword());
        } catch (Exception ignored) {
            throw new BaseException(PASSWORD_DECRYPTION_ERROR);
        }

        if(user.getPassword().equals(encryptPwd)){
            int userIdx = user.getUserId();
            String jwt = jwtService.createJwt(userIdx);
            return new PostLoginRes(userIdx,jwt);
        }
        else{
            throw new BaseException(FAILED_TO_LOGIN);
        }

    }

    public List<GetMyPlaylistRes> getMyPlaylist(int userIdByJwt) throws BaseException {
        try {
            List<GetMyPlaylistRes> getMyPlaylistRes = userDao.getMyPlaylist(userIdByJwt);
            return getMyPlaylistRes;
        } catch (Exception exception) {
            throw new BaseException(GET_MYPLAYLIST_ERROR);
        }
    }

}
