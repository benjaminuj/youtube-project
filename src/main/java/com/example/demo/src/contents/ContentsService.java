package com.example.demo.src.contents;



import com.example.demo.config.BaseException;
import com.example.demo.src.contents.model.*;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.SHA256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
public class ContentsService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ContentsDao contentsDao;
    private final ContentsProvider contentsProvider;
    private final JwtService jwtService;


    @Autowired
    public ContentsService(ContentsDao contentsDao, ContentsProvider contentsProvider, JwtService jwtService) {
        this.contentsDao = contentsDao;
        this.contentsProvider = contentsProvider;
        this.jwtService = jwtService;

    }

    public PostVideoRes createVideo(PostVideoReq postVideoReq) throws BaseException {

        try{
            int userIdx = contentsDao.createVideo(postVideoReq);
            //jwt 발급.
            //int userIdx = 7;
            String jwt = jwtService.createJwt(userIdx);
            return new PostVideoRes(jwt,userIdx);
        } catch (Exception exception) {
            throw new BaseException(CREATE_VIDEO_ERROR);
        }
    }
    
    public PostOfflineVideoRes OfflineVideo(PostOfflineVideoReq postOfflineVideoReq) throws BaseException {

        try{
            int rs = contentsDao.OfflineVideo(postOfflineVideoReq);
            return new PostOfflineVideoRes(rs);
        } catch (Exception exception) {
            throw new BaseException(CREATE_VIDEO_ERROR);
        }
    }

    public PostLaterVideoRes LaterVideo(PostLaterVideoReq postLaterVideoReq) throws BaseException {

        try{
            int rs = contentsDao.LaterVideo(postLaterVideoReq);
            return new PostLaterVideoRes(rs);
        } catch (Exception exception) {
            throw new BaseException(CREATE_VIDEO_ERROR);
        }
    }

    public PostLikedVideoRes LikedVideo(PostLikedVideoReq postLikedVideoReq) throws BaseException {

        try{
            int rs = contentsDao.LikedVideo(postLikedVideoReq);
            return new PostLikedVideoRes(rs);
        } catch (Exception exception) {
            throw new BaseException(CREATE_VIDEO_ERROR);
        }
    }

    public PostWatchedVideoRes WatchedVideo(PostWatchedVideoReq postWatchedVideoReq) throws BaseException {

        try{
            int rs = contentsDao.WatchedVideo(postWatchedVideoReq);
            return new PostWatchedVideoRes(rs);
        } catch (Exception exception) {
            throw new BaseException(CREATE_VIDEO_ERROR);
        }
    }

    public PostCommentRes CreateComment(PostCommentReq postCommentReq) throws BaseException {

        try{
            int rs= contentsDao.CreateComment(postCommentReq);

            return new PostCommentRes(rs);
        } catch (Exception exception) {
            throw new BaseException(CREATE_VIDEO_ERROR);
        }
    }

    public PostLikedCommentRes LikedComment(PostLikedCommentReq postLikedCommentReq) throws BaseException {

        try{
            int rs = contentsDao.LikedComment(postLikedCommentReq);
            return new PostLikedCommentRes(rs);
        } catch (Exception exception) {
            throw new BaseException(CREATE_VIDEO_ERROR);
        }
    }

    public PatchCommentRes modifyComment(PatchCommentReq patchCommentReq) throws BaseException {
        try{
            int rs = contentsDao.modifyComment(patchCommentReq);
            if(rs == 0){
                throw new BaseException(MODIFY_FAIL_COMMENT);
            }
            return new PatchCommentRes(rs);
        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public PostDislikedContentsRes DislikedContents(PostDislikedContentsReq postDislikedContentsReq) throws BaseException {

        try{
            int rs = contentsDao.DislikedContents(postDislikedContentsReq);
            return new PostDislikedContentsRes(rs);
        } catch (Exception exception) {
            throw new BaseException(CREATE_VIDEO_ERROR);
        }
    }

}