package com.example.demo.src.channels;


import com.example.demo.src.channels.model.*;
import com.fasterxml.jackson.databind.deser.impl.CreatorCandidate.Param;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;


@Repository
public class ChannelsDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int subscription(PostSubscriptionReq postSubscriptionReq){

        String createUserQuery = "insert into subscribed (userId, subscribedChannelUserId) VALUES (?,?)";
        Object[] createUserParams = new Object[]{postSubscriptionReq.getUserId(), postSubscriptionReq.getSubscribedChannelUserId()};
        int rs = this.jdbcTemplate.update(createUserQuery, createUserParams);
        
        return rs;
    }

    public int cancelSubscription(DeleteSubscriptionReq deleteSubscriptionReq){

        String Query = "delete from subscribed where subscribedChannelUserId = ?";
        Object[] Params = new Object[]{deleteSubscriptionReq.getSubscribedChannelUserId()};
        int rs = this.jdbcTemplate.update(Query, Params);

        return rs;
    }

    public List<GetSubscriptionRes> getMySubscription(int userIdByJwt){
        String Query = "SELECT subscribedChannelUserId FROM subscribed where userId=?";
        return this.jdbcTemplate.query(Query,
                (rs, rowNum) -> new GetSubscriptionRes(
                        rs.getInt("subscribedChannelUserId")),
                        userIdByJwt);
    }


}

