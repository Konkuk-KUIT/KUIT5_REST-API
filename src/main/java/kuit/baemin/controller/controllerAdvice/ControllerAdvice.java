package kuit.baemin.controller.controllerAdvice;

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
public class ControllerAdvice {

    private final SignupValidator signupValidator;

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<Object> handleRuntimeException(RuntimeException e) {
        log.error(e.getMessage(), e);
        if (e.getMessage().equals(BaseResponseStatus.DUPLICATED_EMAIL.getResponseMessage())) {
            return new BaseResponse<>(BaseResponseStatus.DUPLICATED_EMAIL);
        }

        return new BaseResponse<>(BaseResponseStatus.INVALID_REQUEST);
    }


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


    @InitBinder("signupRequest")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(signupValidator);
    }

}
