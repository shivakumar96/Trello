package edu.syr.oodproject.trelloclonesu.jpa.repository;

import edu.syr.oodproject.trelloclonesu.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
}
