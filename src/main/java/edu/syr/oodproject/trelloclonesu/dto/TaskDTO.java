package edu.syr.oodproject.trelloclonesu.dto;

import edu.syr.oodproject.trelloclonesu.models.status.TaskStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

class TaskDTO {
    private int taskID;
    private TaskStatus status;
    private String description;
    private List<UserDTO> assignedTo;
    private List<CommentDTO> commentDTOS;
    private LocalDateTime CreatedDate;
    private LocalDate DueDate;
    private LocalDateTime completionDate;

    public int getTaskID() {
        return taskID;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public List<UserDTO> getAssignedTo() {
        return assignedTo;
    }

    public List<CommentDTO> getCommentDTOS() {
        return commentDTOS;
    }

    public LocalDateTime getCreatedDate() {
        return CreatedDate;
    }

    public LocalDate getDueDate() {
        return DueDate;
    }

    public LocalDateTime getCompletionDate() {
        return completionDate;
    }

}