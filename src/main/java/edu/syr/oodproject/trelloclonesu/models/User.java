package edu.syr.oodproject.trelloclonesu.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.syr.oodproject.trelloclonesu.models.status.UserStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;


@Entity(name = "user_details")
public class User {

    @Id
    @GeneratedValue
    private int userID;

    @Size(min=2,message = "name should contain min of 2 character ")
    private String name;

   @ManyToMany
   @JoinColumn(name = "task_id",nullable = false)
   @JsonIgnore
   private List<Task> tasks ;

    @NotNull(message = "email cannot be null")
    @Email
    private String email;

    @ManyToMany
    @JoinColumn(name = "task_id",nullable = false)
    @JsonIgnore
    private List<Comment> comments;

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

}
