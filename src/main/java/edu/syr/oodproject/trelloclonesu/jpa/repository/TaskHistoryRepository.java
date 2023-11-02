package edu.syr.oodproject.trelloclonesu.jpa.repository;

import edu.syr.oodproject.trelloclonesu.models.Task;
import edu.syr.oodproject.trelloclonesu.models.TaskHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskHistoryRepository extends JpaRepository<TaskHistory,Integer> {

    @Query("SELECT th FROM TaskHistory th WHERE th.task = :task")
    public List<TaskHistory> findAllByTask(@Param("task") Task task);


    @Query("DELETE FROM TaskHistory th WHERE th.task = :task")
    void deleteAllHistoryByTask(@Param("task") Task task);

}
