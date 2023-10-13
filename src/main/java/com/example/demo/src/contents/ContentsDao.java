package com.example.demo.src.contents;


import com.example.demo.src.contents.model.*;
import com.fasterxml.jackson.databind.deser.impl.CreatorCandidate.Param;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Arrays;

@Repository
public class ContentsDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int createVideo(PostVideoReq postVideoReq){

        String Query = "insert into contents (title, playTime,userId,typeId) VALUES(?,?,?,?)";
        Object[] Params = new Object[]{postVideoReq.getTitle(),postVideoReq.getPlayTime(),postVideoReq.getUserId(),postVideoReq.getTypeId() };
        int rs = this.jdbcTemplate.update(Query, Params);

        return rs;
    }

    public int OfflineVideo(PostOfflineVideoReq postOfflineVideoReq){

        String Query = "insert into offline_saved (userId,savedContentsId) VALUES(?,?)";
        Object[] Params = new Object[]{postOfflineVideoReq.getUserId(), postOfflineVideoReq.getSavedContentsId()};
        int rs = this.jdbcTemplate.update(Query, Params);

        return rs;
    }

    public int LaterVideo(PostLaterVideoReq postLaterVideoReq){

        String Query = "insert into video_to_watch_later (userId,contentsId) VALUES(?,?)";
        Object[] Params = new Object[]{postLaterVideoReq.getUserId(), postLaterVideoReq.getContentsId()};
        int rs = this.jdbcTemplate.update(Query, Params);

        return rs;
    }

    public int LikedVideo(PostLikedVideoReq postLikedVideoReq){

        String Query = "insert into liked_contents (userId,likedContentsId) VALUES(?,?)";
        Object[] Params = new Object[]{postLikedVideoReq.getUserId(), postLikedVideoReq.getLikedContentsId()};
        int rs = this.jdbcTemplate.update(Query, Params);

        return rs;
    }

    public int WatchedVideo(PostWatchedVideoReq postWatchedVideoReq){

        String Query = "insert into watched_contents (userId,contentsId) VALUES(?,?)";
        Object[] Params = new Object[]{postWatchedVideoReq.getUserId(), postWatchedVideoReq.getContentsId()};
        int rs = this.jdbcTemplate.update(Query, Params);

        return rs;
    }

    public int CreateComment(PostCommentReq postCommentReq){

        String Query = "insert into comments (comment, contentsId, userId, reply) VALUES(?,?,?,?)";
        Object[] Params = new Object[]{postCommentReq.getComment(), postCommentReq.getContentsId(), postCommentReq.getUserId(), postCommentReq.getReply()};
        int rs = this.jdbcTemplate.update(Query, Params);

        return rs;
    }

    public int LikedComment(PostLikedCommentReq postLikedCommentReq){

        String Query = "insert into liked_comments (commentId, userId) VALUES(?,?)";
        Object[] Params = new Object[]{postLikedCommentReq.getCommentId(), postLikedCommentReq.getUserId()};
        int rs = this.jdbcTemplate.update(Query, Params);

        return rs;
    }

    public int modifyComment(PatchCommentReq patchCommentReq){
        System.out.println("st");
        String modifyCommentQuery = "update comments set comment= ? where commentId = ? and userId = ?";
        Object[] modifyCommentParams = new Object[]{patchCommentReq.getComment(), patchCommentReq.getCommentId(), patchCommentReq.getUserId()};

        int rs =  this.jdbcTemplate.update(modifyCommentQuery,modifyCommentParams);
        return rs;
    }

    public int DislikedContents(PostDislikedContentsReq postDislikedContentsReq){

        String Query = "insert into disliked_contents (userId, dislikedContentsId) VALUES(?,?)";
        Object[] Params = new Object[]{postDislikedContentsReq.getUserId(), postDislikedContentsReq.getDislikedContentsId()};
        int rs = this.jdbcTemplate.update(Query, Params);

        return rs;
    }

}