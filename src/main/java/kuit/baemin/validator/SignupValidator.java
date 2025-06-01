package kuit.baemin.validator;

import kuit.baemin.dto.SignupRequest;
import kuit.baemin.service.UserServiceV4;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;


@Component
public class SignupValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return SignupRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SignupRequest request = (SignupRequest) target;
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            errors.reject("NON_MATCH_PASSWORD");
        }
    }
}
