package edu.syr.oodproject.trelloclonesu.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
public class TaskHistory {
    @Id
    @GeneratedValue
    @JsonIgnore
    private int taskHistoryID;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "task_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
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
