package edu.syr.oodproject.trelloclonesu.jpa.repository;

import edu.syr.oodproject.trelloclonesu.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
