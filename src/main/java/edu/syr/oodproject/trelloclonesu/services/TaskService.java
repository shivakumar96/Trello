package edu.syr.oodproject.trelloclonesu.services;

import edu.syr.oodproject.trelloclonesu.common.exceptions.TaskNotFoundException;
import edu.syr.oodproject.trelloclonesu.common.exceptions.UserNotFoundException;
import edu.syr.oodproject.trelloclonesu.jpa.repository.TaskRepository;
import edu.syr.oodproject.trelloclonesu.models.Task;
import edu.syr.oodproject.trelloclonesu.common.api.dao.CommonServiceAPI;
import edu.syr.oodproject.trelloclonesu.models.TaskHistory;
import edu.syr.oodproject.trelloclonesu.models.User;
import edu.syr.oodproject.trelloclonesu.models.status.TaskStatus;
import edu.syr.oodproject.trelloclonesu.validator.TaskValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService implements CommonServiceAPI<Task> {

    @Autowired
    private TaskRepository repository;
    @Autowired
    private TaskHistoryService historyService;

    @Autowired UserService userService;

    @Override
    public Optional<List<Task>> getAll() {
        return Optional.of(repository.findAll());
    }

    @Override
    public Optional<Task> get(int id) {
        return repository.findById(id);
    }

    @Override
    public void save(Task task) {
        task.setCreateDate(LocalDateTime.now());
        task.setStatus(TaskStatus.TODO);
        TaskHistory history = new TaskHistory();
        history.setDescription("Created a Task");
        history.setUpdateTime(LocalDateTime.now());
        history.setTask(task);
        task.getHistories().add(history);
        repository.save(task);
        historyService.save(history);
    }

    public void save(User user, int taskID) {
        Task task = get(taskID).orElseThrow(()-> new TaskNotFoundException("Task doesn't exist"));
        User user1 = userService.get(user.getUserID()).orElseThrow(()-> new UserNotFoundException("User doesn't exist"));

        user1.addTask(task);
        task.getAssignedTo().add(user1);

        TaskHistory history = new TaskHistory();
        history.setDescription("Added User New User");
        history.setUpdateTime(LocalDateTime.now());
        history.setTask(task);
        task.getHistories().add(history);
        repository.save(task);
        historyService.save(history);
        userService.save(user1);
    }

    @Override
    public Optional<Task> update(Task task) {
        Task oldTask = repository.findById(task.getTaskID()).get();
        if(task == null)
            return Optional.empty();
        String message = TaskValidator.validateAndGetMessage(oldTask,task);
        if(!message.equals("")) {
            TaskHistory history = new TaskHistory();
            history.setTask(oldTask);
            history.setDescription(message);
            historyService.save(history);
            oldTask.getHistories().add(history);
        }
        oldTask.setStatus(task.getStatus());
        oldTask.setDescription(task.getDescription());
        oldTask.setDueDate(task.getDueDate());
        repository.save(oldTask);
        return Optional.of(oldTask);
    }
    @Override
    public void delete(Task task) {
        repository.deleteById(task.getTaskID());
    }

    public void delete(User user, int taskID) {
        Task task = get(taskID).orElseThrow(()-> new TaskNotFoundException("Task doesn't exist"));
        User user1 = userService.get(user.getUserID()).orElseThrow(()-> new UserNotFoundException("User doesn't exist"));
        task.getAssignedTo().remove(user1);
        user1.getTasks().remove(task);
        TaskHistory history = new TaskHistory();
        history.setDescription("Removed User New User");
        history.setUpdateTime(LocalDateTime.now());
        history.setTask(task);
        task.getHistories().add(history);
        repository.save(task);
        historyService.save(history);
        userService.save(user1);

    }
}
