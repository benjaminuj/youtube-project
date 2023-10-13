package com.example.demo.config;

import lombok.Getter;

/**
 * 에러 코드 관리
 */
@Getter
public enum BaseResponseStatus {
    /**
     * 1000 : 요청 성공
     */
    SUCCESS(true, 1000, "요청에 성공하였습니다."),


    /**
     * 2000 : Request 오류
     */
    // Common
    REQUEST_ERROR(false, 2000, "입력값을 확인해주세요."),
    EMPTY_JWT(false, 2001, "JWT를 입력해주세요."),
    INVALID_JWT(false, 2002, "유효하지 않은 JWT입니다."),
    INVALID_USER_JWT(false,2003,"권한이 없는 유저의 접근입니다."),

    // users
    USERS_EMPTY_USER_ID(false, 2010, "유저 아이디 값을 확인해주세요."),

    // [POST] app/users/signup
    POST_USERS_EMPTY_LOGINID(false, 2015, "아이디를 입력해주세요."),
    POST_USERS_EXISTS_LOGINID(false,2016,"중복된 아이디입니다."),

    POST_USERS_EMPTY_PASSWORD(false, 2017, "비밀번호를 입력해주세요."),

    POST_USERS_EMPTY_NICKNAME(false, 2018, "닉네임(채널명)을 입력해주세요."),
    POST_USERS_EXISTS_NICKNAME(false, 2019, "중복된 닉네임(채널명)입니다."),

    POST_USERS_EMPTY_COUNTRYID(false, 2020, "국가를 입력해주세요."),
    POST_USERS_VALID_COUNTRYID(false, 2021, "국가 형식을 확인해주세요."),

    POST_USERS_EMPTY_BIRTHDAT(false, 2022, "생일을 입력해주세요."),
    POST_USERS_VALID_BIRTHDAT(false, 2023, "생일 형식을 확인해주세요."),

    POST_USERS_EMPTY_PHONENUMBER(false, 2024, "전화번호를 입력해주세요."),
    POST_USERS_VALID_PHONENUMBER(false, 2025, "전화번호 형식을 확인해주세요."),

    POST_USERS_EMPTY_SEX(false, 2026, "성별을 입력해주세요."),
    POST_USERS_VALID_SEX(false, 2027, "성별 형식을 확인해주세요."),

    POST_USERS_EMPTY_GOOGLENAME(false, 2028, "구글 닉네임을 입력해주세요."),

    POST_USERS_EMPTY_MEMBERSHIPID(false, 2029, "멤버십을 입력해주세요."),
    POST_USERS_VALID_MEMBERSHIPID(false, 2030, "멤버십 형식을 확인해주세요."),

    POST_USERS_EMPTY_MEMBERSHIPPYAMENTCARDID(false, 2031, "멤버십 결제시 사용할 카드를 입력해주세요."),
    POST_USERS_VALID_MEMBERSHIPPYAMENTCARDID(false, 2032, "멤버십 결제시 사용할 카드의 형식을 확인해주세요."),

    POST_USERS_EMPTY_DESIGN(false, 2033, "디자인테마를 입력해주세요."),
    POST_USERS_VALID_DESIGN(false, 2034, "디자인테마 형식을 확인해주세요."),

    POST_USERS_EMPTY_LIMITEDMODE(false, 2035, "제한모드 형식을 확인해주세요."),
    POST_USERS_VALID_LIMITEDMODE(false, 2036, "제한모드 형식을 확인해주세요."),


    /**
     * 3000 : Response 오류
     */
    // Common
    RESPONSE_ERROR(false, 3000, "값을 불러오는데 실패하였습니다."),

    // [POST] /users
    DUPLICATED_EMAIL(false, 3013, "중복된 이메일입니다."),
    FAILED_TO_LOGIN(false,3014,"없는 아이디거나 비밀번호가 틀렸습니다."),



    /**
     * 4000 : Database, Server 오류
     */
    DATABASE_ERROR(false, 4000, "데이터베이스 연결에 실패하였습니다."),
    SERVER_ERROR(false, 4001, "서버와의 연결에 실패하였습니다."),

    //[PATCH] /users/{userIdx}
    MODIFY_FAIL_USERNAME(false,4014,"유저네임 수정 실패"),
    MODIFY_FAIL_COMMENT(false,4015,"댓글 수정 실패"),

    PASSWORD_ENCRYPTION_ERROR(false, 4011, "비밀번호 암호화에 실패하였습니다."),
    PASSWORD_DECRYPTION_ERROR(false, 4012, "비밀번호 복호화에 실패하였습니다."),
    

    // ADD API Error
    CREATE_USER_ERROR(false, 5000, "user 생성에 실패하였습니다."),
    CHECK_LOGINID_ERROR(false, 5001, "id 중복체크에 실패하였습니다."),
    GET_USER_ERROR(false, 5002, "user정보를 가져오는데에 실패하였습니다."),
    GET_MY_CHANNEL_ERROR(false, 5003, "myChannel 정보를 가져오는데에 실패하였습니다."),
    SUBSCRIPTION_ERROR(false, 5004, "구독에 실패하였습니다."),
    CANCLE_SUBSCRIPTION_ERROR(false,5005,"구독취소에 실패하였습니다."),
    ALARM_ON_ERROR(false,5006,"알람설정에 실패하였습니다."),
    ALARM_OFF_ERROR(false,5007,"알람끄기에 실패하였습니다."),
    CREATE_VIDEO_ERROR(false,5008,"비디오 업로드에 실패하였습니다."),
    GET_MYPLAYLIST_ERROR(false, 5009, "나의 재생목록을 가져오는데 실패하였습니다."),
    GET_MY_SUBSCRIPTION_ERROR(false, 5010, "나의 구독목록을 가져오는데 실패하였습니다.");

    // 5000 : 필요시 만들어서 쓰세요
    // 6000 : 필요시 만들어서 쓰세요


    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
