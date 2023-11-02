package edu.syr.oodproject.trelloclonesu.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.syr.oodproject.trelloclonesu.models.status.TaskStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.time.LocalDateTime;

@Entity
public class TaskHistory {
    @Id
    @GeneratedValue
    private int taskHistoryID;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "task_id",nullable = false)
    @JsonIgnore
    private Task task;
    @NotNull
    private String description;
    @NotNull(message = "Task modification state can't be null")
    private LocalDateTime updateTime;

    public int getTaskHistoryID() {
        return taskHistoryID;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public void setTaskHistoryID(int taskHistoryID) {
        this.taskHistoryID = taskHistoryID;
    }

}
