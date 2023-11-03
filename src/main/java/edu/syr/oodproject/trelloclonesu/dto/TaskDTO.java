package edu.syr.oodproject.trelloclonesu.dto;

import edu.syr.oodproject.trelloclonesu.models.Comment;
import edu.syr.oodproject.trelloclonesu.models.Task;
import edu.syr.oodproject.trelloclonesu.models.User;
import edu.syr.oodproject.trelloclonesu.models.status.TaskStatus;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskDTO {
    private int taskID;
    private TaskStatus status;
    private String description;
    private List<UserDTO> assignedTo;
    private List<CommentDTO> commentDTOS;
    private LocalDateTime createdDate;
    private LocalDate dueDate;
    private LocalDateTime completionDate;

    public TaskDTO(int taskID, TaskStatus status, String description, List<UserDTO> assignedTo,
                   List<CommentDTO> commentDTOS, LocalDateTime createdDate, LocalDate dueDate,
                   LocalDateTime completionDate) {
        this.taskID = taskID;
        this.status = status;
        this.description = description;
        this.assignedTo = assignedTo;
        this.commentDTOS = commentDTOS;
        this.createdDate = createdDate;
        this.dueDate = dueDate;
        this.completionDate = completionDate;
    }

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
        return createdDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDateTime getCompletionDate() {
        return completionDate;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAssignedTo(List<UserDTO> assignedTo) {
        this.assignedTo = assignedTo;
    }

    public void setCommentDTOS(List<CommentDTO> commentDTOS) {
        this.commentDTOS = commentDTOS;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setCompletionDate(LocalDateTime completionDate) {
        this.completionDate = completionDate;
    }

    public static TaskDTO convertToTaskDTO(Task task){
        List<CommentDTO> comments = new ArrayList<>();
        List<UserDTO> assignedTo = new ArrayList<>();
        if(task == null) return null;
        for(Comment comment: task.getComments()){
            comments.add(CommentDTO.convertToCommentDTO(comment));
        }
        for (User user: task.getAssignedTo()){
            assignedTo.add(UserDTO.convertToUserDTO(user));
        }
        return new TaskDTO(task.getTaskID(),task.getStatus(),task.getDescription(),
                assignedTo,comments,task.getCreateDate(),task.getDueDate(),task.getCompletionDate());
    }
}