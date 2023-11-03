package edu.syr.oodproject.trelloclonesu.services;

import edu.syr.oodproject.trelloclonesu.common.exceptions.InvalidOperationException;
import edu.syr.oodproject.trelloclonesu.common.exceptions.UserNotFoundException;
import edu.syr.oodproject.trelloclonesu.jpa.repository.CommentRepository;
import edu.syr.oodproject.trelloclonesu.models.Comment;
import edu.syr.oodproject.trelloclonesu.common.api.dao.CommonServiceAPI;
import edu.syr.oodproject.trelloclonesu.models.Task;
import edu.syr.oodproject.trelloclonesu.models.User;
import edu.syr.oodproject.trelloclonesu.validator.CommentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService implements CommonServiceAPI<Comment> {

    @Autowired
    UserService userService;
    @Autowired
    TaskService taskService;

    @Autowired
    CommentRepository commentRepository;

    @Override
    public Optional<List<Comment>> getAll() {
        return null;
    }

    @Override
    public Optional<Comment> get(int id) {
        return null;
    }

    @Override
    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    public void save(Comment comment, int taskID){
        Task task = taskService.get(taskID).orElseThrow(()-> new UserNotFoundException("Task not found"));
        if(!CommentValidator.validateComment(comment))
            throw new InvalidOperationException("Mention User ID and Name ot email");
        User user = userService.get(comment.getUser().getUserID()).get();
        user.addComment(comment);
        task.getComments().add(comment);
        userService.update(user);
        taskService.update(task);
        commentRepository.save(comment);
    }

    @Override
    public Optional<Comment> update(Comment comment) {
       commentRepository.saveAndFlush(comment);
        return commentRepository.findById(comment.getCommentID());
    }

    @Override
    public void delete(Comment comment) {
        commentRepository.deleteById(comment.getCommentID());
    }
}
