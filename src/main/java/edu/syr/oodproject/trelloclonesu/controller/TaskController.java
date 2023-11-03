package edu.syr.oodproject.trelloclonesu.controller;

import edu.syr.oodproject.trelloclonesu.common.exceptions.InvalidOperationException;
import edu.syr.oodproject.trelloclonesu.common.exceptions.TaskNotFoundException;
import edu.syr.oodproject.trelloclonesu.common.exceptions.UserNotFoundException;
import edu.syr.oodproject.trelloclonesu.common.logs.ApplicationLogger;
import edu.syr.oodproject.trelloclonesu.dto.CommentDTO;
import edu.syr.oodproject.trelloclonesu.dto.TaskDTO;
import edu.syr.oodproject.trelloclonesu.dto.UserDTO;
import edu.syr.oodproject.trelloclonesu.models.Comment;
import edu.syr.oodproject.trelloclonesu.models.Task;
import edu.syr.oodproject.trelloclonesu.models.TaskHistory;
import edu.syr.oodproject.trelloclonesu.models.User;
import edu.syr.oodproject.trelloclonesu.services.CommentService;
import edu.syr.oodproject.trelloclonesu.services.TaskService;
import edu.syr.oodproject.trelloclonesu.services.TaskHistoryService;
import edu.syr.oodproject.trelloclonesu.services.UserService;
import edu.syr.oodproject.trelloclonesu.validator.CommentValidator;
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

    private ApplicationLogger applicationLogger = ApplicationLogger.getApplicationLogger();


    @GetMapping(path = "/tasks")
    public ResponseEntity<List<TaskDTO>> getAllTasks(){
        List<Task> taskList = taskService.getAll().orElse(new ArrayList<Task>());
        List<TaskDTO> tasksDTOList = new ArrayList<>();
        for ( Task task: taskList)
            tasksDTOList.add(TaskDTO.convertToTaskDTO(task));
        return new ResponseEntity<>(tasksDTOList, HttpStatus.OK);
    }

    @GetMapping(path = "/tasks/{taskID}")
    public ResponseEntity<TaskDTO> getTask(@PathVariable int taskID){
        Task task =  taskService.get(taskID).orElseThrow( ()-> new TaskNotFoundException("Task not found") );
        TaskDTO taskDTO = TaskDTO.convertToTaskDTO(task);
        return new ResponseEntity<>(taskDTO,HttpStatus.OK);
    }

    @PostMapping(path = "/tasks")
    public ResponseEntity<TaskDTO> addTask(@Valid @RequestBody Task task){
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
        Task task = taskService.get(taskID).
                orElseThrow(()-> new InvalidOperationException("Cannot update a task which doesn't exists"));
        taskService.delete(task);
        return new ResponseEntity<>("Successfully deleted",HttpStatus.OK);
    }

    @GetMapping(path = "/tasks/{taskID}/history")
    public ResponseEntity<List<TaskHistory>> getTaskHistory(@PathVariable int taskID){
        Task task = taskService.get(taskID).orElseThrow(()-> new UserNotFoundException("Task not found"));
        List<TaskHistory> histories = task.getHistories();
        return new ResponseEntity<List<TaskHistory>>(histories,HttpStatus.OK);
    }

    @GetMapping(path = "/tasks/{taskID}/comments")
    public ResponseEntity<List<CommentDTO>> getAllComment(@PathVariable int taskID){
        Task task = taskService.get(taskID).orElseThrow(()-> new UserNotFoundException("Task not found"));
        List<Comment> comments = task.getComments();
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (Comment comment: comments)
            commentDTOList.add(CommentDTO.convertToCommentDTO(comment));
        return new ResponseEntity<>(commentDTOList,HttpStatus.OK);
    }

    @PostMapping(path = "/tasks/{taskID}/comments")
    public ResponseEntity<String> addAComment(@PathVariable int taskID ,@RequestBody Comment comment){
        commentService.save(comment,taskID);
        return new ResponseEntity<>("Comment added!",HttpStatus.OK);
    }

    @GetMapping(path = "/tasks/{taskID}/user")
    public ResponseEntity<List<UserDTO>> getAllUser(@PathVariable int taskID){

        return null;
    }

    @DeleteMapping(path = "/tasks/{taskID}/user")
    public ResponseEntity<String> DeleteUser(@PathVariable int taskID){

        return new ResponseEntity<>("User Removed!",HttpStatus.OK);
    }

}
