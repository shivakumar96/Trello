package edu.syr.oodproject.trelloclonesu.jpa.repository;

import edu.syr.oodproject.trelloclonesu.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
