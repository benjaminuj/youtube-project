package com.example.demo.src.contents;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.contents.model.*;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import static com.example.demo.config.BaseResponseStatus.*;

@RestController
@RequestMapping("/app/contents")
public class ContentsController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final ContentsProvider contentsProvider;
    @Autowired
    private final ContentsService contentsService;
    @Autowired
    private final JwtService jwtService;




    public ContentsController(ContentsProvider contentsProvider, ContentsService contentsService, JwtService jwtService){
        this.contentsProvider = contentsProvider;
        this.contentsService = contentsService;
        this.jwtService = jwtService;
    }

     /**
     * 비디오 업로드 API
     * [POST] /app/contents/video
     * @return BaseResponse<PostVideoRes>
     */
    // Body
    @ResponseBody
    @PostMapping("/video")
    public BaseResponse<PostVideoRes> createVideo(@RequestBody PostVideoReq postVideoReq) {
        try{
            PostVideoRes postVideoRes = contentsService.createVideo(postVideoReq);
            return new BaseResponse<>(postVideoRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }

    }


    /**
     * 동영상 오프라인 저장하기 API
     * [POST] /app/contents/video/saved
     * @return BaseResponse<PostOfflineVideoRes>
     */
    // Body
    @ResponseBody
    @PostMapping("/video/saved")
    public BaseResponse<PostOfflineVideoRes> OfflineVideo(@RequestBody PostOfflineVideoReq postOfflineVideoReq) {
        try{
            PostOfflineVideoRes postOfflineVideoRes = contentsService.OfflineVideo(postOfflineVideoReq);
            return new BaseResponse<>(postOfflineVideoRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }

    }

    /**
     * 나중에 볼 동영상 저장하기 API
     * [POST] /app/contents/video/later
     * @return BaseResponse<PostLaterVideoRes>
     */
    // Body
    @ResponseBody
    @PostMapping("/video/later")
    public BaseResponse<PostLaterVideoRes> LaterVideo(@RequestBody PostLaterVideoReq postLaterVideoReq) {
        try{
            PostLaterVideoRes postLaterVideoRes = contentsService.LaterVideo(postLaterVideoReq);
            return new BaseResponse<>(postLaterVideoRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }

    }

    /**
     * 동영상 좋아요 표시하기API
     * [POST] /app/contents/video/liked
     * @return BaseResponse<PostLikedVideoRes>
     */
    // Body
    @ResponseBody
    @PostMapping("/video/liked")
    public BaseResponse<PostLikedVideoRes> LikedVideo(@RequestBody PostLikedVideoReq postLikedVideoReq) {
        try{
            PostLikedVideoRes postLikedVideoRes = contentsService.LikedVideo(postLikedVideoReq);
            return new BaseResponse<>(postLikedVideoRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }

    }

    /**
     * 나의시청기록에 추가 API
     * [POST] /app/contents/video/watched
     * @return BaseResponse<PostWatchedVideoRes>
     */
    // Body
    @ResponseBody
    @PostMapping("/video/watched")
    public BaseResponse<PostWatchedVideoRes> WatchedVideo(@RequestBody PostWatchedVideoReq postWatchedVideoReq) {
        try{
            PostWatchedVideoRes postWatchedVideoRes = contentsService.WatchedVideo(postWatchedVideoReq);
            return new BaseResponse<>(postWatchedVideoRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }

    }

    /**
     * 댓글달기 API
     * [POST] /app/contents/comment
     * @return BaseResponse<PostCommentRes>
     */
    // Body
    @ResponseBody
    @PostMapping("/comment")
    public BaseResponse<PostCommentRes> CreateComment(@RequestBody PostCommentReq postCommentReq) {
        try{
            PostCommentRes postCommentRes = contentsService.CreateComment(postCommentReq);
            return new BaseResponse<>(postCommentRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }

    }

    /**
     * 댓글좋아요 API
     * [POST] /app/contents/comment/liked
     * @return BaseResponse<PostLikedCommentRes>
     */
    // Body
    @ResponseBody
    @PostMapping("/comment/liked")
    public BaseResponse<PostLikedCommentRes> LikedComment(@RequestBody PostLikedCommentReq  postLikedCommentReq) {
        try{
            PostLikedCommentRes postLikedCommentRes = contentsService.LikedComment(postLikedCommentReq);
            return new BaseResponse<>(postLikedCommentRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }

    }

    /**
     * 댓글수정 API
     * [PATCH] /app/contents/{commentId}
     * @return BaseResponse<String>
     */
    @ResponseBody
    @PatchMapping("/edit/{userId}")
    public BaseResponse<PatchCommentRes> modifyComment(@PathVariable("userId") int userId, @RequestBody Comments comments) {
        try {
            //jwt에서 id 추출.
            int userIdByJwt = jwtService.getUserId();
            //userIdx와 접근한 유저가 같은지 확인
            if(userId != userIdByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            PatchCommentReq patchCommentReq = new PatchCommentReq(comments.getCommentId(), comments.getComment(), userId);
            PatchCommentRes patchCommentRes = contentsService.modifyComment(patchCommentReq);

        return new BaseResponse<>(patchCommentRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }


    /**
     * 컨텐츠 싫어요 누르기 API
     * [POST] /app/contents/disliked
     * @return BaseResponse<PostDislikedContentsRes>
     */
    // Body
    @ResponseBody
    @PostMapping("/disliked")
    public BaseResponse<PostDislikedContentsRes> DislikedContents(@RequestBody PostDislikedContentsReq  postDislikedContentsReq) {
        try{
            PostDislikedContentsRes postDislikedContentsRes = contentsService.DislikedContents(postDislikedContentsReq);
            return new BaseResponse<>(postDislikedContentsRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }

    }





    



    
}

