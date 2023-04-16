package com.appointnow.validation;

import com.appointnow.entity.user.User;
import com.appointnow.model.ChangePasswordForm;
import com.appointnow.service.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class CurrentPasswordMatchesValidator implements ConstraintValidator<CurrentPasswordMatches,Object> {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Override
    public void initialize(final CurrentPasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        var changePasswordForm = (ChangePasswordForm) value;
        boolean isValid = false;
        User user = userService.getUserById(changePasswordForm.getId());
        if (passwordEncoder.matches(changePasswordForm.getCurrentPassword(), user.getPassword()))
            isValid = true;

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode("currentPassword").addConstraintViolation();
        }
        return isValid;
    }
}
