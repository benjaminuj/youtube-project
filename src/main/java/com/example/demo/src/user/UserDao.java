package com.example.demo.src.user;


import com.example.demo.src.user.model.*;
import com.fasterxml.jackson.databind.deser.impl.CreatorCandidate.Param;

import org.hibernate.query.criteria.internal.path.ListAttributeJoin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;


@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetUserRes> getUsers(){
        String getUsersQuery = "select * from UserInfo";
        return this.jdbcTemplate.query(getUsersQuery,
                (rs,rowNum) -> new GetUserRes(
                    rs.getString("nickname"),
                    rs.getString("googleName"))
                );
    }

    public List<GetUserRes> getUsersByEmail(String email){
        String getUsersByEmailQuery = "select * from UserInfo where email =?";
        String getUsersByEmailParams = email;
        return this.jdbcTemplate.query(getUsersByEmailQuery,
                (rs, rowNum) -> new GetUserRes(
                    rs.getString("nickname"),
                    rs.getString("googleName")),
                getUsersByEmailParams);
    }

    public GetUserRes getUser(int userId){
        String getUserQuery = "select nickname, googleName from user where userId = ?";
        //Object[] createUserParams = new Object[]{userId, userId};
        //select u.nickname, count(*) AS subscribed, googleName from user AS u ,subscribed AS s where u.userId = ? && s.subscribedChannelUserId = ?
        int getUserParams = userId;
        return this.jdbcTemplate.queryForObject(getUserQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getString("nickname"),
                        rs.getString("googleName")),
                 getUserParams);
    }

    
    public List<GetMyChannelRes> getMyChannel(int userIdByJwt){
        String getUserQuery = "select u.userId, u.nickname, u.googleName, c.title, c.playTime from user AS u, contents AS c where u.userId = ?";
        return this.jdbcTemplate.query(getUserQuery,
                (rs, rowNum) -> new GetMyChannelRes(
                        rs.getString("nickname"),
                        rs.getString("googleName"),
                        rs.getString("title"),
                        rs.getInt("playTime")),
                        userIdByJwt);
    }

    public int AlarmOn(PostAlarmReq postAlarmReq){

        String createUserQuery = "insert into alarm_channel(userId,alarmOnChannelId) VALUE(?,?)";
        Object[] createUserParams = new Object[]{postAlarmReq.getUserId(), postAlarmReq.getAlarmOnChannelId()};
        
        this.jdbcTemplate.update(createUserQuery, createUserParams);

        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);
    }

    public int AlarmOff(int userId){

        String createUserQuery = "delete from alarm_channel where alarmOnChannelId = ? and userId=?";
        Object[] createUserParams = new Object[]{userId};
        
        this.jdbcTemplate.update(createUserQuery, createUserParams);

        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);
    }

    public int createUser(PostUserReq postUserReq){

        String createUserQuery = "insert into user (loginId, password, nickname, countryId, birthday, phonenumber, sex, googleName, membershipId, membershipPaymentCardId, design, limitedMode) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        Object[] createUserParams = new Object[]{postUserReq.getLoginId(), postUserReq.getPassword(), postUserReq.getNickname(), postUserReq.getCountryId(), postUserReq.getBirthday(), postUserReq.getPhonenumber(), postUserReq.getSex(), postUserReq.getGoogleName(), postUserReq.getMembershipId(), postUserReq.getMembershipPaymentCardId(), postUserReq.getDesign(),postUserReq.getLimitedMode()};
        
        this.jdbcTemplate.update(createUserQuery, createUserParams);

        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);
    }

    public GetCheckLoginIdRes checkLoginId(String loginId){
        String checkLoginIdQuery = "select loginId from user where loginId = ?";
        // select exists(select loginId from user where loginId = ?)
        return this.jdbcTemplate.queryForObject(checkLoginIdQuery,
                (rs, rowNum) -> new GetCheckLoginIdRes(
                        rs.getString("loginId")),
                loginId);

    }

    public int modifyUserName(PatchUserReq patchUserReq){
        String modifyUserNameQuery = "update UserInfo set userName = ? where userIdx = ? ";
        Object[] modifyUserNameParams = new Object[]{patchUserReq.getUserName(), patchUserReq.getUserIdx()};

        return this.jdbcTemplate.update(modifyUserNameQuery,modifyUserNameParams);
    }

    public User getPwd(PostLoginReq postLoginReq){
        String getPwdQuery = "select userId, loginId, password, nickname from user where loginId = ?";
        String getPwdParams = postLoginReq.getLoginId();

        return this.jdbcTemplate.queryForObject(getPwdQuery,
                (rs,rowNum)-> new User(
                        rs.getInt("userId"),
                        rs.getString("loginId"),
                        rs.getString("password"),
                        rs.getString("nickname")
                ),
                getPwdParams
                );

    }

    public List<GetMyPlaylistRes> getMyPlaylist(int userIdByJwt){
        String getMyPlaylistQuery = "SELECT userId, playlistId, playlistName from my_playlist where userId=?";
        return this.jdbcTemplate.query(getMyPlaylistQuery,
                (rs, rowNum) -> new GetMyPlaylistRes(
                        rs.getInt("userId"),
                        rs.getInt("playlistId"),
                        rs.getString("playlistName")),
                    userIdByJwt);
    }


}
