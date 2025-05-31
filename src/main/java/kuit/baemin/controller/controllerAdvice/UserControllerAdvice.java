package kuit.baemin.controller.controllerAdvice;

import kuit.baemin.controller.UserController;
import kuit.baemin.utils.BaseResponse;
import kuit.baemin.utils.BaseResponseStatus;
import kuit.baemin.validator.SignupValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

//    @ExceptionHandler(RuntimeException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public BaseResponse<Object> handleRuntimeException(RuntimeException e) {
//        log.error(e.getMessage(), e);
//        return new BaseResponse<>(BaseResponseStatus.NON_MATCH_PASSWORD);
//    }


    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<Object> handleValidationException(org.springframework.web.bind.MethodArgumentNotValidException e) {
        String errorCode = e.getBindingResult()
                .getAllErrors()
                .get(0)
                .getCode();

        String errorMessage = e.getBindingResult()
                .getAllErrors()
                .get(0)
                .getDefaultMessage();

        log.error("Validation error code: {}, message: {}", errorCode, errorMessage);

        try {
            BaseResponseStatus status = BaseResponseStatus.valueOf(errorCode);
            return new BaseResponse<>(status);
        } catch (IllegalArgumentException ex) {
            return new BaseResponse<>(false, 4000, errorMessage);
        }
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(signupValidator);
    }

}
