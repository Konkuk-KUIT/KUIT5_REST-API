package kuit.baemin.exception;

import kuit.baemin.utils.BaseResponseStatus;

// 비즈니스 오류 처리.
public class BusinessException extends RuntimeException{
    private final BaseResponseStatus status;

    public BusinessException(BaseResponseStatus status) {
        super(status.getResponseMessage());
        this.status = status;
    }

    public BaseResponseStatus getStatus() {
        return status;
    }
}
