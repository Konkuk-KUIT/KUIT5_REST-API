package kuit.baemin.validator;

import kuit.baemin.dto.request.user.PasswordChangeRequest;
import kuit.baemin.exception.BusinessException;
import kuit.baemin.utils.BaseResponseStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PasswordChangeValidator implements Validator {
    // 비밀번호 수정일때만 이 validator가 호출될 수 있도록
    @Override
    public boolean supports(Class<?> clazz) {
        return PasswordChangeRequest.class.isAssignableFrom(clazz);
    }

    //비즈니스 규칙
    @Override
    public void validate(Object target, Errors errors) {
        PasswordChangeRequest request = (PasswordChangeRequest) target;
        // 입력값간에 검증만 Validator에서 하기!
        if (!request.getNewPassword().equals(request.getNewPasswordConfirm())) {
//            errors.reject("passwordChange", "New passwords do not match");
            throw new BusinessException(BaseResponseStatus.NON_MATCH_PASSWORD);
        }
    }
}