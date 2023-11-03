package edu.syr.oodproject.trelloclonesu.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
public class Comment {
    @Id
    @GeneratedValue
    private int commentID;

   @NotNull(message = "task id cannot be null")
   @ManyToOne(cascade = CascadeType.MERGE)
   @JoinColumn(name = "task_id",nullable = false)
   @JsonIgnore
   @OnDelete(action = OnDeleteAction.CASCADE)
   private Task task;

    @NotNull(message = "user cannot be null")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
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
