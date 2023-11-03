package edu.syr.oodproject.trelloclonesu.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.syr.oodproject.trelloclonesu.features.notification.email.SendEmail;
import edu.syr.oodproject.trelloclonesu.models.status.UserStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;


@Entity(name = "user_details")
public class User {

    @Id
    @GeneratedValue
    private int userID;

    @Size(min=2,message = "name should contain min of 2 character ")
    private String name;

   @ManyToMany(cascade = CascadeType.MERGE)
   @JoinColumn(name = "task_id",nullable = false)
   @JsonIgnore
   @OnDelete(action = OnDeleteAction.CASCADE)
   private List<Task> tasks = new ArrayList<>();

    @NotNull(message = "email cannot be null")
    @Email
    private String email;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "task_id",nullable = false)
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Comment> comments = new ArrayList<>();

    private UserStatus userStatus;

    public int getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
    public void  addComment(Comment comment){
        comments.add(comment);
    }
    public void addTask(Task task){
        tasks.add(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void notifyUser(String message){
        String email = this.getEmail();
        SendEmail sendEmail = new SendEmail(email,message);
        sendEmail.sendNotification();
    }
}
