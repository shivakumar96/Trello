package edu.syr.oodproject.trelloclonesu.jpa.repository;

import edu.syr.oodproject.trelloclonesu.models.Task;
import edu.syr.oodproject.trelloclonesu.models.TaskHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskHistoryRepository extends JpaRepository<TaskHistory,Integer> {


}
