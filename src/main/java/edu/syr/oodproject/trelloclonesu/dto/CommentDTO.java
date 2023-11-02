package edu.syr.oodproject.trelloclonesu.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CommentDTO {
    private String userName;
    private String comment;

    public CommentDTO(String userName, String comment) {
        this.userName = userName;
        this.comment = comment;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
