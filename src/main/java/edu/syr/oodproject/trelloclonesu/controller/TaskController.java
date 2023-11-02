package edu.syr.oodproject.trelloclonesu.controller;

import edu.syr.oodproject.trelloclonesu.common.exceptions.InvalidOperationException;
import edu.syr.oodproject.trelloclonesu.common.exceptions.TaskNotFoundException;
import edu.syr.oodproject.trelloclonesu.common.exceptions.UserNotFoundException;
import edu.syr.oodproject.trelloclonesu.common.logs.ApplicationLogger;
import edu.syr.oodproject.trelloclonesu.models.Comment;
import edu.syr.oodproject.trelloclonesu.models.Task;
import edu.syr.oodproject.trelloclonesu.models.TaskHistory;
import edu.syr.oodproject.trelloclonesu.services.CommentService;
import edu.syr.oodproject.trelloclonesu.services.TaskService;
import edu.syr.oodproject.trelloclonesu.services.TaskHistoryService;
import edu.syr.oodproject.trelloclonesu.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="/app/v1")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;
    @Autowired
    private TaskHistoryService historyService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private ApplicationLogger applicationLogger;


    @GetMapping(path = "/tasks")
    public ResponseEntity<List<Task>> getAllTasks(){
        List<Task> taskList = taskService.getAll().orElse(new ArrayList<Task>());
        return new ResponseEntity<>(taskList, HttpStatus.OK);
    }

    @GetMapping(path = "/tasks/{taskID}")
    public ResponseEntity<Task> getTask(@PathVariable int taskID){
        Task task =  taskService.get(taskID).orElseThrow( ()-> new TaskNotFoundException("Task not found") );
        return new ResponseEntity<Task>(task,HttpStatus.OK);
    }

    @PostMapping(path = "/tasks")
    public ResponseEntity<Task> addTask( @Valid @RequestBody Task task){
        taskService.save(task);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(task.getTaskID())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(path = "/tasks/{taskID}")
    public ResponseEntity<Task> modifyTask(@PathVariable int taskID, @Valid @RequestBody Task task){
        task.setTaskID(taskID);
        Task updatedTask = taskService.update(task).
                orElseThrow(()-> new InvalidOperationException("Cannot update a task which doesn't exists"));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().
                path("").buildAndExpand().toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/tasks/{taskID}")
    public ResponseEntity<String> deleteTask(@PathVariable int taskID){

        return new ResponseEntity<>("Successfully deleted",HttpStatus.OK);
    }

    @GetMapping(path = "/tasks/{taskID}/history")
    public ResponseEntity<List<TaskHistory>> getTaskHistory(@PathVariable int taskID){
        Task task = taskService.get(taskID).orElseThrow(()-> new UserNotFoundException("Task not found"));
        List<TaskHistory> histories = task.getHistories();
        return new ResponseEntity<List<TaskHistory>>(histories,HttpStatus.OK);
    }

    @GetMapping(path = "/tasks/{taskID}/comments")
    public ResponseEntity<List<Comment>> getAllComment(@PathVariable int taskID){
        return null;
    }

    @PostMapping(path = "/tasks/{taskID}/comments")
    public ResponseEntity<List<Comment>> addAComment(@PathVariable int taskID){
        return null;
    }

    @GetMapping(path = "/tasks/{taskID}/user")
    public ResponseEntity<List<Comment>> getAllUser(@PathVariable int taskID){
        return null;
    }
}
