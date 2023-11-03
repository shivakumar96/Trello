package edu.syr.oodproject.trelloclonesu.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.syr.oodproject.trelloclonesu.models.status.TaskStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Task {

    @Id
    @GeneratedValue
    private int taskID;

    @ManyToMany(mappedBy = "tasks",cascade = CascadeType.ALL)
    private List<User> assignedTo = new ArrayList<>();

    private TaskStatus status;

    @Size(min = 5, message = "Description should have min of 5 character")
    private String description;

    @OneToMany(mappedBy = "task",cascade = CascadeType.MERGE)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "task", cascade = CascadeType.MERGE)
    @JsonIgnore
    private List<TaskHistory> histories = new ArrayList<>();

    @Column(nullable = false)
    private LocalDateTime createDate;

    @Column(nullable = true)
    private LocalDateTime completionDate;
    @FutureOrPresent(message = "Due date has to be a future date")
    @Column(nullable = true)
    private LocalDate dueDate;

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int id) {
        this.taskID = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDateTime completionDate) {
        this.completionDate = completionDate;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public List<TaskHistory> getHistories() {
        return histories;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public List<User> getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(List<User> assignedTo) {
        this.assignedTo = assignedTo;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public void setHistories(List<TaskHistory> histories) {
        this.histories = histories;
    }

    public void addHistory(TaskHistory history) {
        histories.add(history);
    }

    public void addUser(User user) {
        assignedTo.add(user);
    }

    public void removeUser(User user) {
        assignedTo.remove(user);
    }

}