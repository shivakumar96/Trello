package edu.syr.oodproject.trelloclonesu.validator;

import edu.syr.oodproject.trelloclonesu.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {
    public static boolean validateBeforeInsert(User user){
        return (user.getName().length() !=0 &&
                user.getEmail().length() !=0 );
    }
}
