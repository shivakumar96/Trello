package edu.syr.oodproject.trelloclonesu.models;



import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class Board {

    @Id
    @GeneratedValue
    int boardID;

    @NotNull
    String boarDName;

    @OneToMany(mappedBy = "board",cascade = CascadeType.MERGE)
    List<Task> tasks;

    public int getBoardID() {
        return boardID;
    }

    public void setBoardID(int boardID) {
        this.boardID = boardID;
    }

    public String getBoarDName() {
        return boarDName;
    }

    public void setBoarDName(String boarDName) {
        this.boarDName = boarDName;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
