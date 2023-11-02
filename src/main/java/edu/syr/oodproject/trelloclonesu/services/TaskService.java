package edu.syr.oodproject.trelloclonesu.services;

import edu.syr.oodproject.trelloclonesu.jpa.repository.TaskRepository;
import edu.syr.oodproject.trelloclonesu.models.Task;
import edu.syr.oodproject.trelloclonesu.common.api.dao.CommonServiceAPI;
import edu.syr.oodproject.trelloclonesu.models.TaskHistory;
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
    private TaskHistoryService historyDaoService;

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
        TaskHistory history = new TaskHistory();
        history.setDescription("Created a Task");
        history.setUpdateTime(LocalDateTime.now());
        history.setTask(task);
        task.getHistories().add(history);
        repository.saveAndFlush(task);
    }

    @Override
    public Optional<Task> update(Task task) {
        Task oldTask = repository.findById(task.getTaskID()).get();
        if(task == null)
            return Optional.empty();
        String message = TaskValidator.validateAndGetMessage(oldTask,task);
        if(!message.equals("")) {
            TaskHistory history = new TaskHistory();
            history.setTask(task);
            history.setDescription(message);
            historyDaoService.save(history);
        }
        repository.saveAndFlush(task);
        return repository.findById(task.getTaskID());
    }

    @Override
    public void delete(Task task) {
        repository.deleteById(task.getTaskID());
        historyDaoService.deleteHistoryForATask(task);
    }
}
