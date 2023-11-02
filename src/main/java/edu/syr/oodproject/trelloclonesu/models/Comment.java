package edu.syr.oodproject.trelloclonesu.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity
public class Comment {
    @Id
    @GeneratedValue
    private int commentID;

   @NotNull(message = "task id cannot be null")
   @ManyToOne
   @JoinColumn(name = "task_id",nullable = false)
   @JsonIgnore
   private Task task;

    @NotNull(message = "user cannot be null")
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @Size(min = 1,message = "the comment description has to be min of 1 character")
    @Column(nullable = false)
    private String commentDescription ;

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public User getUser() {
        return user;
    }

    public void setUsers(User users) {
        this.user = users;
    }

    public String getCommentDescription() {
        return commentDescription;
    }

    public void setCommentDescription(String commentDescription) {
        this.commentDescription = commentDescription;
    }

}
