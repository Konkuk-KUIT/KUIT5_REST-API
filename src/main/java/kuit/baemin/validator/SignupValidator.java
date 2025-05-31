package kuit.baemin.validator;

import kuit.baemin.dto.request.user.SignupRequest;
import kuit.baemin.exception.BusinessException;
import kuit.baemin.utils.BaseResponseStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

// controller에 @Validated을 붙이면, 스프링이 이 SignupValidator을 자동으로 호출
// -> BindingResult에 에러를 담아줌.
@Component
public class SignupValidator implements Validator {

    // SignupRequest 또는 그 서브타입이 넘어올때만 validated가 호출.
    @Override
    public boolean supports(Class<?> clazz) {
        return SignupRequest.class.isAssignableFrom(clazz);
    }

    // 비즈니스 규칙 ; password = confirmPassword인지 체크
    // 오류가 있으면 ObjectError로 등록
    @Override
    public void validate(Object target, Errors errors) {
        SignupRequest request = (SignupRequest) target;
        if (!request.getPassword().equals(request.getConfirmPassword())) {
//            errors.reject("signup", "Passwords do not match");
            throw new BusinessException(BaseResponseStatus.NON_MATCH_PASSWORD);
        }
    }
}
