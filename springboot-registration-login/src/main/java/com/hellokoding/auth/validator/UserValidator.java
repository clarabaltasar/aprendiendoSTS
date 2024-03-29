package com.hellokoding.auth.validator;

 

import com.hellokoding.auth.model.User;
import com.hellokoding.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

 

@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;

 

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

 

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        // Rejecting the user name if the field contains errors or if it's an empty value
        // It will also not be accepted if it's too long or too short
        //Or in case the user doesn't exist

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (user.getUsername().length() < 6 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }
        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        // Rejecting the password if the field contains errors or if it's an empty value
        // It will also not be accepted if it's too long or too short
        //Or in case the passwordConfirm it is not the same as the previous password

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

 

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
    }
}
