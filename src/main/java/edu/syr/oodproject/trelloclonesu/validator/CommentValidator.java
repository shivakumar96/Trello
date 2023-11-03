package edu.syr.oodproject.trelloclonesu.validator;

import edu.syr.oodproject.trelloclonesu.common.exceptions.InvalidOperationException;
import edu.syr.oodproject.trelloclonesu.models.Comment;
import edu.syr.oodproject.trelloclonesu.models.User;
import edu.syr.oodproject.trelloclonesu.services.CommentService;
import edu.syr.oodproject.trelloclonesu.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentValidator {
    @Autowired
    private static CommentService commentService;
    @Autowired
    private static UserService userService;

    public static boolean validateComment(Comment comment){
        if(comment.getUser()==null || comment.getUser().getEmail() ==null || comment.getUser().getName() ==null)
           return false;
        User user = userService.get(comment.getUser().getUserID()).get();
        if (user == null)
            return  false;
        if(comment.getUser().getName() != null)
            if(! user.getName().equals(comment.getUser().getName()))
                return false;

        if(comment.getUser().getEmail() != null)
            if(! user.getEmail().equals(comment.getUser().getEmail()))
                return false;

        return true;
    }
}
