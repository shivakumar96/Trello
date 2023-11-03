package edu.syr.oodproject.trelloclonesu.services;

import edu.syr.oodproject.trelloclonesu.jpa.repository.TaskHistoryRepository;
import edu.syr.oodproject.trelloclonesu.models.TaskHistory;
import edu.syr.oodproject.trelloclonesu.common.api.dao.CommonServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskHistoryService implements CommonServiceAPI<TaskHistory> {

    @Autowired
    TaskHistoryRepository historyRepository;

    @Override
    public Optional<List<TaskHistory>> getAll() {
        return Optional.of(historyRepository.findAll());
    }

    @Override
    public Optional<TaskHistory> get(int id) {
        return historyRepository.findById(id);
    }

    @Override
    public void save(TaskHistory taskHistory) {
        taskHistory.setUpdateTime(LocalDateTime.now());
        historyRepository.save(taskHistory);
    }

    @Override
    public Optional<TaskHistory> update(TaskHistory taskHistory) {
        historyRepository.saveAndFlush(taskHistory);
        return historyRepository.findById(taskHistory.getTaskHistoryID());
    }

    @Override
    public void delete(TaskHistory taskHistory) {
        historyRepository.deleteById(taskHistory.getTaskHistoryID());
    }


}
