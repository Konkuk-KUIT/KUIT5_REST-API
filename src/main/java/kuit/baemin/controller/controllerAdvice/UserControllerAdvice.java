package kuit.baemin.controller.controllerAdvice;

import kuit.baemin.exception.BusinessException;
import kuit.baemin.utils.BaseResponse;
import kuit.baemin.utils.BaseResponseStatus;
import kuit.baemin.validator.PasswordChangeValidator;
import kuit.baemin.validator.SignupValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class UserControllerAdvice {

    private final SignupValidator signupValidator;
    private final PasswordChangeValidator passwordChangeValidator;

    // new BusinessException이 들어오면.
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<Object> handleBusiness(BusinessException ex) {
        log.error(ex.getMessage(), ex);
        return new BaseResponse<>(ex.getStatus());
    }

    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<Object> handleDuplicateKey(DuplicateKeyException ex) {
        log.error(ex.getMessage(), ex);
        return new BaseResponse<>(BaseResponseStatus.DUPLICATED_EMAIL);
    }

    // SignupRequest 바인딩 때만 호출
    @InitBinder("signupRequest")
    public void signupInitBinder(WebDataBinder binder) {
        binder.addValidators(signupValidator);
    }

    // PasswordChangeRequest 바인딩 때만 호출
    @InitBinder("passwordChangeRequest")
    public void passwordInitBinder(WebDataBinder binder) {
        binder.addValidators(passwordChangeValidator);
    }

}
