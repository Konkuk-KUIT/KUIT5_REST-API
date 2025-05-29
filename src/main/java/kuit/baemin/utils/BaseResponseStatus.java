package kuit.baemin.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BaseResponseStatus {

    SUCCESS(true, 1000, "요청에 성공하였습니다."),

    //users
    DUPLICATED_EMAIL(false, 2001, "중복된 이메일 입니다."),
    NON_MATCH_PASSWORD(false, 2002, "비밀번호가 일치하지 않습니다."),
    SAME_PASSWORD(false, 2003, "새로운 비밀번호와 기존 비밀번호가 같습니다."),
    USER_NOT_FOUND(false, 2004, "존재하지 않는 유저입니다.");

    private final boolean isSuccess;
    private final int responseCode;
    private final String responseMessage;

}
